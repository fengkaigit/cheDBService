package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.CarDao;
import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;

@Repository("carDaoImpl")
public class CarDaoImpl implements CarDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<ModelCarBrand> getCarBrandList() {
		String sql = "select id,name from car_brand where enable=1 and id in(select brand_id from car) order by sort ";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCarBrand>() {

			@Override
			public ModelCarBrand mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelCarBrand item = new ModelCarBrand();

				item.setId(rs.getString("id"));//
				item.setName(rs.getString("name"));
				return item;
			}

		});

	}

	public List<ModelCarSerise> getCarSeriseListByBrand(String brandId) {

		String sql = "select series_id, series_name from car_series where enable=1 and brand_id='"
				+ brandId + "' order by sort";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCarSerise>() {

			@Override
			public ModelCarSerise mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelCarSerise item = new ModelCarSerise();

				item.setId(rs.getString("series_id"));

				item.setName(rs.getString("series_name"));//
				return item;
			}

		});

	}

	public List<ModelCar> getCarListBySerise(String seriseId) {
		String sql = "select car_id, car_name,series_id, maintain_path,brand_id,(select car_brand.name from car_brand where car_brand.id= car.brand_id) brand_name,(select car_series.series_name from car_series where car_series.series_id=car.series_id) as series_name   from car where enable=1 and series_id='"
				+ seriseId + "' order by sort";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCar>() {

			@Override
			public ModelCar mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelCar item = new ModelCar();
				item.setBrandId(rs.getString("brand_id"));
				item.setBrandname(rs.getString("brand_name"));
				item.setSeriseId(rs.getString("series_id"));
				item.setSeriseId(rs.getString("series_id"));
				item.setSerisename(rs.getString("series_name"));
				item.setId(rs.getString("car_id"));//
				item.setName(rs.getString("car_name"));//
				item.setMaintainPath(rs.getString("maintain_path"));//
				return item;
			}

		});

	}

	@Override
	public List<ModelCar> getDefaultCars(String carid) throws Exception {
		// TODO Auto-generated method stub
		String querySql = "select car.*,(select car_brand.name from car_brand where car_brand.id= car.brand_id) as brandname,(select car_series.series_name from car_series where car_series.series_id=car.series_id) as series_name  from car where car_id='"
				+ carid
				+ "' union select car.*,(select car_brand.name from car_brand where car_brand.id= car.brand_id) as brandname,(select car_series.series_name from car_series where car_series.series_id=car.series_id) as series_name  from "
				+ " car where car_id<>'" + carid + "' limit 2";
		return this.jdbcTemplate.query(querySql, new RowMapper<ModelCar>() {

			@Override
			public ModelCar mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelCar modelCar = new ModelCar();
				// car.car_id, car.car_name, car.maintain_path
				modelCar.setName(rs.getString("car_name"));
				modelCar.setId(rs.getString("car_id"));
				modelCar.setSeriseId(rs.getString("series_id"));
				modelCar.setBrandId(rs.getString("brand_id"));
				modelCar.setBrandname(rs.getString("brandname"));
				modelCar.setMaintainPath(rs.getString("maintain_path"));
				modelCar.setSerisename(rs.getString("series_name"));
				return modelCar;
			}

		});
	}

	@Override
	public List<ModelCarYear> getCarYearListBySerise(String carId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "select id, car_id, year, name, short_name, maintain_path from car_year where enable=1 and car_id='"
				+ carId + "' order by year";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCarYear>() {

			@Override
			public ModelCarYear mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelCarYear modelCarYear = new ModelCarYear();
				modelCarYear.setId(rs.getString("id"));//
				modelCarYear.setCarId(rs.getString("car_id"));//
				modelCarYear.setYear(rs.getString("year"));
				modelCarYear.setName(rs.getString("name"));//
				modelCarYear.setShortName(rs.getString("short_name"));//
				modelCarYear.setMaintainPath(rs.getString("maintain_path"));//
				return modelCarYear;
			}

		});
	}

}
