package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.DatumDao;
import com.chedb.model.ModelDatum;
import com.chedb.model.ModelDatumLabel;

@Repository("datumDaoImpl")
public class DatumDaoImpl implements DatumDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ModelDatumLabel> getDatumLabelListByPanretId(int hasDatum, String pId) throws Exception {
		// TODO Auto-generated method stub
		String querySql = "select * from datum_label where status=1 and panret_id='"+pId+"'";
		if (hasDatum==1) {
			querySql += " and (select count(0) from datum where label_id='"+pId+"')>0";
		}
		querySql += " order by sort desc";
		return this.jdbcTemplate.query(querySql, new RowMapper<ModelDatumLabel>() {

			@Override
			public ModelDatumLabel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelDatumLabel modelLabel = new ModelDatumLabel();
				//
//				modelCar.setName(rs.getString("car_name"));
				modelLabel.setTheId(rs.getString("id"));
				modelLabel.setPanretId(rs.getString("panret_id"));
				modelLabel.setTatle(rs.getString("tatle"));
				modelLabel.setSort(rs.getInt("sort"));
//				modelCar.setMaintainPath(rs.getString("maintain_path"));
//				modelCar.setSerisename(rs.getString("series_name"));
				return modelLabel;
			}
		});
	}
	
	@Override
	public List<ModelDatum> getDatumListByLabelId(String labelId, int pageNum) throws Exception {
		// TODO Auto-generated method stub
		int pos = pageNum*10;
		String querySql = "select * from datum where status=1 and label_id='"+labelId+"'";
		querySql += " order by sort desc  limit "+pos+", "+(pos+10);
		
		return this.jdbcTemplate.query(querySql, new RowMapper<ModelDatum>() {

			@Override
			public ModelDatum mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelDatum datum = new ModelDatum();
				//
				datum.setTheId(rs.getString("id"));
				datum.setUserId(rs.getString("user_id"));
				datum.setUserName(rs.getString("user_name"));
				datum.setTatle(rs.getString("title"));
				datum.setUrl(rs.getString("url"));
				datum.setSort(rs.getInt("sort"));
				datum.setRenzheng(rs.getInt("renzheng"));
				
				return datum;
			}
		});
	}
}
