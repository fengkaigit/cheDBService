package com.chedb.model;


public class ModelCarUserChooseItem implements java.io.Serializable {

	private String id;
	private String name;
	private int type;//1为车系，2为车型（含年款）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
