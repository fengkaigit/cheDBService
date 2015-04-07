package com.chedb.model;

public class AccessToken {

	private String token;

	private int expiresIn;
	
	private long accesstime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(long accesstime) {
		this.accesstime = accesstime;
	}
	
}