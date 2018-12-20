package com.wq.security.core.properties;

import org.springframework.boot.autoconfigure.social.*;

/**
 * Created by qwu on 2018/12/18.
 */
public class QQProperties extends org.springframework.boot.autoconfigure.social.SocialProperties {

    private String providerId = "qq";//服务提供方的唯一标识

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
