package com.chedb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.UserDao;
import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelUser;
import com.chedb.model.WechatUserModel;
import com.chedb.util.ServerConfig;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean delete(int id) {
		try {
			this.jdbcTemplate.execute("delete from user where id=" + id);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	//
	/**
	 * 获取一个新的用户ID
	 * 
	 * @param type
	 *            ：用户类型，1为车主用户，2为修理厂用户，3为配件商家用户
	 * @return 新的用户ID
	 *         获得的新ID用来注册，但很可能不被注册。新的ID在user表中状态为0，真正注册后状态为1，新ID生成后半小时内没有真正注册则可被重复使用
	 */
	public String getNewId(int type) throws Exception {

		String newId = null;
		int noLen = 8;// 车主用户id长度
		if (type == 2)
			noLen = 6;// 商家用户
		else if (type == 3) {
			noLen = 5;// 配件商家
		}
		Calendar c = Calendar.getInstance();
		// c.setTime(nowDate);
		c.add(Calendar.MINUTE, -30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time30m = sdf.format(c.getTime());
		String unuseIdSql = "select no from user where status=0 and LENGTH(no)="
				+ noLen
				+ " and regdate < '"
				+ time30m
				+ "'- INTERVAL 30 MINUTE order by regdate limit 1";
		//
		// RowMapper<String> mapper=new RowMapper<String>();
		List<String> nos = this.jdbcTemplate.query(unuseIdSql,
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						return arg0.getString("no");
					}

				});
		if (nos != null && !nos.isEmpty()) {
			newId = nos.get(0);
		}
		// newId = this.jdbcTemplate.queryForObject(unuseIdSql,
		// java.lang.String.class);

		// 如果有可用的ID，则就用这个，并将该ID时间更新
		if (newId != null) {
			this.jdbcTemplate.update("update user set regdate=now() where no='"
					+ newId + "'");
		} else {
			// 没有可用的ID，则生成一个，取现存 的最大值
			String maxNoSql = "select max(no) as maxno from user where LENGTH(no)="
					+ noLen;
			newId = this.jdbcTemplate.queryForObject(maxNoSql,
					java.lang.String.class);
			if (newId != null) {
				newId = (Long.parseLong(newId) + 1) + "";
				// 将新的ID写入表中，status为0
				String addIdSql = "insert into user(no,name,level,status,regdate) "
						+ "values('" + newId + "','新用户'," + type + ",0,now())";
				this.jdbcTemplate.execute(addIdSql);

			}
		}

		return newId;

	}

	public boolean register(ModelUser user) throws Exception {
		// 是否被注册
		String esxitSql = "select no from user where status=0 and no='"
				+ user.getNo() + "'";
		List<Map<String, Object>> result = this.jdbcTemplate
				.queryForList(esxitSql);
		int row = 0;
		if (!result.isEmpty()) {
			row++;
		}
		if (row == 0) {
			return false;
		}
		// 修改状态为1
		String updateSql = "update user set status=1,name='" + user.getName()
				+ "',passwd='" + user.getPasswd() + "',phone='"
				+ user.getPhone() + "' where no='" + user.getNo() + "'";
		this.jdbcTemplate.execute(updateSql);

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

	public ModelUser getUserByPhone(String phone) {

		return this.jdbcTemplate.queryForObject(
				"select * from user where phone='" + phone + "'",
				new RowMapper<ModelUser>() {

					@Override
					public ModelUser mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelUser user = new ModelUser();
						user.setNo(rs.getString("no"));
						user.setName(rs.getString("name"));
						user.setPasswd(rs.getString("passwd"));
						user.setLevel(rs.getInt("level"));
						user.setPhone(rs.getString("phone"));
						return user;
					}

				});

	}

	public ModelUser checkUser(String userNo, String passwd) {
		String sql = "select * from user where no='" + userNo + "' and pwsswd='"+passwd+"'";
		return getUserObj(sql);
	}
	
	public ModelUser getUser(String userNo) {
		List<ModelUser> users = this.jdbcTemplate.query(
				"select * from user where no='" + userNo + "'",
				new RowMapper<ModelUser>() {
					@Override
					public ModelUser mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelUser user = new ModelUser();
						user.setNo(rs.getString("no"));
						user.setName(rs.getString("name"));
						user.setPasswd(rs.getString("passwd"));
						user.setLevel(rs.getInt("level"));
						user.setPhone(rs.getString("phone"));
						user.setJifen(rs.getInt("jifen"));
						return user;
					}

				});
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
		/*
		 * return
		 * this.jdbcTemplate.queryForObject("select * from user where no='" +
		 * userNo + "'", new RowMapper<ModelUser>() {
		 * 
		 * @Override public ModelUser mapRow(ResultSet rs, int arg1) throws
		 * SQLException { // TODO Auto-generated method stub ModelUser user =
		 * new ModelUser(); user.setNo(rs.getString("no"));
		 * user.setName(rs.getString("name"));
		 * user.setPasswd(rs.getString("passwd"));
		 * user.setLevel(rs.getInt("level"));
		 * user.setPhone(rs.getString("phone"));
		 * user.setJifen(rs.getInt("jifen")); return user; }
		 * 
		 * });
		 */

	}
	private ModelUser getUserObj(String sql) {

		return this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelUser>() {

					@Override
					public ModelUser mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelUser user = new ModelUser();
						user.setNo(rs.getString("no"));
						user.setName(rs.getString("name"));
						user.setPasswd(rs.getString("passwd"));
						user.setLevel(rs.getInt("level"));
						user.setPhone(rs.getString("phone"));
						return user;
					}

				});

	}

	// type:1车系，2车型
	public List<ModelCarYear> getChoosedCarItemByUser(String userId, String type) {
		if (type.equals("1")) {
			String sql = "select car_id from user_car  where user_id='"+userId + "' and char_length(car_id)<10 ";		
			sql += " order by choose_date desc limit 2";
		
			List<ModelCarYear> resultlist = this.jdbcTemplate.query(sql,
					new RowMapper<ModelCarYear>() {
						@Override
						public ModelCarYear mapRow(ResultSet rs, int arg1)
								throws SQLException {
							// TODO Auto-generated method stub
							ModelCarYear item = new ModelCarYear();
							
							String carSeries = rs.getString("car_id");
							item.setId(carSeries);//
							
							String sqlItem = "select b.name as name, s.series_name as sname from car_brand b, car_series s where b.id='"
									+carSeries.substring(0, 3)+	"' and s.series_id='"+carSeries+"'";	
							List<Map<String, Object>> results = jdbcTemplate
									.queryForList(sqlItem);
							for (Map<String, Object> row : results) {
								item.setName(row.get("name")+" "+row.get("sname"));
							}							
							return item;
						}
					});
			if (resultlist.size() == 0) {// 没有，给个默认的
				ModelCarYear item = new ModelCarYear();
				item.setId("419103");//
				item.setName("大众 速腾");//
				resultlist.add(item);
			}
			return resultlist;
			
		} else if (type.equals("2")) {
			String sql = "select car_year.id, car_year.name, car_year.maintain_path"+
					" from car_year, user_car  where user_car.user_id='"+userId +"' and car_year.id=user_car.car_id ";
			sql += " and char_length(user_car.car_id)>10 ";		
			sql += " order by choose_date desc limit 2";
			List<ModelCarYear> resultlist = this.jdbcTemplate.query(sql,
					new RowMapper<ModelCarYear>() {
						@Override
						public ModelCarYear mapRow(ResultSet rs, int arg1)
								throws SQLException {
							// TODO Auto-generated method stub
							ModelCarYear item = new ModelCarYear();
							item.setId(rs.getString("id"));//
							item.setName(rs.getString("name"));//
							item.setMaintainPath(rs.getString("maintain_path"));//
							return item;
						}
			});
			if (resultlist.size() == 0) {// 默认的
				ModelCarYear item = new ModelCarYear();
				item.setId("4191031004_2012");//
				item.setName("大众 速腾 1.8TSI 2012年产");//
				resultlist.add(item);
			}
			return resultlist;				
		} else {
			return null;
		}
	}
	
	public boolean addChoosedCarByAppId(String carId, String appId) {

		String id = null;
		String existSql = "select id from user_car " + " where user_id='"
				+ appId + "' and car_id='" + carId + "'";
		try {
			id = this.jdbcTemplate.queryForObject(existSql, java.lang.String.class);
		} catch (Exception e) {
			;
		}

		if (id == null) {
			String addIdSql = "insert into user_car(user_id,car_id,choose_date) "
					+ "values('" + appId + "','" + carId + "',now())";

			this.jdbcTemplate.execute(addIdSql);
		} else {
			String updatedSql = "update user_car set choose_date=now() where id="
					+ id;

			this.jdbcTemplate.execute(updatedSql);
		}

		return true;

	}

	// 1001:打开程序
	// 1002:退出程序
	// 2001：注册
	// 2002：登录
	// 3001：选择新车型
	// 3002：查修理厂
	public boolean addUserConfig(String operType, String appId,
			String operContent) {
		String addIdSql = "insert into user_config(app_id, oper_type, oper_content, oper_time) "
				+ "values('"
				+ appId
				+ "','"
				+ operType
				+ "','"
				+ operContent
				+ "',now())";
		this.jdbcTemplate.execute(addIdSql);
		return true;
	}

	@Override
	public boolean isBind(String username) throws Exception {
		// TODO Auto-generated method stub
		List<WechatUserModel> users = this.jdbcTemplate.query(
				"select  * from wechatuser where username='" + username + "'",
				new RowMapper<WechatUserModel>() {
					@Override
					public WechatUserModel mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						String username = arg0.getString("username");
						String openid = arg0.getString("openid");
						java.sql.Date bindDate = arg0.getDate("binddate");
						WechatUserModel wechatUserModel = new WechatUserModel();
						wechatUserModel.setUsername(username);
						wechatUserModel.setOpenid(openid);
						wechatUserModel.setBinddate(new Date(bindDate.getTime()));
						return wechatUserModel;
					}

				});
		if (users != null && !users.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void bindUser(final WechatUserModel user) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.jdbcTemplate.execute(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection arg0)
						throws SQLException {
					// TODO Auto-generated method stub
					return arg0
							.prepareStatement("insert into wechatuser(username,openid,binddate) values(?,?,?)");
				}

			}, new PreparedStatementCallback<Integer>() {

				@Override
				public Integer doInPreparedStatement(PreparedStatement arg0)
						throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					arg0.setString(1, user.getUsername());
					arg0.setString(2, user.getOpenid());
					arg0.setDate(3,
							new java.sql.Date(System.currentTimeMillis()));
					arg0.execute();
					return 1;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
