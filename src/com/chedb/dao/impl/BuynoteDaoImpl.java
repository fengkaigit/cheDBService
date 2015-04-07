package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.BuynoteDao;
import com.chedb.dao.ProviderDao;
import com.chedb.model.BsiRecordValue;
import com.chedb.model.ModelBusinote;

@Repository("buynoteDaoImpl")
public class BuynoteDaoImpl implements BuynoteDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "providerDaoImpl")
	private ProviderDao providerDao;

	public boolean addBusi(ModelBusinote t) {
		String updateJifen = "";
		if (t.getPayType() == 3) {// 积分抵付，先查用户积分是否大于等于项目所需积分
			String sqlQUser = "select jifen from user where no='"
					+ t.getUserId() + "'";
			int jifen = 0;
			jifen = this.jdbcTemplate.queryForObject(sqlQUser,
					java.lang.Integer.class);
			if (t.getPriceJifen() > jifen) {
				// 积分不够返回失败 
				return false;
			}
			// 积分够的话，扣除用户积分
			updateJifen = "update user set jifen=jifen-" + t.getPriceJifen()
					+ " where no='" + t.getUserId() + "'";
			this.jdbcTemplate.execute(updateJifen);
		}

		// 生成交易订单数据
		Date tt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-");
		int random = (int) (Math.random() * 900);
		String no = sdf.format(tt) + (random + 100);
		String sqlAdd = "insert into busi_record(no,pay_type,price,price_jifen,return_jifen,pay_time,online_pay_no,service_class_id,service_id,status,provider_id,user_id,delay_type,enable)"
				+ "values('"
				+ no
				+ "',"
				+ t.getPayType()
				+ ","
				+ t.getPrice()
				+ ","
				+ t.getPriceJifen()
				+ ","
				+ t.getReturnJifen()
				+ ",now(),'"
				+ t.getOnlinePayNo()
				+ "','"
				+ t.getServiceClassId()
				+ "','"
				+ t.getServiceId()
				+ "',1,'" + t.getProviderId() + "','" + t.getUserId() +"',"+t.getDelayType()+",1)";
		this.jdbcTemplate.execute(sqlAdd);
		return true;
	}

	public boolean updateBusinoteInfo(String busiNo, String newStatus,
			String score) {
		String sqlQBusi = "select pay_type, price_jifen, return_jifen, service_class_id, service_id, status, provider_id, user_id from busi_record where no='"
				+ busiNo + "'";
		int type = 1;
		int price_jifen = 0;
		int return_jifen = 0;
		String serviceId = "";
		String serviceClassId = "";
		int status = 0;
		String providerId = "";
		String user_id = "";
		BsiRecordValue bsiRecordValuei = this.jdbcTemplate.queryForObject(
				sqlQBusi, new RowMapper<BsiRecordValue>() {
					@Override
					public BsiRecordValue mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						BsiRecordValue bsiRecordValue = new BsiRecordValue();
						bsiRecordValue.setType(rs.getInt("pay_type"));
						bsiRecordValue.setPrice_jifen(rs.getInt("price_jifen"));
						bsiRecordValue.setReturn_jifen(rs
								.getInt("return_jifen"));
						bsiRecordValue.setServiceClassId(rs
								.getString("service_class_id"));
						bsiRecordValue.setServiceId(rs.getString("service_id"));
						bsiRecordValue.setStatus(rs.getInt("status"));
						bsiRecordValue.setProviderId(rs.getString("service_id"));
						bsiRecordValue.setUser_id(rs.getString("user_id"));
						return bsiRecordValue;
					}

				});
		type = bsiRecordValuei.getType();
		price_jifen = bsiRecordValuei.getPrice_jifen();
		return_jifen = bsiRecordValuei.getReturn_jifen();
		serviceClassId = bsiRecordValuei.getServiceClassId();
		serviceId = bsiRecordValuei.getServiceId();
		status = bsiRecordValuei.getStatus();
		providerId = bsiRecordValuei.getProviderId();
		user_id = bsiRecordValuei.getUser_id();

		if (newStatus != null) {
			String sqlUpdate = "";
			//
			if (Integer.parseInt(newStatus) == 0) {// 取消订单
				if (status != 1) {
					// 如果不是状态为1，则不能取消
					return false;
				}
				if (type == 3) {// 如果是积分抵付，则归还积分
					String updateJifen = "update user set jifen=jifen+"
							+ price_jifen + " where no='" + user_id + "'";
					this.jdbcTemplate.execute(updateJifen);
				}
				sqlUpdate = "update busi_record set status=0  where no='" + busiNo + "'";	
			} else if (Integer.parseInt(newStatus) == 2) {// 商家确认
				if (status != 1) {
					// 如果不是状态为1，则不能进行商家确认
					return false;
				}
				sqlUpdate = "update busi_record set status=2  where no='" + busiNo + "'";	
			} else if (Integer.parseInt(newStatus) == 3) {// 完成
				if (status != 2) {
					// 如果不是商家确认状态（2），则不能进行
					return false;
				}
				if (return_jifen > 0) {// 累积积分
					String updateJifen = "update user set jifen=jifen+"
							+ return_jifen + " where no='" + user_id + "'";

					this.jdbcTemplate.execute(updateJifen);
				}
				// 更新商家、服务的交易量数据
				String updateProvider = "update provider set business=business+1 where id='"
						+ providerId + "'";
				this.jdbcTemplate.execute(updateProvider);

				String updateServiceClass = "update service_class set business_num=business_num+1 where class_id='"
						+ serviceClassId + "'";
				this.jdbcTemplate.execute(updateServiceClass);

				String updateService = "update service set business_num=business_num+1 where service_id='"
						+ serviceId + "'";
				this.jdbcTemplate.execute(updateService);

				sqlUpdate = "update busi_record set status=3  where no='" + busiNo + "'";		
			} else if (Integer.parseInt(newStatus) == 4) {// 打分			
				if (status != 3) {
					// 如果不是“完成”状态（3），则不能进行
					return false;
				}
				sqlUpdate = "update busi_record set status=4, score=" + score
						+ " where no='" + busiNo + "'";			
				
				try {
					// 更新商家得分
					providerDao.updateScore(providerId, score);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return false;
			}
			this.jdbcTemplate.execute(sqlUpdate);
		} else {// 
			return false;
		}
		return true;
	}
	// 目前该接口只被管理员工具使用
	public List<ModelBusinote> queryBuynoteList(String classId, String status, String providerId
			, String userId, String startTime, String endTime) {
		String sql = "select busi_record.*, service.price_provider, service.service_name, provider.title, user.name "+
				" from busi_record, service, provider, user where busi_record.enable=1 "+
				" and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"
				;
		if (classId != null && classId.length()>0) {
			sql += " and busi_record.service_class_id in ("+classId+") ";
		}
		if (status != null && status.length()>0) {
			sql += " and busi_record.status in ("+status+") ";
		}
		if (providerId != null && providerId.length()>0) {
			sql += " and busi_record.provider_id='"+providerId+"' ";
		}
		if (startTime != null && startTime.length()>0) {
			sql += " and busi_record.pay_time>'"+startTime+"' ";
		}
		if (endTime != null && endTime.length()>0) {
			sql += " and busi_record.pay_time<'"+endTime+"' ";
		}
		sql += " order by no desc";
		return getListBySql(sql);	
	}

	/**
	 * 查询系统项目类别
	 * 
	 * @param
	 * @return
	 */
	public List<ModelBusinote> getBuynoteListByUser(String userId, int page) {
		int start = 10 * page;
		String sql = "select busi_record.*, service.price_provider, service.service_name, provider.title, user.name "
				+ " from busi_record, service, provider, user where busi_record.enable=1 "
				+ " and busi_record.user_id='"
				+ userId
				+ "' "
				+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and user.no='"
				+ userId
				+ "' order by no desc limit  "
				+ start
				+ ","
				+ (start + 10);

		return getListBySql(sql);
	}

	public List<ModelBusinote> getBuynoteListByProviderId(String providerId,
			int page) {
		int start = 10 * page;
		String sql = "select busi_record.*, service.price_provider, service.service_name, provider.title, user.name "
				+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
				+ " and busi_record.provider_id='"
				+ providerId
				+ "' "
				+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"
				+ " order by no desc limit  " + start + "," + (start + 10);

		return getListBySql(sql);
	}

	public ModelBusinote getBusinoteById(String no) {
		String sql = "select status from busi_record where no='" + no + "'";
		ModelBusinote item = this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelBusinote>() {
					@Override
					public ModelBusinote mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelBusinote itemi = new ModelBusinote();
						itemi.setStatus(rs.getInt("status"));
						return itemi;
					}
				});
		return item;

	}

	@Override
	public List<ModelBusinote> paingqueryBuynote(String userId,
			Integer pageindex, Integer type) throws Exception {
		// TODO Auto-generated method stub
		int end = 10 * pageindex;
		String sql = "";
		if (type == 1) {
			sql = "select busi_record.*, service.service_name, service.price_provider, provider.title, user.name "
					+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
					+ " and busi_record.user_id='"
					+ userId
					+ "' "
					+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and user.no='"
					+ userId + "' order by no desc limit  " + 0 + "," + end;
		} else {
			sql = "select busi_record.*, service.service_name, service.price_provider, provider.title, user.name "
					+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
					+ " and busi_record.provider_id='"
					+ userId
					+ "' "
					+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"
					+ " order by no desc limit  " + 0 + "," + end;
		}
		return getListBySql(sql);
	}

	@Override
	public Integer rowcount(String userId, Integer type) throws Exception {
		// TODO Auto-generated method stub
		String sql = "";
		if (type == 1) {
			sql = "select count(1) from (select busi_record.*, service.service_name, provider.title, user.name "
					+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
					+ " and busi_record.user_id='"
					+ userId
					+ "' "
					+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and user.no='"
					+ userId + "') as tab";
		} else {
			sql = "select count(1) from (select busi_record.*, service.service_name, provider.title, user.name "
					+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
					+ " and busi_record.provider_id='"
					+ userId
					+ "' "
					+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"
					+ " ) as tab";
		}

		return this.jdbcTemplate.queryForObject(sql, java.lang.Integer.class);
	}

	private List<ModelBusinote> getListBySql(String sql) {
		return this.jdbcTemplate.query(sql, new RowMapper<ModelBusinote>() {

			@Override
			public ModelBusinote mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelBusinote item = new ModelBusinote();
				item.setBuynoteNo(rs.getString("no"));
				item.setUserId(rs.getString("user_id"));
				item.setPayType(rs.getInt("pay_type"));
				item.setPrice(rs.getInt("price"));
				item.setPriceJifen(rs.getInt("price_jifen"));
				item.setReturnJifen(rs.getInt("return_jifen"));
				item.setOnlinePayNo(rs.getString("online_pay_no"));
				item.setPriceProvider(rs.getInt("price_provider"));
				item.setServiceId(rs.getString("service_id"));
				item.setStatus(rs.getInt("status"));
				item.setProviderId(rs.getString("provider_id"));
				item.setProviderName(rs.getString("title"));
				item.setScore(rs.getInt("score"));
				item.setServiceClassId(rs.getString("service_class_id"));
				item.setServiceName(rs.getString("service_name"));
				item.setUserName(rs.getString("name"));
				item.setDelayType(rs.getInt("delay_type"));
				return item;
			}

		});
	}

}
