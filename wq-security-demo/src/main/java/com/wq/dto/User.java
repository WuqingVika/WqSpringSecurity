package com.wq.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.wq.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by qwu on 2018/12/4.
 */
public class User {
    //使用接口来声明多个视图
    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    private String id;
    @MyConstraint(message = "wuqingvika验证username默认抛出错误信息")
    private String username;
    @NotBlank
    private String password;
    @Past(message = "生日必须是过去的时间")
    private Date birthday;

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

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String id, String username, String password, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
