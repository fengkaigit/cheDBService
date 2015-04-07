package com.chedb.model;

import java.util.List;

public class ModelProvider implements java.io.Serializable {

	private String providerId;

	private String title;
	private String summary;
	private String remark;

	private int browse; // �������
	private int business; // �ɽ�����

	private float score; // �ۺϵ÷�
	private int scoreTotal; // �ܷ�
	private int scoreCount; // �������

	private String addr;
	private String phone;

	private double latitude;// γ��
	private double longitude;// ����

	private int jl; // �뵱ǰ�û�����λ�õľ���

	private int renzheng;// ��֤
	private int s4;
	private int liansuo;

	public String[] imgIdList;

	private String imgIdListStr;
	private String defaultimg;

	private List<ModelProviderItem> providerItems;

	public void copyDetailInfo(ModelProvider src) {
		this.summary = src.summary;
		this.remark = src.remark;
		this.imgIdListStr = src.imgIdListStr;
		this.phone = src.phone;
		this.browse = src.browse;
		this.business = src.business;
	}

	public String getImgIdListStr() {
		return imgIdListStr;
	}

	public void setImgIdListStr(String imgIdListStr) {
		this.imgIdListStr = imgIdListStr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}

	public int getBusiness() {
		return business;
	}

	public void setBusiness(int business) {
		this.business = business;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getRenzheng() {
		return renzheng;
	}

	public void setRenzheng(int renzheng) {
		this.renzheng = renzheng;
	}

	public int getS4() {
		return s4;
	}

	public void setS4(int s4) {
		this.s4 = s4;
	}

	public int getLiansuo() {
		return liansuo;
	}

	public void setLiansuo(int liansuo) {
		this.liansuo = liansuo;
	}

	public int getJl() {
		return jl;
	}

	public void setJl(int jl) {
		this.jl = jl;
	}

	public int getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public List<ModelProviderItem> getProviderItems() {
		return providerItems;
	}

	public void setProviderItems(List<ModelProviderItem> providerItems) {
		this.providerItems = providerItems;
	}

	public String getDefaultimg() {
		return defaultimg;
	}

	public void setDefaultimg(String defaultimg) {
		this.defaultimg = defaultimg;
	}

}
