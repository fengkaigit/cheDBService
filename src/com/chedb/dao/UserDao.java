package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelUser;
import com.chedb.model.WechatUserModel;

//
public interface UserDao {

	public boolean delete(int id) throws Exception;

	//
	/**
	 * ��ȡһ���µ��û�ID
	 * 
	 * @param type
	 *            ���û����ͣ�1Ϊ�����û���2Ϊ�����û���3Ϊ����̼��û�
	 * @return �µ��û�ID
	 *         ��õ���ID����ע�ᣬ���ܿ��ܲ���ע�ᡣ�µ�ID��user����״̬Ϊ0������ע���״̬Ϊ1����ID���ɺ��Сʱ��û������ע����ɱ��ظ�ʹ��
	 */
	public String getNewId(int type) throws Exception;

	public boolean register(ModelUser user) throws Exception;

	public boolean update(String userNo, String type, String content)
			throws Exception;

	public ModelUser getUserByPhone(String phone) throws Exception;

	public ModelUser getUser(String userNo) throws Exception;

//	public List<ModelCar> getChoosedCarByUser(String userId) throws Exception;
	public List<ModelCarYear> getChoosedCarItemByUser(String userId, String type)throws Exception;

	public boolean addChoosedCarByAppId(String carId, String appId)
			throws Exception;

	// 1001:�򿪳���
	// 1002:�˳�����
	// 2001��ע��
	// 2002����¼
	// 3001��ѡ���³���
	// 3002��������
	public boolean addUserConfig(String operType, String appId,
			String operContent) throws Exception;

	/**
	 * У���Ƿ��Ѿ���
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean isBind(String username) throws Exception;

	/**
	 * ���û�
	 * 
	 * @param openid
	 * @param username
	 * @throws Exception
	 */
	public void bindUser(WechatUserModel user) throws Exception;
}
