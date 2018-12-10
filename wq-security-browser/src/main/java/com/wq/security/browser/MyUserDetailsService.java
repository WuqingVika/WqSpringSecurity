package com.wq.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by qwu on 2018/12/10.
 */
@Component
public class MyUserDetailsService implements UserDetailsService{
    //这里可以注入你查询数据库需要的一些Mapper.
   /* @Autowired
    private UserMapper userMapper;*/

   @Autowired
   private PasswordEncoder passwordEncoder;//这个加密工具每次会随机加盐 不容易被人破解
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录：http://localhost:8060/login
        //自定义用户登录认证逻辑 passwordEncoder.encode(password)通过加密后再匹配，在现实环境中直接取数据库里加密过的密码作为参数。
        String password="123";//现实中用户登录的密码
        String passwordAuth = passwordEncoder.encode(password);//数据库取出的密码 ---应该都是从数据库获取，但这里不作为重点 所以直接简单写
        logger.info("数据库密码是:"+passwordAuth);
        logger.info("表单登录用户名:" + username);//下面最后一个参数是将用户拥有的权限转化为权限集合的。
        //其中四个true分别代表：是否可用，账号是否过期，密码是否过期，账号是否被冻结/锁定
        return new User(username,passwordAuth, true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
