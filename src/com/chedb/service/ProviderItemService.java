package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelProviderItem;
import com.chedb.model.ModelSysItem;

public interface ProviderItemService {
	public boolean add(ModelProviderItem item) throws Exception;

	public boolean delete(String itemId) throws Exception;

	public boolean update(String itemId, String type, String content)
			throws Exception;

	public boolean updatePrice(String itemId, String price, String priceOld)
			throws Exception;

	public boolean appendBusinessCount(String itemId) throws Exception;

	/**
	 * �ƶ���Ŀλ�ã�������ǰ��Ŀ��Ŀ����Ŀ������ֵ
	 * 
	 * @param type
	 *            -1���ƣ�1����
	 * @param itemId
	 *            ��ǰ��Ŀ
	 * @param targetItemId
	 *            �ƶ�Ŀ����Ŀ
	 * @return
	 */
	public boolean moveItem(String itemId, String targetItemId)
			throws Exception;

	/**
	 * mode : 0��ʾ��ѯ����������ȫ����1��ʾ��ѯǰ���������2��ʾ��ѯ������������
	 */
	public List<ModelProviderItem> getProviderItemByProviderId(int mode,
			String providerId, String strSysItemList, String priceStart,
			String priceEnd) throws Exception;

	/**
	 * ��ѯ�̼ҵ�ĳ��ϵͳ��Ŀ���м���������Ŀ
	 * 
	 * @param providerId
	 * @param sysItemId
	 * @return
	 */
	public List<ModelSysItem> getProviderItemBySysItemId(String providerId,
			String sysItemId) throws Exception;

	public ModelProviderItem getProviderItemById(String itemId)
			throws Exception;

	public ModelProviderItem getProviderItemDetailById(String itemId)
			throws Exception;
}
