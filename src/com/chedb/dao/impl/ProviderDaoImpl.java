package com.chedb.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.ProviderDao;
import com.chedb.model.ModelProvider;
import com.chedb.util.ServerConfig;

@Repository("providerDaoImpl")
public class ProviderDaoImpl implements ProviderDao  {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean delete(int id) throws Exception {
		this.jdbcTemplate.execute("delete from user where id=" + id);
		return true;

	}

	public boolean add(ModelProvider t) {

		this.jdbcTemplate
				.execute("insert into provider(id,level,title,status) values('"
						+ t.getProviderId() + "',1,'" + t.getTitle() + "',1"
						+ ")");

		return true;

	}

	public boolean updateScore(String providerId, String score) {

		ModelProvider modelProvideri = this.jdbcTemplate.queryForObject(
				"select score_total,score_count from provider where id='"
						+ providerId + "'", new RowMapper<ModelProvider>() {

					@Override
					public ModelProvider mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelProvider modelProvider = new ModelProvider();
						modelProvider.setScoreTotal(arg0.getInt("score_total"));
						modelProvider.setScoreCount(arg0.getInt("score_count"));
						return modelProvider;
					}

				});
		int score_total = modelProvideri.getScoreTotal();
		int score_count = modelProvideri.getScoreCount();
		score_total += Integer.parseInt(score);
		score_count++;
		float srore = (score_total) / ((float) score_count);
		BigDecimal b = new BigDecimal(srore);
		srore = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.jdbcTemplate.execute("update provider set score=" + srore
				+ ",score_total=" + score_total + ",score_count=" + score_count
				+ " where id='" + providerId + "'");
		return true;

	}

	public boolean update(String providerId, String type, String content) {

		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderTitle)
			updateStr = "title='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderSummary)
			updateStr = "summary='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderRemark)
			updateStr = "remark='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderAddr) {
			int pos = content.lastIndexOf("|");
			String x = "", y = "";
			if (pos > 0) {
				int xpos = content.lastIndexOf("-");
				x = content.substring(pos + 1, xpos);
				y = content.substring(xpos + 1);
				updateStr = "addr='" + content.substring(0, pos)
						+ "',latitude=" + x + ",longitude=" + y;
			} else {
				updateStr = "addr='" + content + "'";
			}
		} else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderPhone)
			updateStr = "phone='" + content + "'";
		else
			return false;
		this.jdbcTemplate.execute("update provider set " + updateStr
				+ " where id=" + providerId);
		return true;

	}

	public boolean appendBusinessCount(String providerId) {
		String sql = "update provider set business=business+1 where id="
				+ providerId;
		this.jdbcTemplate.execute(sql);
		return true;
	}

	public boolean appendBrowseCount(String providerId) {
		String sql = "update provider set browse=browse+1 where id="
				+ providerId;
		this.jdbcTemplate.execute(sql);
		return true;
	}

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelProvider queryProviderById(String providerId) {
		String sql = "select * from provider where id = '" + providerId + "'";
		return getProvider(sql);
	}

	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	public List<ModelProvider> getProviderListBySearch(String strSearch) {
		String sql = "";
		boolean mat = strSearch.matches("\\d+");// ����trueΪ������,����Ͳ��Ǵ�����
		if (mat) {
			sql = "select * from provider where id like '%" + strSearch
					+ "%' limit 100";
		} else {
			sql = "select * from provider where title like '%" + strSearch
					+ "%' limit 100";
		}
		return getProviderListBySql(sql);
	}

	public String getImgIdAndUpdateImgList(String providerId) {

		String selStr = "1";
		int nowid = 1;
		String imgidList = "";
		imgidList = this.jdbcTemplate
				.queryForObject("select imgid_list from provider where id='"
						+ providerId + "'", java.lang.String.class);
		if (imgidList != null && imgidList.length() > 0) {
			String idlist[] = imgidList.split(",");
			if (idlist != null && idlist.length > 0) {
				for (int i = 0; i < idlist.length; i++) {
					int id = Integer.parseInt(idlist[i]);
					if (nowid < id) {
						nowid = id;
					}
				}
			}
			nowid++;
			selStr = imgidList + "," + nowid;
		} else {
			selStr = nowid + "";
		}
		this.jdbcTemplate.execute("update provider set imgid_list='" + selStr
				+ "' where id='" + providerId + "'");
		return nowid + "";

	}

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
			String imgId, String edittype) {

		String newImgIdList = "";
		String ids[] = oldImgIdList.split(",");
		if (ids != null && ids.length > 0) {
			int index = 0;
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].equals(imgId)) {
					index = i;
					break;
				}
			}
			if (edittype.equals("1")) {
				for (int i = 0; i < index; i++) {
					newImgIdList += ids[i] + ",";
				}
				if (ids.length > index + 1) {
					newImgIdList += ids[index + 1] + ",";
				}
				newImgIdList += ids[index] + ",";
				for (int i = index + 2; i < ids.length; i++) {
					newImgIdList += ids[i] + ",";
				}
			} else if (edittype.equals("0")) {
				for (int i = 0; i < index; i++) {
					newImgIdList += ids[i] + ",";
				}
				for (int i = index + 1; i < ids.length; i++) {
					newImgIdList += ids[i] + ",";
				}
			} else if (edittype.equals("-1")) {
				for (int i = 0; i < index - 1; i++) {
					newImgIdList += ids[i] + ",";
				}
				newImgIdList += ids[index] + ",";
				if (index > 0) {
					newImgIdList += ids[index - 1] + ",";
				}
				for (int i = index + 1; i < ids.length; i++) {
					newImgIdList += ids[i] + ",";
				}
			}
		}
		// 1,3,5,4
		// 0,1,2,3
		if (newImgIdList.length() > 0) {
			newImgIdList = newImgIdList.substring(0, newImgIdList.length() - 1);
		}

		this.jdbcTemplate.execute("update provider set imgid_list='"
				+ newImgIdList + "' where id='" + providerId + "'");
		return newImgIdList;

	}

	// ����λ������
	private String sqlProviderListBySpace(String level, String strSysItemList,
			String priceStart, String priceEnd, String latitude,
			String longitude) {
		String sql = "select * from provider where status=1 and level=" + level;// +" order by score limit 100";
		boolean sysItemListOk = strSysItemList != null
				&& strSysItemList.length() > 0;
		boolean priceStartOk = priceStart != null && priceStart.length() > 0;
		boolean priceEndOk = priceEnd != null && priceEnd.length() > 0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			sql += " and id in (select distinct provider_id from provider_item where status=1 ";
			if (sysItemListOk) {
				sql += " and sys_item_id in (" + strSysItemList + ")";
			}
			if (priceStartOk) {
				sql += " and price>=" + priceStart;
			}
			if (priceEndOk) {
				sql += " and price<=" + priceEnd;
			}
			sql += ")";
		}
		if (latitude != null && latitude.length() > 0 && longitude != null
				&& longitude.length() > 0) {
			sql += " order by abs(latitude-" + latitude + ")+abs(longitude-"
					+ longitude + ")";
		}
		sql += " limit 100";

		return sql;
	}

	// ���ݿڱ����÷֣�����
	private String sqlProviderListByScore(String level, String strSysItemList,
			String priceStart, String priceEnd) {
		String sql = "select * from provider where status=1 and level=" + level;// +" order by score limit 100";
		boolean sysItemListOk = strSysItemList != null
				&& strSysItemList.length() > 0;
		boolean priceStartOk = priceStart != null && priceStart.length() > 0;
		boolean priceEndOk = priceEnd != null && priceEnd.length() > 0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			sql += " and id in (select distinct provider_id from provider_item where status=1 ";
			if (sysItemListOk) {
				sql += " and sys_item_id in (" + strSysItemList + ")";
			}
			if (priceStartOk) {
				sql += " and price>=" + priceStart;
			}
			if (priceEndOk) {
				sql += " and price<=" + priceEnd;
			}
			sql += ")";
		}
		sql += " order by score desc ";
		sql += " limit 100";

		return sql;
	}

	// ���ݼ۸�����
	private String sqlProviderListByPrice(String level, String strSysItemList,
			String priceStart, String priceEnd) {
		String sql = "select distinct provider_item.provider_id, provider.* from provider_item  "
				+ " INNER JOIN provider ON provider.id=provider_item.provider_id "
				+ " where provider.status=1 and provider.level="
				+ level
				+ " and provider_item.status=1 ";

		boolean sysItemListOk = strSysItemList != null
				&& strSysItemList.length() > 0;
		boolean priceStartOk = priceStart != null && priceStart.length() > 0;
		boolean priceEndOk = priceEnd != null && priceEnd.length() > 0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			if (sysItemListOk) {
				sql += " and sys_item_id in (" + strSysItemList + ")";
			}
			if (priceStartOk) {
				sql += " and price>=" + priceStart;
			}
			if (priceEndOk) {
				sql += " and price<=" + priceEnd;
			}
		}
		sql += " order by price ";
		sql += " limit 100";
		return sql;
	}

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
			String priceEnd, String latitude, String longitude) {
		String sql = null;
		if (sort.equals("1")) {// ������
			sql = sqlProviderListBySpace(level, strSysItemList, priceStart,
					priceEnd, latitude, longitude);
		} else if (sort.equals("2")) {
			sql = sqlProviderListByScore(level, strSysItemList, priceStart,
					priceEnd);
		} else if (sort.equals("3")) {
			sql = sqlProviderListByPrice(level, strSysItemList, priceStart,
					priceEnd);
		}
		return getProviderListBySql(sql);
	}

	private List<ModelProvider> getProviderListBySql(String sql) {
		List<ModelProvider> list = new ArrayList<ModelProvider>();
		list = this.jdbcTemplate.query(sql, new RowMapper<ModelProvider>() {

			@Override
			public ModelProvider mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelProvider item = new ModelProvider();
				item.setProviderId(rs.getString("id"));
				item.setTitle(rs.getString("title"));// //
				item.setSummary(rs.getString("summary")); //
				item.setRemark(rs.getString("remark"));
				item.setBrowse(rs.getInt("browse"));
				item.setBusiness(rs.getInt("business"));
				item.setScore(rs.getFloat("score"));//
				item.setScoreCount(rs.getInt("score_count"));//
				item.setAddr(rs.getString("addr"));//
				item.setLatitude(rs.getDouble("latitude"));//
				item.setLongitude(rs.getDouble("longitude"));// //
				item.setPhone(rs.getString("phone")); //
				item.setRenzheng(rs.getInt("renzheng"));// //
				item.setS4(rs.getInt("s4"));// //
				item.setLiansuo(rs.getInt("liansuo"));// //
				item.setImgIdListStr(rs.getString("imgid_list"));

				return item;
			}

		});
		return list;
	}

	private ModelProvider getProvider(String sql) {
		return this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelProvider>() {

					@Override
					public ModelProvider mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelProvider item = new ModelProvider();
						item.setProviderId(rs.getString("id"));

						item.setTitle(rs.getString("title"));//
						item.setSummary(rs.getString("summary"));
						item.setRemark(rs.getString("remark"));
						item.setBrowse(rs.getInt("browse"));
						item.setBusiness(rs.getInt("business"));
						item.setScore(rs.getFloat("score"));//
						item.setScoreCount(rs.getInt("score_count"));//
						item.setAddr(rs.getString("addr"));//
						item.setLatitude(rs.getDouble("latitude"));//
						item.setLongitude(rs.getDouble("longitude"));//
						item.setPhone(rs.getString("phone"));
						item.setRenzheng(rs.getInt("renzheng"));//
						item.setS4(rs.getInt("s4"));//
						item.setLiansuo(rs.getInt("liansuo"));//
						item.setImgIdListStr(rs.getString("imgid_list"));
						return item;
					}
				});

	}

}
