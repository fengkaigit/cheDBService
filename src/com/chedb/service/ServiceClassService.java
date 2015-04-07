package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelServiceClass;

//
public interface ServiceClassService {
	
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId)throws Exception ;
	
	
	public List<ModelServiceClass> queryAllServiceClass() throws Exception ;
}
