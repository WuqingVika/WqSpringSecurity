package com.wq.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * 验证码生成接口
 * Created by qwu on 2018/12/12.
 */
public interface ValidateCodeGenerator {
     ValidateCode generate(ServletWebRequest request);
}
