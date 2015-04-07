package com.chedb.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chedb.dao.WechatDao;
import com.chedb.model.WechatAccountEntity;

@Repository("wechatDaoImpl")
public class WechatDaoImpl implements WechatDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public WechatAccountEntity getWechatAccountEntity(String appid)
			throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select * from wechataccount",
				WechatAccountEntity.class);
	}

}
