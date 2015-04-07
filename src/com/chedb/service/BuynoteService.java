package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelBusinote;

public interface BuynoteService {

	/**
	 * 管理员查询业务记录
	 * @param classId
	 * @param status
	 * @param providerId
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<ModelBusinote> queryBuynoteList(String classId, String status, String providerId
			, String userId, String startTime, String endTime)
			throws Exception;
	
	public List<ModelBusinote> queryBuynoteByUserId(String userId, Integer page)
			throws Exception;

	/**
	 * 查询业务订单记录
	 * 
	 * @param userId
	 * @param pageindex
	 * @return
	 * @throws Exception
	 */
	public List<ModelBusinote> paingqueryBuynote(String userId,
			Integer pageindex,Integer type) throws Exception;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer rowcount(String userId,Integer type) throws Exception;

	/**
	 * 
	 * @param providerId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ModelBusinote> queryBuynoteByProviderId(String providerId,
			Integer page) throws Exception;

	/**
	 * 
	 * @param busiNo
	 * @return
	 * @throws Exception
	 */
	public ModelBusinote queryBusinoteById(String busiNo) throws Exception;

	public void updateBusinoteInfo(String busiNo, String newStatus, String score)
			throws Exception;

	public boolean buyService(String buynoteStr) throws Exception;
}
