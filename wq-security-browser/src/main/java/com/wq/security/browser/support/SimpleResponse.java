package com.wq.security.browser.support;

/**
 * Created by qwu on 2018/12/10.
 */
public class SimpleResponse {
    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
