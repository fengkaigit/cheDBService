package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelServiceClass;

//
public interface ServiceClassDao {
	
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId)throws Exception ;
	
	
	public List<ModelServiceClass> queryAllServiceClass() throws Exception ;
}
