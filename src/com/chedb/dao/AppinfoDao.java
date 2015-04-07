package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelPublicItem;

public interface AppinfoDao {
	/**
	 * 
	 * @param type
	 * @param strSingleTitleList
	 * @param text
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean userCommit(String type, String strSingleTitleList,
			String text, String userId) throws Exception;

	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public String getAppinfoContentById(String id) throws Exception;

	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<ModelPublicItem> getAppinfoListByType(String type)
			throws Exception;

}
