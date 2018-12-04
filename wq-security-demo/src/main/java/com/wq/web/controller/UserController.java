package com.wq.web.controller;

import com.wq.dto.User;
import com.wq.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public List<User> query(UserQueryCondition userQueryCondition,@PageableDefault(page=2,size = 10,sort = "username,asc") Pageable pageable) {//    public List<User> query() {// System.out.println(username);
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        //打印分页的
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        System.out.println(pageable.getPageNumber());
        List<User> users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
