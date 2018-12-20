package com.wq.security.core.social;

import com.wq.security.core.properties.SecurityProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 社交配置的适配器
 * Created by qwu on 2018/12/17.
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        //第三个参数用于插入数据库对token这些敏感信息进行加解密
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("wq_");
        return repository;
        //noOpText不做任何不添加盐值
    }

    @Bean
    public SpringSocialConfigurer wqSocialSecurityConfig() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        WqSpringSocialConfigurer configurer = new WqSpringSocialConfigurer(filterProcessesUrl);
        //configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }
}
