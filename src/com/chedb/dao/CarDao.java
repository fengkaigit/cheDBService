package com.chedb.dao;

import java.util.List;

import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;

//
public interface CarDao {

	public List<ModelCarBrand> getCarBrandList() throws Exception;

	public List<ModelCarSerise> getCarSeriseListByBrand(String brandId)
			throws Exception;

	public List<ModelCar> getCarListBySerise(String seriseId) throws Exception;

	public List<ModelCar> getDefaultCars(String carid) throws Exception;
	
	public List<ModelCarYear> getCarYearListBySerise(String carId)throws Exception;
}
