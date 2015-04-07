package com.chedb.model;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class ModelSysItem implements java.io.Serializable {

	private String parentId;

	private String labelId;

	private String title;

	private String sysItemName;
	private Float price;
	private Float oldprice;
	private String summary;
	private int select;
	private String pricestr;
	private String oldpricestr;
	private List<ModelSysItem> childitems;

	public boolean equal(ModelSysItem label) {
		return this.labelId.equals(label.getLabelId());
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public List<ModelSysItem> getChilditems() {
		return childitems;
	}

	public void setChilditems(List<ModelSysItem> childitems) {
		this.childitems = childitems;
	}

	public String getSysItemName() {
		return sysItemName;
	}

	public void setSysItemName(String sysItemName) {
		this.sysItemName = sysItemName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getOldprice() {
		return oldprice;
	}

	public void setOldprice(Float oldprice) {
		this.oldprice = oldprice;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPricestr() {
		return pricestr;
	}

	public void setPricestr(String pricestr) {
		this.pricestr = pricestr;
	}

	public String getOldpricestr() {
		return oldpricestr;
	}

	public void setOldpricestr(String oldpricestr) {
		this.oldpricestr = oldpricestr;
	}

}
