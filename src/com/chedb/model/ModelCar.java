package com.chedb.model;

public class ModelCar implements java.io.Serializable {

	private String id;
	private String brandId;
	private String seriseId;
	private String name;
	private String maintainPath; // 官方保养手册图片路径
	private Integer selected;
	private String brandname;
	private String serisename;

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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSeriseId() {
		return seriseId;
	}

	public void setSeriseId(String seriseId) {
		this.seriseId = seriseId;
	}

	public String getMaintainPath() {
		return maintainPath;
	}

	public void setMaintainPath(String maintainPath) {
		this.maintainPath = maintainPath;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getSerisename() {
		return serisename;
	}

	public void setSerisename(String serisename) {
		this.serisename = serisename;
	}

}
