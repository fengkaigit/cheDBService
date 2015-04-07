package com.chedb.model;

public class ModelDatum implements java.io.Serializable {

	private String theId;
	private String labelId;
	private String userId;
	private String userName;
	private String tatle;
	private String url;
	private Integer status;
	private Integer renzheng;
	private Integer sort;
	public String getTheId() {
		return theId;
	}
	public void setTheId(String theId) {
		this.theId = theId;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTatle() {
		return tatle;
	}
	public void setTatle(String tatle) {
		this.tatle = tatle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(Integer renzheng) {
		this.renzheng = renzheng;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
