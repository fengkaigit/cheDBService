package com.chedb.model;


public class ModelService implements java.io.Serializable {
	private String classId;
	private String serviceId;	
	private String name;
	
	private int businessNum;
	private int needCar;
	private int firstKm;
	private int periodKm;
	private String remark;
	
	private int preferential;
	private int priceOld;
	
	private int allowOnline;
	private int allowXianjin;
	private int allowJifen;// 是否可以积分抵付
	private int priceOnline;
	private int priceXianjin;
	private int priceJifen;// 抵付的话需要多少积分
	private int priceProvider;// 结算价
	
	private int returnJifenOnline;
	private int returnJifenXianjin;
	private int returnJifenJifen;
	
	private String workTitle;
	private String workUrlPath;
	private String rawUrlPath;
	
	private int needDelay;// 是否延迟
	
	private int hasRaw;
	// 服务明细文本
	private int itemNum;
	private String item1ClassTxt;
	private String item1Txt;
	private String item2ClassTxt;
	private String item2Txt;
	private String item3ClassTxt;
	private String item3Txt;
	private String item4ClassTxt;
	private String item4Txt;
	
	private String message;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}
	public int getNeedCar() {
		return needCar;
	}
	public void setNeedCar(int needCar) {
		this.needCar = needCar;
	}
	public int getFirstKm() {
		return firstKm;
	}
	public void setFirstKm(int firstKm) {
		this.firstKm = firstKm;
	}
	public int getPeriodKm() {
		return periodKm;
	}
	public void setPeriodKm(int periodKm) {
		this.periodKm = periodKm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getAllowOnline() {
		return allowOnline;
	}
	public void setAllowOnline(int allowOnline) {
		this.allowOnline = allowOnline;
	}
	public int getAllowXianjin() {
		return allowXianjin;
	}
	public void setAllowXianjin(int allowXianjin) {
		this.allowXianjin = allowXianjin;
	}
	public int getAllowJifen() {
		return allowJifen;
	}
	public void setAllowJifen(int allowJifen) {
		this.allowJifen = allowJifen;
	}
	public int getPriceOnline() {
		return priceOnline;
	}
	public void setPriceOnline(int priceOnline) {
		this.priceOnline = priceOnline;
	}
	public int getPriceXianjin() {
		return priceXianjin;
	}
	public void setPriceXianjin(int priceXianjin) {
		this.priceXianjin = priceXianjin;
	}
	public int getPriceJifen() {
		return priceJifen;
	}
	public void setPriceJifen(int priceJifen) {
		this.priceJifen = priceJifen;
	}
	public int getReturnJifenOnline() {
		return returnJifenOnline;
	}
	public void setReturnJifenOnline(int returnJifenOnline) {
		this.returnJifenOnline = returnJifenOnline;
	}
	public int getReturnJifenXianjin() {
		return returnJifenXianjin;
	}
	public void setReturnJifenXianjin(int returnJifenXianjin) {
		this.returnJifenXianjin = returnJifenXianjin;
	}
	public int getReturnJifenJifen() {
		return returnJifenJifen;
	}
	public void setReturnJifenJifen(int returnJifenJifen) {
		this.returnJifenJifen = returnJifenJifen;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWorkUrlPath() {
		return workUrlPath;
	}
	public void setWorkUrlPath(String workUrlPath) {
		this.workUrlPath = workUrlPath;
	}
	public String getRawUrlPath() {
		return rawUrlPath;
	}
	public void setRawUrlPath(String rawUrlPath) {
		this.rawUrlPath = rawUrlPath;
	}
	public int getHasRaw() {
		return hasRaw;
	}
	public void setHasRaw(int hasRaw) {
		this.hasRaw = hasRaw;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getItem1ClassTxt() {
		return item1ClassTxt;
	}
	public void setItem1ClassTxt(String item1ClassTxt) {
		this.item1ClassTxt = item1ClassTxt;
	}
	public String getItem1Txt() {
		return item1Txt;
	}
	public void setItem1Txt(String item1Txt) {
		this.item1Txt = item1Txt;
	}
	public String getItem2ClassTxt() {
		return item2ClassTxt;
	}
	public void setItem2ClassTxt(String item2ClassTxt) {
		this.item2ClassTxt = item2ClassTxt;
	}
	public String getItem2Txt() {
		return item2Txt;
	}
	public void setItem2Txt(String item2Txt) {
		this.item2Txt = item2Txt;
	}
	public String getItem3ClassTxt() {
		return item3ClassTxt;
	}
	public void setItem3ClassTxt(String item3ClassTxt) {
		this.item3ClassTxt = item3ClassTxt;
	}
	public String getItem3Txt() {
		return item3Txt;
	}
	public void setItem3Txt(String item3Txt) {
		this.item3Txt = item3Txt;
	}
	public String getItem4ClassTxt() {
		return item4ClassTxt;
	}
	public void setItem4ClassTxt(String item4ClassTxt) {
		this.item4ClassTxt = item4ClassTxt;
	}
	public String getItem4Txt() {
		return item4Txt;
	}
	public void setItem4Txt(String item4Txt) {
		this.item4Txt = item4Txt;
	}
	public int getPriceOld() {
		return priceOld;
	}
	public void setPriceOld(int priceOld) {
		this.priceOld = priceOld;
	}
	public int getPreferential() {
		return preferential;
	}
	public void setPreferential(int preferential) {
		this.preferential = preferential;
	}
	public int getPriceProvider() {
		return priceProvider;
	}
	public void setPriceProvider(int priceProvider) {
		this.priceProvider = priceProvider;
	}
	public int getNeedDelay() {
		return needDelay;
	}
	public void setNeedDelay(int needDelay) {
		this.needDelay = needDelay;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
