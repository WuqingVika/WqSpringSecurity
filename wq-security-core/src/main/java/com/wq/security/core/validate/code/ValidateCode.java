package com.wq.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 短信验证码
 * Created by qwu on 2018/12/14.
 */
public class ValidateCode {
    private String code;//随机生成的数字 需要生成后放到session中供用户在提交登录请求时验证
    private LocalDateTime expireTime;//过期时间

    public ValidateCode(String code, int expireIn) {//expireIn代表多少时间内过期 比如 传个多少秒进来
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 验证是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
