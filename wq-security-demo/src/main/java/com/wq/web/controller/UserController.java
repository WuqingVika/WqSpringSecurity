package com.wq.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wq.dto.User;
import com.wq.dto.UserQueryCondition;
import com.wq.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwu on 2018/12/4.
 */
@RequestMapping(value = "/user")
@RestController//用来提供restful服务的
public class UserController {

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println("error---->" + error.getDefaultMessage()));
        }
        user.setId("1");
        System.out.println(user.toString());
        return user;
    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping
    @JsonView(User.UserSimpleView.class)//简单视图，不显示密码
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(page = 2, size = 10, sort = "username,asc") Pageable pageable) {//    public List<User> query() {// System.out.println(username);
        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
        //打印分页的
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        System.out.println(pageable.getPageNumber());
        List<User> users = new ArrayList<>();
        users.add(new User("wuqingvika", "21"));
        users.add(new User("wuqingvika", "23"));
        users.add(new User("wuqingvika", "20"));
        return users;
    }


    @JsonView(User.UserDetailView.class)//显示详情(用户名+密码)
    //idhh只能接收数字
    //@RequestMapping(value = "/user/{idhh:\\d+}", method = RequestMethod.GET)
    @GetMapping(value = "/{idhh:\\d+}")
    public User getInfo(@PathVariable(name = "idhh") String id) {
       // throw new RuntimeException("dfsdfklsdf");
       System.out.println(ReflectionToStringBuilder.toString(id));
        List<User> users = new ArrayList<>();
        users.add(new User("wuqingvika", "21"));
        users.add(new User("wuqingvika", "23"));
        users.add(new User("wuqingvika", "20"));
        return users.get(0);
    }

    /**
     * 根据id更新一个用户信息
     *
     * @param user
     * @param errors
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println("error---->" + error.getDefaultMessage()));
        }
        //throw new UserNotExistException("user not exist");
       /* System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());*/

        user.setId("1");
        return user;
    }

    /**
     * 根据Id删除用户信息
     *
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

}
