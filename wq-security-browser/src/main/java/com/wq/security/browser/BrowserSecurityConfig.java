package com.wq.security.browser;

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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//这里也可以用自定义的加密的(只要实现加密解密方法就行) 或者Md5
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.SpringSecurity默认的登录方式
        //   1.1 通过表单登录的方式
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //   1.2 通过弹出式登录
        //http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
    }
}
