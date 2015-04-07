package com.chedb.model;

import java.util.List;


public class MenuEntity  {
	private String id;
	private String name;
	private String menuKey;
	private String type;//click or view
	private String url;//
	private String msgType;//
	private String templateId;//
	private int orders;
	private MenuEntity menuEntity;
	private List<MenuEntity> menuList; 
	private String accountId;
	private String fatherid;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	
	
	public MenuEntity getMenuEntity() {
		return menuEntity;
	}
	
	public void setMenuEntity(MenuEntity menuEntity) {
		this.menuEntity = menuEntity;
	}

	public List<MenuEntity> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuEntity> menuList) {
		this.menuList = menuList;
	}

	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	
}
