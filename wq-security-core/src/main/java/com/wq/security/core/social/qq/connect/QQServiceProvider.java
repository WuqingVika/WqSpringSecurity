package com.wq.security.core.social.qq.connect;

import com.wq.security.core.social.qq.api.QQ;
import com.wq.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Created by qwu on 2018/12/17.
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {//OAuth2Operations oauth2Operations
        //super(oauth2Operations);
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        //多例的 所以不能直接像以前那样通过@Component的方式
        return new QQImpl(accessToken, appId);
    }
}
