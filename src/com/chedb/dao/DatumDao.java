package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelDatum;
import com.chedb.model.ModelDatumLabel;

//
public interface DatumDao {

	public List<ModelDatumLabel> getDatumLabelListByPanretId(int hasDatum, String pId) throws Exception;
	
	public List<ModelDatum> getDatumListByLabelId(String labelId, int pageNum) throws Exception;

}
