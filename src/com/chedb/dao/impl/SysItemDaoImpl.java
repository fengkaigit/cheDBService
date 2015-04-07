package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.SysItemDao;
import com.chedb.model.ModelSysItem;
import com.chedb.util.ServerConfig;

@Repository("sysItemDaoImpl")
public class SysItemDaoImpl implements SysItemDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean delete(int id) {
		this.jdbcTemplate.execute("delete from user where id=" + id);
		return true;
	}

	public boolean update(String userNo, String type, String content) {
		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Name)
			updateStr = "name='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Passwd)
			updateStr = "passwd='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Phone)
			updateStr = "phone='" + content + "'";
		else
			return false;
		this.jdbcTemplate.execute("update user set " + updateStr + " where no="
				+ userNo);

		return true;
	}

	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelSysItem> getSysItemClass(String level) {

		return this.jdbcTemplate.query(
				"select * from sys_item_class where status=1 and level="
						+ level + " order by sort",
				new RowMapper<ModelSysItem>() {

					@Override
					public ModelSysItem mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelSysItem item = new ModelSysItem();

						item.setLabelId(rs.getString("id"));
						item.setTitle(rs.getString("title"));
						return item;
					}

				});
	}

	/**
	 * ��ѯ�̼ҵķ�����Ŀ����Ӧ��ϵͳ��Ŀ
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelSysItem> getProviderSysItem(String providerId) {
		return this.jdbcTemplate
				.query("select DISTINCT sys_item_id from provider_item where status=1 and provider_id='"
						+ providerId + "'", new RowMapper<ModelSysItem>() {

					@Override
					public ModelSysItem mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						String sysItemId = rs.getString("sys_item_id");
						ModelSysItem item = getSysItemById(sysItemId);
						return item;
					}

				});

	}

	/**
	 * ��ѯϵͳ��Ŀ
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelSysItem> getSysItemByClassId(final String classId) {
		return this.jdbcTemplate.query(
				"select * from sys_item where status=1 and class_item_id='"
						+ classId + "' order by sort",
				new RowMapper<ModelSysItem>() {

					@Override
					public ModelSysItem mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelSysItem item = new ModelSysItem();

						item.setLabelId(rs.getString("id"));
						item.setTitle(rs.getString("title"));
						item.setParentId(classId);
						return item;
					}

				});

	}

	/**
	 * ��ѯϵͳ��Ŀ
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public ModelSysItem getSysItemById(final String itemId) {
		List<ModelSysItem> items = this.jdbcTemplate
				.query("select * from sys_item where status=1 and id='"
						+ itemId + "'", new RowMapper<ModelSysItem>() {

					@Override
					public ModelSysItem mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelSysItem item = new ModelSysItem();
						item.setLabelId(itemId);
						item.setTitle(rs.getString("title"));
						item.setParentId(rs.getString("class_item_id")); //
						return item;
					}

				});
		if (items != null && !items.isEmpty()) {
			return items.get(0);
		} else {
			return null;
		}
	}
}
