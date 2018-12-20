package com.wq.security.core.social.qq.connect;

import com.wq.security.core.social.qq.api.QQ;
import com.wq.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 将服务提供商获取的用户信息把它适配到标准的用户信息上
 * Created by qwu on 2018/12/17.
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ qq) {
        return true;//这里认为QQ永远是通的
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo=api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());//用户40*40大小的头像是一定有的
        values.setProfileUrl(null);//主页 如果连的是微博可以把微博的个人主页放进去、
        values.setProviderUserId(userInfo.getOpenId());//服务商的用户id即OpenId
        System.out.println("QQAdapter--setConnectionValues()中拿到的用户信息如下：\n"+userInfo);
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {
        //这里不用 像微博发送啊 会有时间线的状态啊啥的。
    }
}
