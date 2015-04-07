package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelSysItem;

public interface SystemItemService {
	public boolean delete(int id) throws Exception;

	public boolean update(String userNo, String type, String content)
			throws Exception;

	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelSysItem> getSysItemClass(String level) throws Exception;

	/**
	 * ��ѯ�̼ҵķ�����Ŀ����Ӧ��ϵͳ��Ŀ
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelSysItem> getProviderSysItem(String providerId)
			throws Exception;

	/**
	 * ��ѯϵͳ��Ŀ
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelSysItem> getSysItemByClassId(String classId)
			throws Exception;

	/**
	 * ��ѯϵͳ��Ŀ
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public ModelSysItem getSysItemById(String itemId) throws Exception;
}
