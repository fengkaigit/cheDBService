package com.chedb.model;

import java.util.Date;

public class WechatUserModel {
	private String openid;
	private String username;
	private Date binddate;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBinddate() {
		return binddate;
	}

	public void setBinddate(Date binddate) {
		this.binddate = binddate;
	}

}
