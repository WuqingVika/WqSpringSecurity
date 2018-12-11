package com.wq.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wq.security.core.properties.LoginType;
import com.wq.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证成功的处理逻辑
 * Created by qwu on 2018/12/11.
 */
@Component("wqAuthenticationSuccessHandler")
public class WqAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler/*implements AuthenticationSuccessHandler*/ {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;//这个注进来用来判断登录成功后 到底是返回一个json还是进行页面跳转

    @Override//自定义成功的处理行为
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("browser登录成功");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            //调用父类的方法跳转
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
