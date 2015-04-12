package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.ProviderItemDao;
import com.chedb.model.ModelProviderItem;
import com.chedb.model.ModelSysItem;
import com.chedb.util.ServerConfig;

@Repository("providerItemDaoImpl")
public class ProviderItemDaoImpl implements ProviderItemDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean add(ModelProviderItem item) {
		int maxSort = 1;
		String maxSortSql = "select max(sort) as maxsort from provider_item where provider_id='"
				+ item.getProviderId() + "'";
		String maxItemId = item.getProviderId() + item.getSysItemId() + "001";
		String maxItemIdSql = "select max(id) as maxid from provider_item where provider_id='"
				+ item.getProviderId()
				+ "' and sys_item_id='"
				+ item.getSysItemId() + "'";

		String nowMaxId = null;
		nowMaxId = this.jdbcTemplate.queryForObject(maxItemIdSql,
				java.lang.String.class);
		if (nowMaxId != null) {
			maxItemId = (Long.parseLong(nowMaxId) + 1) + "";
		}
		String maxSortStr = this.jdbcTemplate.queryForObject(maxSortSql,
				java.lang.String.class);
		if (maxSortStr!=null) {
			maxSort = Integer.parseInt(maxSortStr) +1;
		}
		String insertSql = "insert into provider_item (id, title, sys_item_id, sys_item_name, provider_id,status,sort) "
				+ "values('"
				+ maxItemId
				+ "','"
				+ item.getTitle()
				+ "','"
				+ item.getSysItemId()
				+ "','"
				+ item.getSysItemName()
				+ "','"
				+ item.getProviderId() + "',1," + maxSort + ")";
		this.jdbcTemplate.execute(insertSql);
		item.setItemId(maxItemId);
		return true;

	}

	public boolean delete(String itemId) {

		this.jdbcTemplate.execute("delete from provider_item  where id='"
				+ itemId + "'");

		return true;

	}

	public boolean update(String itemId, String type, String content) {

		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemTitle)
			updateStr = "title='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemSummary)
			updateStr = "summary='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemRemark)
			updateStr = "remark='" + content + "'";
		else
			return false;

		this.jdbcTemplate.execute("update provider_item set " + updateStr
				+ " where id=" + itemId);

		return true;

	}

	public boolean updatePrice(String itemId, String price, String priceOld) {

		String updateStr = "";
		if (Double.parseDouble(priceOld) > 0) {
			updateStr = "update provider_item set price=" + price
					+ ", price_old=" + priceOld + " where id=" + itemId;
		} else {
			updateStr = "update provider_item set price=" + price
					+ " where id=" + itemId;
		}
		this.jdbcTemplate.execute(updateStr);

		return true;

	}

	public boolean appendBusinessCount(String itemId) {
		String sql = "update provider_item set business_count=business_count+1 where id="
				+ itemId;
		return executeUpdate(sql);
	}

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
	public boolean moveItem(String itemId, String targetItemId) {
		int nItemSort = -1;
		String itemSort = "select sort from provider_item where id='" + itemId
				+ "'";
		int nItemTargetSort = -1;
		String itemTargetSort = "select sort from provider_item where id='"
				+ targetItemId + "'";
		nItemSort = this.jdbcTemplate.queryForObject(itemSort,
				java.lang.Integer.class);
		if (nItemSort < 0) {
			return false;
		}
		nItemTargetSort = this.jdbcTemplate.queryForObject(itemTargetSort,
				java.lang.Integer.class);
		if (nItemTargetSort < 0) {
			return false;
		}
		this.jdbcTemplate.execute("update provider_item set sort="
				+ nItemTargetSort + " where id=" + itemId);

		this.jdbcTemplate.execute("update provider_item set sort=" + nItemSort
				+ " where id=" + targetItemId);
		return true;

	}

	/**
	 * mode : 0��ʾ��ѯ���������ȫ����1��ʾ��ѯǰ���������2��ʾ��ѯ�����������
	 */
	public List<ModelProviderItem> getProviderItemByProviderId(int mode,
			String providerId, String strSysItemList, String priceStart,
			String priceEnd) {
		String sql = "select * from provider_item where status=1 and provider_id='"
				+ providerId + "' ";
		if (strSysItemList != null && strSysItemList.equals("") == false) {
			sql += " and sys_item_id in (" + strSysItemList + ")";
		}
		if (priceStart != null && priceStart.length() > 0) {
			sql += " and price>=" + priceStart;
		}
		if (priceEnd != null && priceEnd.length() > 0) {
			sql += " and price<=" + priceEnd;
		}
		sql += " order by sort";
		if (mode == 1) {
			sql += " limit " + ServerConfig.ItemPos;
		} else if (mode == 2) {
			sql += " limit " + ServerConfig.ItemPos + ", 100";
		}
		return getItemListBySql(sql);
	}

	/**
	 * ��ѯ�̼ҵ�ĳ��ϵͳ��Ŀ���м���������Ŀ
	 * 
	 * @param providerId
	 * @param sysItemId
	 * @return
	 */
	public List<ModelSysItem> getProviderItemBySysItemId(String providerId,
			String sysItemId) {
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();

		List<ModelProviderItem> itemlist = getProviderItemByProviderId(0,
				providerId, sysItemId, null, null);
		if (itemlist == null) {
			return list;
		}
		for (int i = 0; i < itemlist.size(); i++) {
			ModelSysItem lbl = new ModelSysItem();
			lbl.setLabelId(itemlist.get(i).getItemId());
			lbl.setParentId(sysItemId);
			lbl.setSysItemName(itemlist.get(i).getSysItemName());
			lbl.setPrice(itemlist.get(i).getPrice());
			lbl.setOldprice(itemlist.get(i).getPriceOld());
			lbl.setSummary(itemlist.get(i).getSummary());
			// lbl.setSelect(1);
			lbl.setTitle(itemlist.get(i).getTitle());
			list.add(lbl);

		}
		return list;
	}

	public ModelProviderItem getProviderItemById(String itemId) {
		String sql = "select * from provider_item where id = '" + itemId + "'";
		List<ModelProviderItem> list = getItemListBySql(sql);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public ModelProviderItem getProviderItemDetailById(String itemId) {
		String sql = "select * from provider_item where id = '" + itemId + "'";

		List<ModelProviderItem> list = getItemListBySql(sql);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	private List<ModelProviderItem> getItemListBySql(String sql) {
		return this.jdbcTemplate.query(sql, new RowMapper<ModelProviderItem>() {

			@Override
			public ModelProviderItem mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelProviderItem item = new ModelProviderItem();
				item.setItemId(rs.getString("id"));
				item.setProviderId(rs.getString("provider_id"));
				item.setSysItemId(rs.getString("sys_item_id"));
				item.setSysItemName(rs.getString("sys_item_name"));
				item.setTitle(rs.getString("title"));
				item.setSummary(rs.getString("summary"));
				item.setRemark(rs.getString("remark"));
				item.setPrice(rs.getFloat("price"));
				item.setPriceOld(rs.getFloat("price_old"));
				item.setBusiness(rs.getInt("business_count"));
				DecimalFormat df = new DecimalFormat("###,###.00");
				item.setPriceStr(df.format(rs.getFloat("price")));
				item.setPriceOldStr(df.format(rs.getFloat("price_old")));
				return item;
			}

		});

	}

	private boolean executeUpdate(String updateStr) {
		this.jdbcTemplate.execute(updateStr);
		return true;
	}
}
