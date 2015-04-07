package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.ServiceClassDao;
import com.chedb.dao.ServiceDao;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelService;
import com.chedb.model.ModelServiceClass;
import com.chedb.model.ModelServiceProvider;
import com.chedb.model.ModelUserAppraise;

@Repository("serviceDaoImpl")
public class ServiceDaoImpl implements ServiceDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "serviceClassDaoImpl")
	private ServiceClassDao serviceClassDao;

	public ModelService getServiceInfo(final String serviceId) {

		String sql = "select * from service where enable=1 and service_id='"
				+ serviceId + "'";
		return this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelService>() {

					@Override
					public ModelService mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelService item = new ModelService();
						// String serviceId = rs.getString("service_id");
						item = new ModelService();
						// String serviceId = rs.getString("service_id");
						item.setServiceId(rs.getString("service_id"));

						item.setClassId(rs.getString("service_class_id"));//
						item.setName(rs.getString("service_name"));
						item.setNeedCar(rs.getInt("need_car"));
						item.setFirstKm(rs.getInt("first_km"));
						item.setPeriodKm(rs.getInt("period_km"));

						item.setPreferential(rs.getInt("preferential"));
						item.setPriceOld(rs.getInt("price_old"));
						item.setAllowOnline(rs.getInt("allow_online"));
						item.setAllowXianjin(rs.getInt("allow_xianjin"));
						item.setAllowJifen(rs.getInt("allow_jifen"));
						item.setPriceOnline(rs.getInt("price_online"));//
						item.setPriceXianjin(rs.getInt("price_xianjin"));//
						item.setPriceJifen(rs.getInt("price_jifen"));//
						item.setReturnJifenOnline(rs
								.getInt("return_jifen_online"));//
						item.setReturnJifenXianjin(rs
								.getInt("return_jifen_xianjin"));//
						item.setReturnJifenJifen(rs
								.getInt("return_jifen_jifen"));//

						item.setHasRaw(rs.getInt("has_raw"));//
						// item.setBusinessNum(businessNum);(rs.getInt("business_initnum"));//
						item.setBusinessNum(rs.getInt("business_num"));//
						item.setWorkTitle(rs.getString("work_title"));//
						item.setWorkUrlPath(rs.getString("workdoc_path"));//
						item.setRawUrlPath(rs.getString("rawdoc_path"));//
						String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='"
								+ serviceId + "'";
						List<Map<String, Object>> result = jdbcTemplate
								.queryForList(sqlItem);
						int count = 0;
						for (Map<String, Object> row : result) {
							if (count == 0) {
								item.setItem1ClassTxt("" + row.get("item_name"));
								item.setItem1Txt("" + row.get("raw_name"));
							} else if (count == 1) {
								item.setItem2ClassTxt("" + row.get("item_name"));
								item.setItem2Txt("" + row.get("raw_name"));
							} else if (count == 2) {
								item.setItem3ClassTxt("" + row.get("item_name"));
								item.setItem3Txt("" + row.get("raw_name"));
							} else if (count == 3) {
								item.setItem4ClassTxt("" + row.get("item_name"));
								item.setItem4Txt("" + row.get("raw_name"));
							}
							count++;
						}
						item.setItemNum(count);
						return item;
					}

				});

	}


	public List<ModelServiceProvider> getServiceListByProvider(String providerId) {
		List<ModelServiceProvider> list = new ArrayList<ModelServiceProvider>();

		String sql = "SELECT distinct(substr(service_id, 6, 3)) as car_brand "
				+ "FROM service_provider where service_id like '02%' and enable=1 and provider_id='"
				+ providerId + "' ";

		List<ModelServiceProvider> list1 = this.jdbcTemplate.query(sql,
				new RowMapper<ModelServiceProvider>() {
					//
					@Override
					public ModelServiceProvider mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelServiceProvider item = new ModelServiceProvider();

						// item.setProviderId(rs.getString("id"));
						item.setServiceId(rs.getString("car_brand"));
						return item;
					}
				});
		if (list1 != null && list1.size() > 0) {
			list.addAll(list1);
		}

		String sql2 = "SELECT service_id "
				+ "FROM service_provider where service_id not like '02%' and enable=1 and provider_id='"
				+ providerId + "' ";

		List<ModelServiceProvider> list2 = this.jdbcTemplate.query(sql2,
				new RowMapper<ModelServiceProvider>() {

					@Override
					public ModelServiceProvider mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelServiceProvider item = new ModelServiceProvider();

						// item.setProviderId(rs2.getString("id"));
						item.setServiceId(rs.getString("service_id"));
						return item;
					}
				});

		if (list2 != null && list2.size() > 0) {
			list.addAll(list2);
		}

		return list;
	}
	
	public boolean saveServiceProviderLink(List<String> serviceIdlist, String serviceClassId, String providerId) {
		String sqlDelOld = "delete from service_provider where service_id like '"
				+ serviceClassId + "%' and provider_id='" + providerId + "' ";
		this.jdbcTemplate.execute(sqlDelOld);

		for (int i = 0; i < serviceIdlist.size(); i++) {
			String sqlNew;
			if (serviceClassId.equals("02")) {
				sqlNew = "insert into service_provider (service_id, provider_id, enable)"
						+ " SELECT service_id,'"
						+ providerId
						+ "',1 FROM service "
						+ " where service_class_id='02' and substr(service_id, 6, 3)='"
						+ serviceIdlist.get(i) + "'";
			} else {
				sqlNew = "insert into service_provider (service_id, provider_id, enable)"
						+ " values('"
						+ serviceIdlist.get(i)
						+ "','"
						+ providerId + "',1)";
			}
			this.jdbcTemplate.execute(sqlNew);
		}

		return true;
	}
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception {
		String sql = "";
		// ServiceClassDaoImpl classDao = new ServiceClassDaoImpl();
		ModelServiceClass serviceClass = serviceClassDao
				.queryServiceClassById(classId);
		if (serviceClass.getNeedCar()==1) {
			if (carId != null && carId.length()>0 ) {
			sql = "select * from service where enable=1 and service_class_id='" + classId 
					+ "' and service_id in (select service_id from car_service where caryear_id='"+carId+"')";		
			} else {
				sql = "select * from service where enable=1 and service_class_id='" + classId + "'";		
			}
		} else {
			sql = "select * from service where enable=1 and service_class_id='" + classId + "'";			
		}
		return getServiceListBySql(sql);
	}

	private List<ModelService> getServiceListBySql(String sql) {

		return this.jdbcTemplate.query(sql, new RowMapper<ModelService>() {

			@Override
			public ModelService mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelService item = new ModelService();
				String serviceId = rs.getString("service_id");
				item.setServiceId(rs.getString("service_id"));

				item.setClassId(rs.getString("service_class_id"));//
				item.setName(rs.getString("service_name"));
				// item.setRemark(rs.getString("remark"));
				item.setNeedCar(rs.getInt("need_car"));
				item.setFirstKm(rs.getInt("first_km"));
				item.setPeriodKm(rs.getInt("period_km"));

				item.setPreferential(rs.getInt("preferential"));
				item.setPriceOld(rs.getInt("price_old"));
				item.setAllowOnline(rs.getInt("allow_online"));
				item.setAllowXianjin(rs.getInt("allow_xianjin"));
				item.setAllowJifen(rs.getInt("allow_jifen"));
				item.setPriceOnline(rs.getInt("price_online"));//
				item.setPriceXianjin(rs.getInt("price_xianjin"));//
				item.setPriceJifen(rs.getInt("price_jifen"));//
				item.setPriceProvider(rs.getInt("price_provider"));//
				item.setReturnJifenOnline(rs.getInt("return_jifen_online"));//
				item.setReturnJifenXianjin(rs.getInt("return_jifen_xianjin"));//
				item.setReturnJifenJifen(rs.getInt("return_jifen_jifen"));//

				item.setHasRaw(rs.getInt("has_raw"));//
				item.setBusinessNum(rs.getInt("business_num"));//
				item.setWorkTitle(rs.getString("work_title"));//
				item.setWorkUrlPath(rs.getString("workdoc_path"));//
				item.setRawUrlPath(rs.getString("rawdoc_path"));//
				item.setNeedDelay(rs.getInt("need_delay"));
				
				String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='"
						+ serviceId + "'";
				List<Map<String, Object>> results = jdbcTemplate
						.queryForList(sqlItem);
				int count = 0;
				for (Map<String, Object> row : results) {
					if (count == 0) {
						item.setItem1ClassTxt("" + row.get("item_name"));
						item.setItem1Txt("" + row.get("raw_name"));
					} else if (count == 1) {
						item.setItem2ClassTxt("" + row.get("item_name"));
						item.setItem2Txt("" + row.get("raw_name"));
					} else if (count == 2) {
						item.setItem3ClassTxt("" + row.get("item_name"));
						item.setItem3Txt("" + row.get("raw_name"));
					} else if (count == 3) {
						item.setItem4ClassTxt("" + row.get("item_name"));
						item.setItem4Txt("" + row.get("raw_name"));
					}
					count++;
				}
				item.setItemNum(count);
				return item;
			}

		});

	}

	public int getProviderCountByServiceId(String serviceId) {
		int count = 0;
		String sql = "SELECT count(1) as count FROM service_provider where service_id='"
				+ serviceId + "' and enable=1";
		Integer counti = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		if (counti != null) {
			count = counti.intValue();
		}
		return count;

	}

	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude) {
		String sql = "select * from provider where status=1 and level=1 and id in(select provider_id from service_provider where service_id='"
				+ serviceId + "') ";
		// Ä¬ÈÏ°´Î»ÖÃÅÅÐò
		if (latitude != null && latitude.length() > 0 && longitude != null
				&& longitude.length() > 0) {
			sql += " order by abs(latitude-" + latitude + ")+abs(longitude-"
					+ longitude + ")";
		}
		sql += " limit " + start + "," + count;

		return this.jdbcTemplate.query(sql, new RowMapper<ModelProvider>() {

			@Override
			public ModelProvider mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelProvider item = new ModelProvider();
				item.setProviderId(rs.getString("id"));
				item.setTitle(rs.getString("title"));//
				item.setBrowse(rs.getInt("browse"));
				item.setBusiness(rs.getInt("business"));
				item.setScore(rs.getFloat("score"));//
				item.setScoreCount(rs.getInt("score_count"));//
				item.setAddr(rs.getString("addr"));//
				item.setLatitude(rs.getDouble("latitude"));//
				item.setLongitude(rs.getDouble("longitude"));//
				item.setImgIdListStr(rs.getString("imgid_list"));
				return item;
			}

		});

	}

	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) {

		ModelUserAppraise app = new ModelUserAppraise();
		String sqlAdd = "insert into service_appraise(service_class_id,service_id,user_id,content,appr_date,is_anonymous,agree_num,enable)"
				+ "values('"
				+ serviceClassId
				+ "','"
				+ serviceId
				+ "','"
				+ userId + "','" + content + "',now(),0,0,1)";
		this.jdbcTemplate.execute(sqlAdd);
		String sql = "select max(id) as id from service_appraise";
		Integer idi = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		if (idi != null) {
			app.setTheId("" + idi.intValue());
		}

		Date tt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		app.setDateStr(sdf.format(tt));
		return app;

	}

	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) {
		String sql = "SELECT count(1) as count FROM service_appraise where service_class_id='"
				+ serviceClassId + "' and enable=1";
		Integer counti = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		int count = 0;
		if (counti != null) {
			count = counti.intValue();
		}
		return count;

	}

	public List<ModelUserAppraise> getAppraiseListByServiceId(
			final String serviceClassId, final String serviceId,
			final String start, final String count) {
		String sql = "select a.id, a.content,a.appr_date,a.is_anonymous,a.agree_num,u.no,u.name "
				+ " from service_appraise as a, user as u "
				+ " where a.enable=1 and a.service_class_id='"
				+ serviceClassId
				+ "' and a.user_id=u.no order by a.appr_date desc limit "
				+ start + "," + count;
		return this.jdbcTemplate.query(sql, new RowMapper<ModelUserAppraise>() {

			@Override
			public ModelUserAppraise mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelUserAppraise item = new ModelUserAppraise();
				item.setTheId(rs.getInt("id") + "");
				item.setServiceClassId(serviceClassId);
				item.setServiceId(serviceId);//
				item.setAppraise(rs.getString("content"));
				item.setAgreeNum(rs.getInt("agree_num"));
				Date dt = rs.getTimestamp("appr_date");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String str = df.format(dt);
				item.setDateStr(str);
				item.setUserId(rs.getString("no"));//
				item.setUserName(rs.getString("name"));//
				return item;
			}

		});

	}

	@Override
	public List<ModelService> recommendService(String classId, String carId,
			String yearid, Integer moverange) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from service where service_class_id='"
				+ classId
				+ "' and EXISTS(select 1 from car_service where car_service.service_id=service.service_id and car_service.caryear_id='"
				+ yearid + "') and service.first_km>=" + moverange
				+ " and service.period_km<=" + moverange + "";
		return this.getServiceListBySql(sql);
	}

}
