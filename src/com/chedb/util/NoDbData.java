package com.chedb.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.chedb.model.ModelProvider;
import com.chedb.model.ModelProviderItem;
import com.chedb.model.ModelPublicItem;
import com.chedb.model.ModelSysItem;


public class NoDbData {
	
	/**
	 * ��ѯϵͳ�ṩ�ķ�������
	 * @param level �̼Ҽ����̼ҷ�Ϊ���࣬1Ϊ������2Ϊ���������
	 * @return
	 */
	static public List<ModelSysItem> querySysItemClassList(int level) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("������ϴ");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("��������");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("�ⲿװ��");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("��̥");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("����");
		list.add(label);
		
		return list;
	}
	
	/**
	 * ��ѯĳ���������µķ���
	 * @param parent ����
	 * @return
	 */
	static public List<ModelSysItem> querySysItemByClassId(String itemClassId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		if (itemClassId.equals("101")) {
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101002");
		label.setParentId("101");
		label.setTitle("����ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101003");
		label.setParentId("101");
		label.setTitle("ȫ�Զ�����ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101004");
		label.setParentId("101");
		label.setTitle("�������");
		list.add(label);
		} else if (itemClassId.equals("102")) {
		label = new ModelSysItem();
		label.setLabelId("102001");
		label.setParentId("102");
		label.setTitle("�������");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102002");
		label.setParentId("102");
		label.setTitle("�����޸�");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102003");
		label.setParentId("102");
		label.setTitle("�³�����");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102004");
		label.setParentId("102");
		label.setTitle("�������");
		list.add(label);
		
		} else if (itemClassId.equals("103")) {
		label = new ModelSysItem();
		label.setLabelId("103001");
		label.setParentId("103");
		label.setTitle("ȫ����Ĥ");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103002");
		label.setParentId("103");
		label.setTitle("����ȫĤ");
//		label.setSelect(1);
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103003");
		label.setParentId("103");
		label.setTitle("��ʹ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103004");
		label.setParentId("103");
		label.setTitle("����װ��");
		list.add(label);
		
		} else if (itemClassId.equals("104")) {
		label = new ModelSysItem();
		label.setLabelId("104001");
		label.setParentId("104");
		label.setTitle("��̥");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104002");
		label.setParentId("104");
		label.setTitle("���ֶ�λ");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104003");
		label.setParentId("104");
		label.setTitle("��̥");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104004");
		label.setParentId("104");
		label.setTitle("���");
		list.add(label);
		
		} else if (itemClassId.equals("105")) {
		label = new ModelSysItem();
		label.setLabelId("105001");
		label.setParentId("105");
		label.setTitle("С����");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105002");
		label.setParentId("105");
		label.setTitle("����");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105003");
		label.setParentId("105");
		label.setTitle("��������ϴ");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105004");
		label.setParentId("105");
		label.setTitle("����");
		list.add(label);
		}
		return list;
	}
	
	static public List<ModelSysItem> querySysItemById(String itemClassId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
//		if (itemClassId.equals("101")) {
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("ϴ��");
		list.add(label);
		return list;
		}
		
	/**
	 * ������۱�ǩ
	 * @param level �̼Ҽ���
	 * @return
	 */
	static public List<ModelSysItem> queryAppraiseLabel(int level) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("����̬�Ⱥ�");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("��������ϸ������");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("�ϰ��˺ã��۸������");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("�۸�ͣ������");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("�ط�����");
		list.add(label);
		
		return list;
	}
	
	/**
	 * ��ó�����ĳ���̼ҵ����ۣ���������ƴ��������û�������۵Ĳ�����
	 * @param providerId �̼�ID
	 * @return
	 */
	static public List<ModelSysItem> queryProviderAppraiseLabel(String providerId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("����̬�Ⱥã�12�ˣ�");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("��������ϸ�����棨10�ˣ�");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("�ϰ��˺ã��۸��������6�ˣ�");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("�۸�ͣ�����ã�3�ˣ�");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("�ط����ң�1�ˣ�");
		list.add(label);
		
		return list;
	}

	/**
	 * �����̼�id��ѯ�̼�¼��ķ������ࣨ��ϵͳ�ķ����ǩ��ÿ�����������¿����ж��������Ŀ��
	 * @param providerId
	 * @return
	 */
	static public List<ModelSysItem> queryProviderSysItem(String providerId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101002");
		label.setParentId("101");
		label.setTitle("����ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101003");
		label.setParentId("101");
		label.setTitle("ȫ�Զ�����ϴ��");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101004");
		label.setParentId("101");
		label.setTitle("�������");
		list.add(label);
		
		return list;
	}
	
	/**
	 * �����̼�id���û�ѡ�񣬲�ѯ�̼ҵķ�����Ŀ
	 * @param providerId
	 * @param appItem
	 * @return
	 */
	static public List<ModelSysItem> queryProviderItemBySysItemId(String providerId, String sysItemId) {
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();		
		
		List<String> labellist = new ArrayList<String>();
//		ModelSysItem item = new ModelSysItem();
//		item.setLabelId(sysItemId);
		labellist.add(sysItemId);
		List<ModelProviderItem> itemlist = queryProviderItemByProviderId(providerId, labellist);
		
		for (int i=0; i<itemlist.size(); i++) {
			ModelSysItem lbl = new ModelSysItem();
			lbl.setLabelId(itemlist.get(i).getItemId());
			lbl.setParentId(sysItemId);
	//		lbl.setSelect(1);
			lbl.setTitle(itemlist.get(i).getTitle());
			list.add(lbl);
			
		}
		return list;
	}
	
	/**
	 * ��ѯ�̼�
	 * @param appItemList �û����õĹ������������Ϊ�գ�����Ϊû�������κ�����
	 * @param sort ����0������1�����룻2���ڱ���3���۸�
	 * @param page
	 * @return
	 */
	static public List<ModelProvider> queryProviderList(int level, List<String> appItemIdList, int sort) {
		List<ModelProvider> list = new ArrayList<ModelProvider>();
//		if (labellist!=null) {
//			for (int i=0; i<labellist.size(); i++) {
//			Log.v("PublicData", "queryProviderList(), Select Label("+i+") = "+labellist.get(i).getTitle());
//			}
//		}
		for (int i=0; i<100; i++) {
			ModelProvider item = new ModelProvider();
//			item.setItemId(""+i);
			item.setTitle("���������̼�-"+i);
			item.setSummary("���ʣ���ҵ��ֵ֤��������ҵ��\n��ģ����˾������03�꣬����Ա��38�ˡ�\n������ּ��Ϊ������ṩ�����ʡ����ݡ��ȫ����������ά�޷���");
			item.setAddr("���ͺ����л���������·32�ţ��κ̴��ö��棬XX��Ƶ��Աߡ�");
			item.setRemark("˵����Ϣ��");
			item.setPhone("15247126745");
			item.setBrowse(i*11);
			item.setBusiness(i*7);
			if (i%4==0) 
				item.setLiansuo(1);
			if (i%4==1) 
				item.setS4(1);
			if (i%4==2) 
				item.setRenzheng(1);
//			item.setPrice(i*110);
//			item.setDiscount(9);
			// 40.853174; y=111.717143
			double rx = Math.random()-0.6;
			double ry = Math.random()-0.6;
			double x = rx*0.08;
			double y = ry*0.1;
			item.setLatitude(40.853174 + x);
			item.setLongitude(111.717143 + y);
			
			item.setScore((float)4.5);
			item.setScoreCount(38);
			list.add(item);
		}
		return list;
	}
	
	/**
	 * �����û�������в�ѯ
	 * @param search ��ѯ������ֻ����������Ϊ���̼Һţ��к�����Ϊ�̼�����
	 * @param page
	 * @return
	 */
	static public List<ModelProvider> queryProviderListBySearch(String searchStr) {
		List<ModelProvider> list = new ArrayList<ModelProvider>();
//		return list;
		for (int i=1; i<14; i++) {
			ModelProvider item = new ModelProvider();
			item.setProviderId((i+3000000)+"");
			item.setTitle("�������������̼�-"+i);
//			item.setSummary("���ʣ���ҵ��ֵ֤��������ҵ��\n��ַ�����ͺ��غ���·32�Ż����Ƶ궫���\n������ּ��Ϊ������ṩ�����ʡ����ݡ��ȫ����������ά�޷���");
			item.setAddr("���ͺ����л���������·32�ţ��κ̴��ö��棬XX��Ƶ��Աߡ�");
			item.setPhone("15247126745");
			item.setBrowse(i*11);
			item.setBusiness(i*7);
			item.setScore((float)4.6);
			item.setScoreCount(38);

			// 40.853174; y=111.717143
			double rx = Math.random()-0.6;
			double ry = Math.random()-0.6;
			double x = rx*0.08;
			double y = ry*0.1;
			item.setLatitude(40.853174 + x);
			item.setLongitude(111.717143 + y);
			
			list.add(item);
		}
		return list;
	}
	
	/**
	 * �����û�ID��ѯ���û�����Ӧ���̼����ݣ�ֻ��7λ���̼ҺŲ����̼����ݣ�һ���û�ֻ��Ӧһ���̼ң������û�û�ж�Ӧ���̼�
	 * @param userId �û�ID
	 * @return
	 */
	static public ModelProvider queryProviderByUserId(String userId) {
		ModelProvider info = new ModelProvider();
		
		info.setProviderId("10201");
		info.setTitle("������������ά�޷���");
		info.setScore((float)4.6);
		info.setScoreCount(125);
		info.setBrowse(436);
		info.setBusiness(231);
		info.setSummary("���ʣ���ҵ��ֵ֤��������ҵ��\n��ַ�����ͺ��غ���·32�Ż����Ƶ궫���\n������ּ��Ϊ������ṩ�����ʡ����ݡ��ȫ����������ά�޷���");
		info.setAddr("���ͺ����л���������·32�ţ��κ̴��ö��棬XX��Ƶ��Աߡ�");
		info.setPhone("15247126745");
		return info;
	}
	
	/**
	 * �����̼�ID��ѯ�̼�����
	 * @param providerId
	 * @return
	 */
	static public ModelProvider queryProviderById(String providerId) {
		ModelProvider info = new ModelProvider();
		
		info.setProviderId(providerId);
		info.setTitle("������������ά�޷���");
		info.setScore((float)4.6);
		info.setScoreCount(125);
		info.setBrowse(436);
		info.setBusiness(231);
		info.setSummary("���ʣ���ҵ��ֵ֤��������ҵ��\n��ַ�����ͺ��غ���·32�Ż����Ƶ궫���\n������ּ��Ϊ������ṩ�����ʡ����ݡ��ȫ����������ά�޷���");
		info.setAddr("���ͺ����л���������·32�ţ��κ̴��ö��棬XX��Ƶ��Աߡ�");
		info.setPhone("15247126745");
		return info;
	}
	
	/**
	 * ������Ŀid��ѯ��Ŀ����
	 * @param itemId
	 * @return
	 */
	static public ModelProviderItem queryProviderItemById(String itemId) {
		ModelProviderItem item = new ModelProviderItem();
		item.setItemId("10000");
		item.setTitle("ĳ����Ŀ-��̥���ֶ�λ");
		item.setSummary("������ת���֡�ת��ں�ǰ������֮��İ�װ����һ�������λ�ã����־���һ�����λ�õİ�װ����ת���ֶ�λ��Ҳ��ǰ�ֶ�λ��");
//		item.setBrowse(11);
		item.setBusiness(7);
		item.setPrice(110);
		item.setPriceOld(110+100);
		
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		String d1=fnum.format(item.getPrice()); 
		String d2 = fnum.format(item.getPriceOld()); 
//		item.setStrPrice(d1 + "Ԫ��ԭ��" + d2+"Ԫ��");
		
//		item.setScore((float)4.6);
//		item.setScoreCount(38);
		
		return item;
	}
	
	/**
	 * �����û�ѡ��Ĺ���������ĳ���̼��в�ѯ��Ŀ
	 * @param providerId
	 * @param labellist
	 * @return
	 */
	static public List<ModelProviderItem> queryProviderItemByProviderId(String providerId, List<String> sysItemIdList) {
		List<ModelProviderItem> list = new ArrayList<ModelProviderItem>();

		int n = 7;
		if (sysItemIdList!=null) {
//			if (labellist)
			n = 3;
		}
		for (int i=1; i<n; i++) {
			ModelProviderItem item = new ModelProviderItem();
			item.setItemId(""+i);
			item.setTitle("���ֶ�λ-"+i);
			item.setSummary("������ת���֡�ת��ں�ǰ������֮��İ�װ����һ�������λ�ã����־���һ�����λ�õİ�װ����ת���ֶ�λ��Ҳ��ǰ�ֶ�λ��");
//			item.setBrowse(i*11);
			item.setBusiness(i*7);
			item.setPrice(i*110);
			item.setPriceOld(i*110+100);
			
			DecimalFormat fnum = new DecimalFormat("##0.00"); 
			String d1=fnum.format(item.getPrice()); 
			String d2 = fnum.format(item.getPriceOld()); 
//			item.setStrPrice(d1 + "Ԫ��ԭ��" + d2+"Ԫ��");
			
//			item.setScore((float)4.6);
//			item.setScoreCount(38);
			list.add(item);
		}
		return list;
	}
	
	/**
	 * ��ѯ��Ŀ����ϸ��Ϣ
	 * @param item
	 * @return
	 */
	static public ModelProviderItem queryProviderItemDetailById(String itemId) {
		ModelProviderItem item = new ModelProviderItem();
//		if (item.getItemId() != null) {
//			detail.setItemId(id);
			item.setRemark("������ʹ�úܳ�ʱ����û����ַ���ת����ء���������ƫ������������λ������̥����ĥ�𣬲�״ĥ�𣬿�״ĥ��ƫĥ�Ȳ�����ĥ���Լ��û���ʻʱ������Ư����������ҡ�ڵ��������ʱ����Ӧ�ÿ��Ǽ��һ�³��ֶ�λֵ�������Ƿ�ƫ��̫�࣬��ʱ��������\n\n��������Ҫÿһ��ȥ��һ����̥��λ����һ������һ�ζ�λ������ó�ʱ��ϳ������ǳ��⳵������֮�����ð���������¾���һ�ζ�λ��\n\nǰ�ֶ�λ������������ǡ���������ǡ�ǰ������Ǻ�ǰ��ǰ���ĸ����ݡ����ֶ�λ������������Ǻ��������ǰ��������ǰ�ֶ�λ�ͺ��ֶ�λ������˵�г��ֶ�λ��Ҳ���ǳ�˵�����ֶ�λ�����ֶ�λ��������ʹ���������ȶ���ֱ����ʻ��ת����㣬��������������ʻ����̥��ת�������ĥ��");
//		} else {
//			item.setInfo1("���������������Ŀ����ϸ��Ϣ����ʹ�õ�ԭ���ϡ���������ȡ���������Ϣ�����ó������ģ��������θС���");
			
//		}
		return item;
//		return detail;
	}
	
	/**
	 * �����û�id��ѯ���û���ҵ���¼
	 * @param userId
	 * @param page
	 * @return
	 */
