package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelService;
import com.chedb.service.CarService;
import com.chedb.service.ServiceIA;
import com.chedb.util.RequestUtils;

@Controller
public class CarController {
	@Resource(name = "carServiceImpl")
	private CarService carService;
	@Resource(name = "serviceImpl")
	private ServiceIA ServiceIA;

	@RequestMapping("/queryCarBrandList.do")
	@ResponseBody
	public List<ModelCarBrand> queryCarBrandList(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		List<ModelCarBrand> modelCarBrands = carService.getCarBrandList();
		if(RequestUtils.getReqJsonp(req,res,modelCarBrands)){
			  return null;
	    }
		return modelCarBrands;
	}

	@RequestMapping("/queryCarSeriseListByBrand.do")
	@ResponseBody
	public List<ModelCarSerise> queryCarSeriseListByBrand(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String breadId = req.getParameter("breadId");
		List<ModelCarSerise> modelCarSerises = carService.getCarSeriseListByBrand(breadId);
		if(RequestUtils.getReqJsonp(req,res,modelCarSerises)){
			  return null;
	    }
		return modelCarSerises;
	}

	@RequestMapping("/queryCarListBySerise.do")
	@ResponseBody
	public List<ModelCar> queryCarListBySerise(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String seriseId = req.getParameter("seriseId");
		List<ModelCar> modelCars = carService.getCarListBySerise(seriseId);
		if(RequestUtils.getReqJsonp(req,res,modelCars)){
			  return null;
	    }
		return modelCars;
	}

	@RequestMapping("/queryCarYearListBySerise.do")
	@ResponseBody
	public List<ModelCarYear> queryCarYearListBySerise(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String carId = req.getParameter("carId");
		List<ModelCarYear> modelCarYears = carService.getCarYearListBySerise(carId);
		if(RequestUtils.getReqJsonp(req,res,modelCarYears)){
			  return null;
	    }
		return modelCarYears;
	}

	@RequestMapping("/carListBySerise.do")
	public ModelAndView carcleanindex(HttpServletRequest req, ModelMap map)
			throws Exception {
		String seriseId = req.getParameter("seriseId");
		String serviceClassId = req.getParameter("classId");
		List<ModelCar> cars = carService.getCarListBySerise(seriseId);
		map.put("cars", cars);
		if(cars!=null&&!cars.isEmpty()){
			List<ModelService> modelServices = ServiceIA.getServiceListByClassId(serviceClassId, cars.get(0).getId());
			map.put("servicelist", modelServices);	
		}
		
		return new ModelAndView("carrepaire", map);
	}

}
