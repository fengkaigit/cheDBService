package com.chedb.service.impl;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.WechatDao;
import com.chedb.model.AccessToken;
import com.chedb.model.WechatAccountEntity;
import com.chedb.service.WechatManager;
import com.chedb.util.WeixinUtil;

@Service("wechatManagerImpl")
@Transactional(rollbackFor = Exception.class)
public class WechatManagerImpl implements WechatManager {
	@Resource(name = "wechatDaoImpl")
	private WechatDao wechatDaoImpl;
	private ResourceBundle bundler = ResourceBundle
			.getBundle("resources/config");

	@Override
	public WechatAccountEntity getWechatAccountEntity() throws Exception {
		// TODO Auto-generated method stub
		String appid = bundler.getString("wechatappid");
		WechatAccountEntity wechatAccountEntity = wechatDaoImpl
				.getWechatAccountEntity(appid);
		AccessToken accessToken = WeixinUtil.getAccessToken(
				wechatAccountEntity.getAccountappid(),
				wechatAccountEntity.getAccountappsecret());
		wechatAccountEntity.setAccountaccesstoken(accessToken.getToken());
		return wechatAccountEntity;
	}

	@Override
	public void addnewitem(String mediaid, String author, String title,
			String contentsourceurl, String content, String digest,
			Integer showcoverpic) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadMapnews(List<String> newsitemids) throws Exception {
		// TODO Auto-generated method stub

	}

}
