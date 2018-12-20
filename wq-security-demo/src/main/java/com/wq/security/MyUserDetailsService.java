package com.wq.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by qwu on 2018/12/10.
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    //这里可以注入你查询数据库需要的一些Mapper.
   /* @Autowired
    private UserMapper userMapper;*/
    @Autowired
    private PasswordEncoder passwordEncoder;//这个加密工具每次会随机加盐 不容易被人破解
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("表单登录用户名:" + username);//下面最后一个参数是将用户拥有的权限转化为权限集合的。
        //其中四个true分别代表：是否可用，账号是否过期，密码是否过期，账号是否被冻结/锁定
       /* return new User(username, passwordAuth, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));*/
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户Id:" + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是:" + password);
        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
