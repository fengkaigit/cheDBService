package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.UserDao;
import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelUser;
import com.chedb.model.WechatUserModel;
import com.chedb.service.UserService;

@Service("userServiceimpl")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Resource(name = "userDaoImpl")
	private UserDao UserDao;

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.delete(id);
	}

	@Override
	public String getNewId(int type) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getNewId(type);
	}

	@Override
	public boolean register(ModelUser user) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.register(user);
	}

	@Override
	public boolean update(String userNo, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return UserDao.update(userNo, type, content);
	}

	@Override
	public ModelUser getUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getUserByPhone(phone);
	}

	@Override
	public ModelUser getUser(String userNo) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getUser(userNo);
	}

	@Override
	public ModelUser checkUser(String userNo, String passwd) throws Exception {
		// TODO Auto-generated method stub
//		return UserDao.checkUser(userNo, passwd);
		return null;
	}
	
	@Override
	public List<ModelCarYear> getChoosedCarItemByUser(String userId, String type) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getChoosedCarItemByUser(userId, type);
	}

	@Override
	public boolean addChoosedCarByAppId(String carId, String appId)
			throws Exception {
		// TODO Auto-generated method stub
		return UserDao.addChoosedCarByAppId(carId, appId);
	}

	@Override
	public boolean addUserConfig(String operType, String appId,
			String operContent) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.addUserConfig(operType, appId, operContent);
	}

	@Override
	public void bindUser(String username, String openid) throws Exception {
		// TODO Auto-generated method stub
		// 1. 判断是否已经绑定过，绑定过则不让绑定
		WechatUserModel user = new WechatUserModel();
		user.setUsername(username);
		user.setOpenid(openid);
		if (this.UserDao.isBind(username)) {
			throw new Exception("用户已经绑定。");
		} else {
			this.UserDao.bindUser(user);
		}
	}

}
