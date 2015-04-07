package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.CarDao;
import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;
import com.chedb.service.CarService;

@Service("carServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CarServiceImpl implements CarService {
	@Resource(name = "carDaoImpl")
	private CarDao carDao;

	@Override
	public List<ModelCarBrand> getCarBrandList() throws Exception {
		// TODO Auto-generated method stub
		return carDao.getCarBrandList();
	}

	@Override
	public List<ModelCarSerise> getCarSeriseListByBrand(String breadid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.carDao.getCarSeriseListByBrand(breadid);
	}

	@Override
	public List<ModelCar> getCarListBySerise(String seriseId) throws Exception {
		// TODO Auto-generated method stub
		List<ModelCar> cars = this.carDao.getCarListBySerise(seriseId);
		return cars;
	}

	@Override
	public List<ModelCar> getDefaultCars(String carid) throws Exception {
		// TODO Auto-generated method stub
		List<ModelCar> cars = this.carDao.getDefaultCars(carid);
		for (ModelCar modelCar : cars) {
			if (modelCar.getId().equalsIgnoreCase(carid)) {
				modelCar.setSelected(1);
			} else {
				modelCar.setSelected(0);
			}
		}
		return cars;
	}

	@Override
	public List<ModelCarYear> getCarYearListBySerise(String carId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.carDao.getCarYearListBySerise(carId);
	}

}
