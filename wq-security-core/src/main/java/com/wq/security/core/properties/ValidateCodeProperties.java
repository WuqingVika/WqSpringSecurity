package com.wq.security.core.properties;

/**
 * 验证码属性
 * Created by qwu on 2018/12/11.
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
