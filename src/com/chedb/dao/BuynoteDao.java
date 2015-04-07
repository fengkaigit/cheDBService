package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelBusinote;

public interface BuynoteDao {

	public boolean addBusi(ModelBusinote t) throws Exception;

	public boolean updateBusinoteInfo(String busiNo, String newStatus,
			String score) throws Exception;

	public List<ModelBusinote> queryBuynoteList(String classId, String status, String providerId
			, String userId, String startTime, String endTime)
			throws Exception;
	
	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param
	 * @return
	 */
	public List<ModelBusinote> getBuynoteListByUser(String userId, int page)
			throws Exception;

	public ModelBusinote getBusinoteById(String no) throws Exception;

	public List<ModelBusinote> getBuynoteListByProviderId(String providerId,
			int page) throws Exception;

	/**
	 * 
	 * @param userId
	 * @param pageindex
	 * @return
	 * @throws Exception
	 */
	public List<ModelBusinote> paingqueryBuynote(String userId,
			Integer pageindex, Integer type) throws Exception;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer rowcount(String userId, Integer type) throws Exception;

}
