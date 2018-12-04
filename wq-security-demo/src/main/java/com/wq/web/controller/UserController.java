package com.wq.web.controller;

import com.wq.dto.User;
import com.wq.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwu on 2018/12/4.
 */
@RestController//用来提供restful服务的
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(UserQueryCondition userQueryCondition,Pageable pageable) {//    public List<User> query() {// System.out.println(username);
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
