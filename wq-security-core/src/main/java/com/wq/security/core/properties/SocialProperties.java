package com.wq.security.core.properties;

/**
 * Created by qwu on 2018/12/18.
 */
public class SocialProperties {

    private String filterProcessesUrl = "/auth";//默认Social也是/auth

    private QQProperties qq = new QQProperties();


    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }


}
