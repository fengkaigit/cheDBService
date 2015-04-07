package com.chedb.service;

import java.util.List;

import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;

public interface CarService {
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ModelCarBrand> getCarBrandList() throws Exception;

	/**
	 * 
	 * @param breadid
	 * @return
	 * @throws Exception
	 */
	public List<ModelCarSerise> getCarSeriseListByBrand(String breadid)
			throws Exception;

	/**
	 * 
	 * @param seriseId
	 * @return
	 * @throws Exception
	 */
	public List<ModelCar> getCarListBySerise(String seriseId) throws Exception;

	/**
	 * 选择默认车型
	 * 
	 * @param carid
	 * @return
	 * @throws Exception
	 */
	public List<ModelCar> getDefaultCars(String carid) throws Exception;

	/**
	 * 
	 * @param carId
	 * @return
	 * @throws Exception
	 */
	public List<ModelCarYear> getCarYearListBySerise(String carId)
			throws Exception;

}
