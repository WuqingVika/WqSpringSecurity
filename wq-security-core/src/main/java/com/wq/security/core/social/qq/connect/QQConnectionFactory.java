package com.wq.security.core.social.qq.connect;

import com.wq.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Created by qwu on 2018/12/17.
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        //pro..id:服务提供商的唯一标识
        //qqServiceProvider
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());

    }
}
