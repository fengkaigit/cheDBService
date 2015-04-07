package com.chedb.model;

public class ModelUser implements java.io.Serializable {

	private String no;

	// 1车主，2修理厂，3卖工具的
	private int level;

	private String name;

	private String passwd;

	private String phone;

	private int jifen;
	/**
	 * 
	 */
	private String loginmess;
	private boolean ok;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public String getLoginmess() {
		return loginmess;
	}

	public void setLoginmess(String loginmess) {
		this.loginmess = loginmess;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	

}
