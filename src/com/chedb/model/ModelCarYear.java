package com.chedb.model;

public class ModelCarYear implements java.io.Serializable {

	private String id;
	private String carId;
	private String year;
	private String name;
	private String shortName;
	private String maintainPath; // 官方保养手册图片路径
	private Integer selected=0;

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

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

}
