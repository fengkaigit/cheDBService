package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.BuynoteDao;
import com.chedb.model.ModelBusinote;
import com.chedb.service.BuynoteService;
import com.chedb.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

@Service("buynoteServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class BuynoteServiceImpl implements BuynoteService {
	@Resource(name = "buynoteDaoImpl")
	private BuynoteDao buynoteDao;

	@Override
	public List<ModelBusinote> queryBuynoteList(String classId, String status, String providerId
			, String userId, String startTime, String endTime)
			throws Exception {
		return this.buynoteDao.queryBuynoteList(classId, status, providerId, userId, startTime, endTime);
	}
	
	@Override
	public List<ModelBusinote> queryBuynoteByUserId(String userId, Integer page)
			throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBuynoteListByUser(userId, page);
	}

	@Override
	public List<ModelBusinote> queryBuynoteByProviderId(String providerId,
			Integer page) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBuynoteListByProviderId(providerId, page);
	}

	@Override
	public ModelBusinote queryBusinoteById(String busiNo) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBusinoteById(busiNo);
	}

	@Override
	public void updateBusinoteInfo(String busiNo, String newStatus, String score)
			throws Exception {
		// TODO Auto-generated method stub
		this.buynoteDao.updateBusinoteInfo(busiNo, newStatus, score);

	}

	@Override
	public boolean buyService(String buynoteStr) throws Exception {
		// TODO Auto-generated method stub
		ModelBusinote buynote = GsonUtil.getGson().fromJson(buynoteStr,
				new TypeToken<ModelBusinote>() {
				}.getType());

		// 添加 一条购买记录
		if (buynoteDao.addBusi(buynote) == true) {
			return true;
		}
		return false;
	}

	@Override
	public List<ModelBusinote> paingqueryBuynote(String userId,
			Integer pageindex, Integer type) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.paingqueryBuynote(userId, pageindex, type);
	}

	@Override
	public Integer rowcount(String userId, Integer type) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.rowcount(userId, type);
	}

}
