package com.chedb.service;

import java.util.List;
import java.util.Map;

import com.chedb.model.MenuEntity;

public interface MenuserviceI {
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<MenuEntity> getMenusByParams(Map<String, Object> params);

	/**
	 * 
	 * @param params
	 * @return
	 */
	public MenuEntity getMenuEntity(Map<String, Object> params);

	/**
	 * 
	 * @param menuValue
	 */
	public void saveOrUpdate(MenuEntity menuValue);

	/**
	 * 
	 * @param menuValue
	 */
	public void deleteMenuEntity(MenuEntity menuValue);

	/**
	 * 
	 * @param accountid
	 * @param fatherid
	 * @return
	 */
	public List<MenuEntity> getChildMenus(String accountid,String fatherid);

}