//	static public List<ModelBuynote> queryBuynoteByUserId(String userId, int page) {
//		List<ModelBuynote> list = new ArrayList<ModelBuynote>();
//		if (userId.equals("999999999")) {
////			return list;
//		}
//		int n = 10;
//		if (page==3) {
//			n = 0;
//		}
//		for (int i=0; i<n; i++) {
//			ModelBuynote note = new ModelBuynote();
//			note.setItemId("102012"+(i+page*10));
//			note.setItemName("��Ŀ����-"+i);
//			note.setProviderId("10101"+i);
//			note.setProviderName("�̼�����"+i);
//			if (i%3==0)
//				note.setScore(4);
//			note.setPrice(212.00);
//			note.setDate("2014-05-12 16:30");
//			note.setUserId("1023220"+i);
//			note.setUserName("�����ǳ�"+i);
//			list.add(note);
//		}
//		return list;
//	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	static public List<ModelPublicItem> queryStringList(int type) {
		List<ModelPublicItem> list = new ArrayList<ModelPublicItem>();
		ModelPublicItem single;
		if (type == 1) {
			// Ͷ����
//			single = new ModelPublicItem("101", "�̼����Ӽ۸���թ���շѲ�����ע����");
			single = new ModelPublicItem();
			single.setId("101");
			single.setTitle("�̼����Ӽ۸���թ���շѲ�����ע����");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("102");
			single.setTitle("�����˵ķ�����ĿҲ��������д��");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("103");
			single.setTitle("�̼Ҳ����ڣ����ǵ�ͼ��Ǵ���");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("104");
			single.setTitle("����̬�ȶ���");
			list.add(single);
//			list.add(new ModelSingleTitle("101", "�̼����Ӽ۸���թ���շѲ�����ע����"));
//			list.add(new ModelSingleTitle("102", "�����˵ķ�����ĿҲ��������д��"));
//			list.add(new ModelSingleTitle("103", "�̼Ҳ����ڣ����ǵ�ͼ��Ǵ���"));
//			list.add(new ModelSingleTitle("104", "����̬�ȶ���"));
		} else if (type == 2) {
			// �������
			single = new ModelPublicItem();
			single.setId("201");
			single.setTitle("���������뿴����Ϣ");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("202");
			single.setTitle("�ٴ���ۿ���Ҳ����ȥ");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("203");
			single.setTitle("����̫���������ҵ�");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("204");
			single.setTitle("�����Ҳ̫����");
			list.add(single);
//			list.add(new ModelSingleTitle("201", "���������뿴����Ϣ"));
//			list.add(new ModelSingleTitle("202", "�ٴ���ۿ���Ҳ����ȥ"));
//			list.add(new ModelSingleTitle("203", "����̫���������ҵ�"));
//			list.add(new ModelSingleTitle("204", "�����Ҳ̫����"));
		} else if (type == 3) {
			single = new ModelPublicItem();
			single.setId("301");
			single.setTitle("����ΰ��ҵķ������Ϣ¼��ȥ�����ó���������");
			list.add(single);
			single = new ModelPublicItem();
			single.setId("302");
			single.setTitle("���Ϲ�����ʲô��˼��");
			list.add(single);
			single = new ModelPublicItem();
			single.setId("303");
			single.setTitle("���ǳ������ҡ��Ϲ����ˣ���α�֤����������");
			list.add(single);
//			list.add(new ModelSingleTitle("301", "����ΰ��ҵķ������Ϣ¼��ȥ�����ó���������"));
//			list.add(new ModelSingleTitle("302", "���Ϲ�����ʲô��˼��"));
//			list.add(new ModelSingleTitle("303", "���ǳ������ҡ��Ϲ����ˣ���α�֤����������"));
		}
		return list;
	}
	
}
