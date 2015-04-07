package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ServiceClassDao;
import com.chedb.model.ModelServiceClass;
import com.chedb.service.ServiceClassService;

@Service("serviceClassServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ServiceClassServiceImpl implements ServiceClassService {
	@Resource(name = "serviceClassDaoImpl")
	private ServiceClassDao serviceClassDao;

	@Override
	public ModelServiceClass queryServiceClassById(String classId)
			throws Exception {
		// TODO Auto-generated method stub
		return serviceClassDao.queryServiceClassById(classId);
	}

	@Override
	public List<ModelServiceClass> queryAllServiceClass() throws Exception {
		return serviceClassDao.queryAllServiceClass();
	}
}
