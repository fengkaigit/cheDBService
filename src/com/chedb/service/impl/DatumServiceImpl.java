package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.DatumDao;
import com.chedb.model.ModelDatum;
import com.chedb.model.ModelDatumLabel;
import com.chedb.service.DatumService;

@Service("datumServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DatumServiceImpl implements DatumService {
	@Resource(name = "datumDaoImpl")
	private DatumDao datumDao;

	@Override
	public List<ModelDatumLabel> getDatumLabelListByPanretId(int hasDatum, String pId) throws Exception {
		// TODO Auto-generated method stub
		return datumDao.getDatumLabelListByPanretId(hasDatum, pId);
	}
	
	@Override
	public List<ModelDatum> getDatumListByLabelId(String labelId, int pageNum) throws Exception {
		// TODO Auto-generated method stub
		return datumDao.getDatumListByLabelId(labelId, pageNum);
	}
}
