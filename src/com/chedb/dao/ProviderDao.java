package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelProvider;

//
public interface ProviderDao {

	public boolean delete(int id) throws Exception;

	public boolean add(ModelProvider t) throws Exception;

	public boolean updateScore(String providerId, String score)
			throws Exception;

	public boolean update(String providerId, String type, String content)
			throws Exception;

	public boolean appendBusinessCount(String providerId) throws Exception;

	public boolean appendBrowseCount(String providerId) throws Exception;

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelProvider queryProviderById(String providerId) throws Exception;

	//
	// /**
	// *
	// * @param userId
	// * @return
	// */
	// public ModelProvider getProviderByUserId(String userId){
	// String sql = "select * from provider where id = '" + userId + "'";
	//
	// return getProvider(sql);
	// }
	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelProvider> getProviderListBySearch(String strSearch)
			throws Exception;

	public String getImgIdAndUpdateImgList(String providerId) throws Exception;

	/**
	 * ����ͼƬ��λ�ã�����ɾ��ͼƬ
	 * 
	 * @param providerId
	 * @param oldImgIdList
	 * @param imgId
	 *            ͼƬID����ӦͼƬ��
	 * @param edittype
	 *            1���ƣ�0ɾ����-1ǰ��
	 * @return
	 */
	public String editImgSpace(String providerId, String oldImgIdList,
			String imgId, String edittype) throws Exception;

	// ����λ������

	/**
	 * ��ѯ�̼�
	 * 
	 * @param level
	 *            �̼����1Ϊ�����̼ң�2Ϊ����̼�
	 * @param strSysItemList
	 *            ϵͳ��Ŀ������ʹ��
	 * @param sort
	 *            ����ʽ��1�����룬2���ڱ���3���۸��С����
	 * @param priceStart
	 *            �۸���Сֵ
	 * @param priceEnd
	 *            �۸����ֵ
	 * @param latitude
	 *            ,longitude ��ѯ�û���ǰ�ľ�γ�ȣ����ڸ��ݾ�������ʱʹ��
	 * @return ��ѯ�����̼��б�
	 */
	public List<ModelProvider> getProviderList(String level,
			String strSysItemList, String sort, String priceStart,
			String priceEnd, String latitude, String longitude)
			throws Exception;

}
