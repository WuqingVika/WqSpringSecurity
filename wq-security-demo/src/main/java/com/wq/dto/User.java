package com.wq.dto;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by qwu on 2018/12/4.
 */
public class User {
    //使用接口来声明多个视图
    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    private String username;

    private String password;//

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
