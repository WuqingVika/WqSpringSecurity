package com.wq.security.core.properties;

/**
 * 短信验证码属性 包含长度 过期时间
 * add on 2018/12/15
 */
public class SmsCodeProperties {
	
	private int length = 6;
	private int expireIn = 60;
	
	private String url;

	public int getLength() {
		return length;
	}
	public void setLength(int lenght) {
		this.length = lenght;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
