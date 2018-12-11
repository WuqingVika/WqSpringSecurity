package com.wq.security.browser;

import com.wq.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by qwu on 2018/12/10.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//这里也可以用自定义的加密的(只要实现加密解密方法就行) 或者Md5
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.SpringSecurity默认的登录方式
        //   1.1 通过表单登录的方式
       // http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //   1.2 通过弹出式登录
        //http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        //2 自定义登录页面
        http.formLogin()
                //.loginPage("/wq-signIn.html")
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")//踩坑:这里没有这句话 登录不能跳转之前请求了
                .and()
                .authorizeRequests()
               // .antMatchers("/wq-signIn.html")
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage())//这里登录页也不需要权限验证
                .permitAll()//wq-signIn.html也要验证 如果不配取消授权会陷入死循环
                .anyRequest().authenticated()//如果不加下面一句在登录时，Could not verify the provided CSRF token because your session was not found.
        .and().csrf().disable();
    }
}
