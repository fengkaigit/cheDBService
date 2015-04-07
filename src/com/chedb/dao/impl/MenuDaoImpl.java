package com.chedb.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.MenuDao;
import com.chedb.model.MenuEntity;

@Repository("menuDaoImpl")
public class MenuDaoImpl implements MenuDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public void saveMenuEntity(final MenuEntity menuValue) {}

	@Override
	public void updMenuEntity(final MenuEntity menuValue) {}

	@Override
	public void deleteMenuEntity(MenuEntity menuValue) {}

	@Override
	public boolean checkExist(MenuEntity menuValue) {
		// TODO Auto-generated method stub
		List<Object> arguments = new ArrayList<Object>();
		// arguments.add(menuValue.getName());
		arguments.add(menuValue.getId());
		// arguments.add(menuValue.getUrl());
		int argTypes[] = { Types.VARCHAR };
		int result = this.jdbcTemplate.queryForObject(
				"select count(1)  from wechatplfmenu where id=? ",
				arguments.toArray(), argTypes, Integer.class);
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<MenuEntity> getMenusByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String sql = "select * from wechatplfmenu where 1=1  ";
		Object accountId = params.get("accountId");
		if (accountId != null) {
			sql = sql + "  and accountid='" + accountId + "'";
		}
		Object toplevel = params.get("toplevel");
		if (toplevel != null) {
			sql = sql + "  and FATHERID is null ";

		}
		Object fatherId = params.get("fatherId");
		if (fatherId != null) {
			sql = sql + "  and FATHERID ='" + fatherId + "'";
		}
		Object id = params.get("id");
		if (id != null) {
			sql = sql + "  and id='" + id + "'";
		}
		if (params.get("menuKey") != null) {
			sql = sql + "  and menuKey='" + params.get("menuKey") + "'";
		}
		Object gridid = params.get("gridid");
		RowMapper<MenuEntity> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(MenuEntity.class);
		
		List<MenuEntity> menus = this.jdbcTemplate.query(sql, rm);
		
		for (MenuEntity menuEntity : menus) {
			String getchildSql = "select * from  wechatplfmenu a  start with a.fatherid='"
					+ menuEntity.getId()
					+ "' connect by prior a.id=a.fatherid ";
			List<MenuEntity> children = this.jdbcTemplate
					.query(getchildSql, rm);
			
			menuEntity.setMenuList(children);
			if (id != null && gridid != null) {
				return children;
			}
		}
		return menus;
	}

	@Override
	public List<MenuEntity> getChildMenus(String accountid, String fatherid) {
		// TODO Auto-generated method stub
		RowMapper<MenuEntity> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(MenuEntity.class);
		List<Object> arguments = new ArrayList<Object>();
		String sql = " select * from  wechatplfmenu a  start with a.accountid=?  and  a.fatherid=?  connect by prior a.id=a.fatherid ";
		arguments.add(accountid);
		arguments.add(fatherid);
		List<MenuEntity> rows = new ArrayList<MenuEntity>();
		try {
			rows = this.jdbcTemplate.query(sql, arguments.toArray(), rm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}

}
