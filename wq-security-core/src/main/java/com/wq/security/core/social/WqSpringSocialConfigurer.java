package com.wq.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 社交登录相关配置
 * Created by qwu on 2018/12/18.
 */
public class WqSpringSocialConfigurer extends org.springframework.social.security.SpringSocialConfigurer {

    private String filterProcessesUrl;

    public WqSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }

}