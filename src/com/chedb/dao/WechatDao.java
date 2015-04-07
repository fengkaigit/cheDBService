package com.chedb.dao;

import com.chedb.model.WechatAccountEntity;

public interface WechatDao {
	public WechatAccountEntity getWechatAccountEntity(String appid)
			throws Exception;
}
