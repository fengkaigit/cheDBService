package com.chedb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chedb.dao.MenuDao;
import com.chedb.model.MenuEntity;
import com.chedb.service.MenuserviceI;

@Service("menuserviceImpl")
public class MenuserviceImpl implements MenuserviceI {
	@Resource(name = "menuDaoImpl")
	private MenuDao menuDao;

	@Override
	public List<MenuEntity> getMenusByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.menuDao.getMenusByParams(params);
	}

	@Override
	public void saveOrUpdate(MenuEntity menuValue) {}

	@Override
	public void deleteMenuEntity(MenuEntity menuValue) {
		// TODO Auto-generated method stub
		this.menuDao.deleteMenuEntity(menuValue);
	}

	@Override
	public MenuEntity getMenuEntity(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<MenuEntity> menus = this.menuDao.getMenusByParams(params);
		return menus.get(0);
	}

	@Override
	public List<MenuEntity> getChildMenus(String accountid, String fatherid) {
		// TODO Auto-generated method stub
		return this.menuDao.getChildMenus(accountid, fatherid);
	}

}
