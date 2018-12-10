package com.wq.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户查询条件
 * Created by qwu on 2018/12/4.
 */
public class UserQueryCondition {
    private String username;
    @ApiModelProperty(value = "用户年龄起始值")
    private int age;
    @ApiModelProperty(value = "用户年龄终止值")
    private int ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}
