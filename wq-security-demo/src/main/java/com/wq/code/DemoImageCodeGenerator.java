package com.wq.code;

import com.wq.security.core.validate.code.image.ImageCode;
import com.wq.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by qwu on 2018/12/13.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码---测试");
        return null;
    }
}
