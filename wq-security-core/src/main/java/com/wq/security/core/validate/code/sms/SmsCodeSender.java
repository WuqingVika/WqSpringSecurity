package com.wq.security.core.validate.code.sms;

/**
 * 短信验证码发送接口
 * Created by qwu on 2018/12/14.
 */
public interface SmsCodeSender {
    /**
     * 发送短信验证码
     * @param mobile 向哪个手机发送
     * @param code 发送的验证内容
     */
    void send(String mobile,String code);
}
