package com.wq.security.core.validate.code;

import com.wq.security.core.properties.SecurityProperties;
import com.wq.security.core.validate.code.image.ImageCodeGenerator;
import com.wq.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.wq.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by qwu on 2018/12/13.
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean(name = "imageValidateCodeGenerator")//imageValidateCodeGenerator
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")//imageCodeGenerator
    public ValidateCodeGenerator imageCodeGenerator() {
        System.out.println("生成imageValidateCodeGenerator");
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)//name="smsCodeSender"
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
