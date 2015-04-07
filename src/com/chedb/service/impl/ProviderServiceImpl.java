package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ProviderDao;
import com.chedb.dao.ProviderItemDao;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelProviderItem;
import com.chedb.service.ProviderService;

@Service("providerServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ProviderServiceImpl implements ProviderService {
	@Resource(name = "providerDaoImpl")
	private ProviderDao providerDao;
	@Resource(name = "providerItemDaoImpl")
	private ProviderItemDao providerItemDao;

	@Override
	public List<ModelProvider> getProviderList(String level,
			String strSysItemList, String sort, String priceStart,
			String priceEnd, String latitude, String longitude)
			throws Exception {
		// TODO Auto-generated method stub
		List<ModelProvider> providers = providerDao
				.getProviderList(level, strSysItemList, sort, priceStart,
						priceEnd, latitude, longitude);
		// 获取符合条件的每个商家的服务项目
		for (ModelProvider modelProvider : providers) {
			List<ModelProviderItem> items = providerItemDao
					.getProviderItemByProviderId(1,
							modelProvider.getProviderId(), strSysItemList,
							priceStart, priceEnd);
			modelProvider.setProviderItems(items);
		}
		return providers;
	}

	@Override
	public List<ModelProvider> getProviderListBySearch(String strSearch)
			throws Exception {
		// TODO Auto-generated method stub
		List<ModelProvider> providers = this.providerDao
				.getProviderListBySearch(strSearch);
		for (ModelProvider modelProvider : providers) {
			List<ModelProviderItem> items = providerItemDao
					.getProviderItemByProviderId(1,
							modelProvider.getProviderId(), null, null, null);
			modelProvider.setProviderItems(items);
		}
		return providers;
	}

	@Override
	public String getImgIdAndUpdateImgList(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.getImgIdAndUpdateImgList(providerId);
	}

	@Override
	public String editImgSpace(String providerId, String oldImgIdList,
			String imgId, String edittype) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.editImgSpace(providerId, oldImgIdList, imgId,
				edittype);
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.delete(id);
	}

	@Override
	public boolean add(ModelProvider t) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.add(t);
	}

	@Override
	public boolean updateScore(String providerId, String score)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.updateScore(providerId, score);
	}

	@Override
	public boolean update(String providerId, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.update(providerId, type, content);
	}

	@Override
	public boolean appendBusinessCount(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.appendBusinessCount(providerId);
	}

	@Override
	public boolean appendBrowseCount(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.appendBrowseCount(providerId);
	}

	@Override
	public ModelProvider queryProviderById(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.queryProviderById(providerId);
	}

}
