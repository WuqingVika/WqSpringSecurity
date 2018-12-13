package com.wq.code;

import com.wq.security.core.validate.code.ImageCode;
import com.wq.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qwu on 2018/12/13.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        System.out.println("更高级的图形验证码生成代码---测试");
        return null;
    }
}
