package com.wq.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by qwu on 2018/12/10.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.SpringSecurity默认的登录方式
        //   1.1 通过表单登录的方式
        /*http.formLogin().and().authorizeRequests().anyRequest().authenticated();*/
        //   1.2 通过弹出式登录
        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
    }
}
