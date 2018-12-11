package com.wq.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 图形验证码异常
 * Created by qwu on 2018/12/11.
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
