package com.wq.security.core.validate.code;

import com.wq.security.core.properties.SecurityProperties;
import com.wq.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证图形码数字是否输入正确
 * Created by qwu on 2018/12/11.
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {//实现这个接口的目的是为了在其他参数组装完毕后 去初始化我们的urls的值
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls= new HashSet<>();
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher=new AntPathMatcher();//这个是用/user/*去匹配/user/1等请求的

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls=StringUtils.split(securityProperties.getCode().getImage().getUrl(),",");
        for (String configUrl:configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/form");//因为登录的验证码是必需的 所以这里直接加上了
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action=false;
        for (String url:urls){
            if(pathMatcher.match(url,request.getRequestURI())){
                action=true;
            }
        }
        if (action) {
            //如果是表单请求且是post请求，即代表用户点击登录按钮
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;//直接返回 不让它往下走下面的过滤器
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, "SESSION_KEY_FOR_CODE_IMAGE");

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    "imageCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, "SESSION_KEY_FOR_CODE_IMAGE");
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, "SESSION_KEY_FOR_CODE_IMAGE");
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
