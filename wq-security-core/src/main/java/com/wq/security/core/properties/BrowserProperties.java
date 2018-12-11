package com.wq.security.core.properties;

/**
 * Created by qwu on 2018/12/11.
 */
public class BrowserProperties {
    private String loginPage = "/wq-signIn.html";//web端登录页 默认的登录页是我们整个应用自定义的登录页面

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
