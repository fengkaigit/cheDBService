package com.chedb.dao;


import java.util.List;
import java.util.Map;

import com.chedb.model.MenuEntity;

public interface MenuDao {
	/**
	 * 
	 * @param menuValue
	 */
	public void saveMenuEntity(MenuEntity menuValue);
   /**
    * 
    * @param menuValue
    */
	public void updMenuEntity(MenuEntity menuValue);
   /**
    * 
    * @param menuValue
    */
	public void deleteMenuEntity(MenuEntity menuValue);
   /**
    * 
    * @param menuValue
    * @return
    */
	public boolean checkExist(MenuEntity menuValue);
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<MenuEntity> getMenusByParams(Map<String, Object> params);
	
	public List<MenuEntity> getChildMenus(String accountid,String fatherid);
}
