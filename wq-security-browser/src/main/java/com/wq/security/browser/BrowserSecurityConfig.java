package com.wq.security.browser;

import com.wq.security.core.authentication.AbstractChannelSecurityConfig;
import com.wq.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.wq.security.core.properties.SecurityConstants;
import com.wq.security.core.properties.SecurityProperties;
import com.wq.security.core.validate.code.ValidateCodeFilter;
import com.wq.security.core.validate.code.ValidateCodeSecurityConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * Created by qwu on 2018/12/10.
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SpringSocialConfigurer wqSpringSocialConfigurer;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//这里也可以用自定义的加密的(只要实现加密解密方法就行) 或者Md5
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);//在启动的时候创建表
        return tokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.SpringSecurity默认的登录方式
        //   1.1 通过表单登录的方式
        // http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //   1.2 通过弹出式登录
        //http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        //2 自定义登录页面
        /*ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(wqAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//加在用户名密码校验之前
        .formLogin()
                //.loginPage("/wq-signIn.html")
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")//踩坑:这里没有这句话 登录不能跳转之前请求了
                .successHandler(wqAuthenticationSuccessHandler)//自个写的成功处理器
                .failureHandler(wqAuthenticationFailureHandler)
                .and()
        */
        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(wqSpringSocialConfigurer)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)//拿到用户名后登录
                .and()
                .authorizeRequests()
                // .antMatchers("/wq-signIn.html")
                //.antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage(), "/code/*")//这里登录页也不需要权限验证、图形/短信验证码也不需要
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
                .permitAll()//wq-signIn.html也要验证 如果不配取消授权会陷入死循环
                .anyRequest().authenticated()//如果不加下面一句在登录时，Could not verify the provided CSRF token because your session was not found.
                .and().csrf().disable();
    }
}
