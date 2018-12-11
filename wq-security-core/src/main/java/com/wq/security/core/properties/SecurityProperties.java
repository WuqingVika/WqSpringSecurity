package com.wq.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by qwu on 2018/12/11.
 */
@ConfigurationProperties(prefix = "wq.security")//会读取所有以wq.security开头的配置项
public class  SecurityProperties {
    private BrowserProperties browser=new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
