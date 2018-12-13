package com.wq.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成接口
 * Created by qwu on 2018/12/12.
 */
public interface ValidateCodeGenerator {
     ImageCode generate(HttpServletRequest request) ;
}
