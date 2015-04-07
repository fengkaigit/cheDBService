package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelDatum;
import com.chedb.model.ModelDatumLabel;

public interface DatumService {

	/**
	 * 
	 * @param breadid
	 * @return
	 * @throws Exception
	 */
	public List<ModelDatumLabel> getDatumLabelListByPanretId(int hasDatum, String pId)
			throws Exception;

	/**
	 * 
	 * @param labelId
	 * @return
	 * @throws Exception
	 */
	public List<ModelDatum> getDatumListByLabelId(String labelId, int pageNum)
			throws Exception;
}
