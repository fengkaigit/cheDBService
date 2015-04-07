package com.chedb.model;

public class ModelDatumLabel implements java.io.Serializable {

	private String theId;
	private String panretId;
	private String tatle;
	private Integer status;
	private Integer sort;
	public String getTheId() {
		return theId;
	}
	public void setTheId(String theId) {
		this.theId = theId;
	}
	public String getPanretId() {
		return panretId;
	}
	public void setPanretId(String panretId) {
		this.panretId = panretId;
	}
	public String getTatle() {
		return tatle;
	}
	public void setTatle(String tatle) {
		this.tatle = tatle;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
}
