package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelProvider;
import com.chedb.model.ModelService;
import com.chedb.model.ModelServiceProvider;
import com.chedb.model.ModelUserAppraise;

//
public interface ServiceDao {

	public ModelService getServiceInfo(String serviceId) throws Exception;

	public List<ModelServiceProvider> getServiceListByProvider(String providerId)
			throws Exception;
	
	public boolean saveServiceProviderLink(List<String> serviceIdlist, String serviceClassId, String providerId)
			throws Exception;
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception;

	public int getProviderCountByServiceId(String serviceId) throws Exception;

	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude)
			throws Exception;

	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) throws Exception;

	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) throws Exception;

	public List<ModelUserAppraise> getAppraiseListByServiceId(
			String serviceClassId, String serviceId, String start, String count)
			throws Exception;
     /**
      * 
      * @param classId
      * @param carId
      * @param yearid
      * @param moverange
      * @return
      * @throws Exception
      */
	public List<ModelService> recommendService(String classId, String carId,
			String yearid, Integer moverange) throws Exception;

}
