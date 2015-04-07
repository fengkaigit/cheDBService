package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelPublicItem;

public interface AppInfoService {
	public List<ModelPublicItem> getAppinfoListByType(String type)
			throws Exception;

//	public ModelVersionInfo getgetNowVersion(String appId) throws Exception;

	public boolean commitComplain(String type,String strSingleTitleList, String userId,
			String text) throws Exception;

	public boolean commitFreeback(String type,String strSingleTitleList, String userId,
			String text) throws Exception;

}
