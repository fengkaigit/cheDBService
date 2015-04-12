package com.chedb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.model.ModelCar;
import com.chedb.model.ModelCarBrand;
import com.chedb.model.ModelCarSerise;
import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelService;
import com.chedb.model.ModelServiceProvider;
import com.chedb.model.ModelUser;
import com.chedb.model.ModelUserAppraise;
import com.chedb.service.CarService;
import com.chedb.service.ServiceIA;
import com.chedb.util.GsonUtil;
import com.chedb.util.RequestUtils;
import com.chedb.util.StaticConstant;
import com.google.gson.reflect.TypeToken;
@Controller
public class ServiceController {
	@Resource(name = "serviceImpl")
	private ServiceIA ServiceIA;
	@Resource(name = "carServiceImpl")
	private CarService carService;

	@RequestMapping("/queryServiceList.do")
	@ResponseBody
	public List<ModelService> queryServiceList(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String carId = req.getParameter("carId");
		List<ModelService> modelServices = ServiceIA.getServiceListByClassId(
				serviceClassId, carId);
		if(RequestUtils.getReqJsonp(req,res,modelServices)){
			  return null;
	    }
		return modelServices;
	}

	@RequestMapping("/queryServiceProviderCount.do")
	@ResponseBody
	public String queryServiceProviderCount(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String jsonStr = "failed";
		String serviceId = req.getParameter("serviceId");
		int count = ServiceIA.getProviderCountByServiceId(serviceId);
		jsonStr = count + "";
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/queryServiceProviderList.do")
	@ResponseBody
	public List<ModelProvider> queryServiceProviderList(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String serviceId = req.getParameter("serviceId");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		List<ModelProvider> listProvider = ServiceIA
				.getProviderListByServiceId(serviceId, start, count, latitude,
						longitude);//
		if(RequestUtils.getReqJsonp(req,res,listProvider)){
			  return null;
	    }
		return listProvider;
	}

	@RequestMapping("/queryServiceAppraiseCount.do")
	@ResponseBody
	public String queryServiceAppraiseCount(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String jsonStr = "failed";
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		int count = ServiceIA.getAppraiseCountByServiceId(serviceClassId,
				serviceId);
		jsonStr = count + "";
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/serviceAppraiseDo.do")
	@ResponseBody
	public ModelUserAppraise serviceAppraiseDo(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		String content = req.getParameter("content");
		String userId = req.getParameter("userId");
		// boolean ok = daoImpl.doAppraise(serviceClassId, serviceId, content,
		// userId);
		// if (ok) {
		// jsonStr = "success";
		// }
		ModelUserAppraise app = ServiceIA.doAppraise(serviceClassId, serviceId,
				content, userId);
		if(RequestUtils.getReqJsonp(req,res,app)){
			  return null;
	    }
		return app;
	}

	@RequestMapping("/queryServiceAppraiseList.do")
	@ResponseBody
	public List<ModelUserAppraise> queryServiceAppraiseList(
			HttpServletRequest req,HttpServletResponse res) throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		List<ModelUserAppraise> listAppraise = ServiceIA
				.getAppraiseListByServiceId(serviceClassId, serviceId, start,
						count);//
		if(RequestUtils.getReqJsonp(req,res,listAppraise)){
			  return null;
	    }
		return listAppraise;
	}

	@RequestMapping("/queryServiceListByProvider.do")
	@ResponseBody
	public List<ModelServiceProvider> queryServiceListByProvider(HttpServletRequest req)
			throws Exception {
		// 根据商家查询服务信息
		String providerId = req.getParameter("providerId");
		List<ModelServiceProvider> listProvider = ServiceIA.getServiceListByProvider(providerId);
//					if (listProvider != null) {
//						jsonStr = GsonUtil.getGson().toJson(listProvider);
//					}
		return listProvider;
	}
	
	@RequestMapping("/saveServiceProviderLink.do")
	@ResponseBody
	public String saveServiceProviderLink(HttpServletRequest req)
			throws Exception {
		// 保存商家和服务的关联数据
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceIds = req.getParameter("serviceIds");
		String providerId = req.getParameter("providerId");
		List<String> serviceIdlist = GsonUtil.getGson().fromJson(serviceIds,
				new TypeToken<List<String>>() {
				}.getType());

		boolean ok = ServiceIA.saveServiceProviderLink(serviceIdlist, serviceClassId, providerId);//
		if (ok) {
			return "success";
		}
		return "failed";
	}
	
	@RequestMapping("/queryServiceInfo.do")
	@ResponseBody
	public ModelService queryServiceInfo(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String serviceId = req.getParameter("serviceId");
		ModelService service = ServiceIA.getServiceInfo(serviceId);
		if(RequestUtils.getReqJsonp(req,res,service)){
			  return null;
	    }
		return service;
	}

	@RequestMapping("/serviceindex.do")
	public ModelAndView carcleanindex(HttpServletRequest req, ModelMap map)
			throws Exception {
		// 选择默认车型
		String classId = req.getParameter("classId");
		String yearid = req.getParameter("yearid");
		String carId = req.getParameter("carId");
		List<ModelCarYear> returnyears = new ArrayList<ModelCarYear>();
		List<ModelCar> returncars = new ArrayList<ModelCar>();
		List<ModelCar> carserises = new ArrayList<ModelCar>();
		if (StaticConstant.SERVICE_CLASS03.equalsIgnoreCase(classId)
				|| StaticConstant.SERVICE_CLASS04.equalsIgnoreCase(classId)) {
			carserises = this.carService.getCarListBySerise(req
					.getParameter("carId"));
			for (ModelCar modelCarYear : carserises) {
				if (modelCarYear.getSeriseId().equalsIgnoreCase(carId)) {
					returncars.add(modelCarYear);
					modelCarYear.setSelected(1);
					yearid = modelCarYear.getSeriseId();
					break;
				}
			}
//			String lastselcarid = req.getParameter("lastselcarid");
//			List<ModelCar> selcarserises = this.carService
//					.getCarListBySerise(lastselcarid);
//			for (ModelCar modelCar : selcarserises) {
//				modelCar.setSelected(0);
//				returncars.add(modelCar);
//				break;
//			}

		} else {
			List<ModelCarYear> caryears = this.carService
					.getCarYearListBySerise(req.getParameter("carId"));
			// 从中最多选择2个
			for (ModelCarYear modelCarYear : caryears) {
				if (modelCarYear.getId().equalsIgnoreCase(yearid)) {
					returnyears.add(modelCarYear);
					modelCarYear.setSelected(1);
				}
			}
//			caryears = this.carService.getCarYearListBySerise(req
//					.getParameter("lastselcarid"));
//			for (ModelCarYear modelCarYear : caryears) {
//				if (!modelCarYear.getId().equalsIgnoreCase(yearid)) {
//					returnyears.add(modelCarYear);
//					break;
//				}
//			}
		}
		List<ModelService> modelServices = ServiceIA.getServiceListByClassId(
				req.getParameter("classId"), yearid);
		map.put("servicelist", modelServices);
		map.put("returncars", returncars);
		map.put("caryears", returnyears);
		map.put("classId", req.getParameter("classId"));
		map.put("view", req.getParameter("view"));
		// 查询车型
		return new ModelAndView(req.getParameter("view"), map);
	}

	@RequestMapping("/serviceItemDetail.do")
	public ModelAndView serviceItemDetail(HttpServletRequest req, ModelMap map) {
		try {
			String serviceClassId = req.getParameter("serviceClassId");
			String serviceId = req.getParameter("serviceId");
			String start = req.getParameter("start");
			String count = req.getParameter("count");
			String latitude = req.getParameter("latitude");
			String longitude = req.getParameter("longitude");
			ModelService modelService = this.ServiceIA
					.getServiceInfo(serviceId);
			map.put("modelService", modelService);
			// 转化为json
			JSONObject json = new JSONObject();
			json.put("serviceInfo", modelService);
			map.put("modelServiceInfo", json.toString());
			Integer serviveCount = this.ServiceIA
					.getProviderCountByServiceId(serviceId);
			map.put("serviveCount", serviveCount);
			// 查询提供此服务的供应商个数
			if (start == null || start.equalsIgnoreCase("")) {
				start = "0";
			}
			if (count == null || count.equalsIgnoreCase("")) {
				count = "5";
			}
			List<ModelProvider> providers = this.ServiceIA
					.getProviderListByServiceId(serviceId, start, count,
							latitude, longitude);
			int providercount = this.ServiceIA
					.getProviderCountByServiceId(serviceId);
			map.put("totalprovidercount", providercount);
			Integer currentProvidercount = Integer.parseInt(start);
			map.put("providercount", currentProvidercount + providers.size());
			map.put("providers", providers);
			map.put("serviceClassId", serviceClassId);
			map.put("serviceId", serviceId);
			List<ModelUserAppraise> apprsises = this.ServiceIA
					.getAppraiseListByServiceId(serviceClassId, serviceId,
							start, "5");
			Integer rowcoount = this.ServiceIA.getAppraiseCountByServiceId(
					serviceClassId, serviceId);
			map.put("apprsises", apprsises);
			map.put("rowcoount", rowcoount);
			int pagecount = rowcoount / 5;
			if (rowcoount % 5 != 0) {
				pagecount = pagecount + 1;
			}
			map.put("pagecount", pagecount);
			if (pagecount > 1) {
				map.put("endpage", 0);
				map.put("nextpage", 2);
			} else {
				map.put("endpage", 1);
				map.put("nextpage", 1);
			}
			map.put("appriseindex", apprsises.size());
			Integer startindex = Integer.parseInt(start);
			if (serviveCount > (startindex + 1) * 5) {
				startindex = (startindex + 1) * 5;
			}
			map.put("start", startindex);
			map.put("count", count);
			//
			Object obj = req.getSession().getAttribute("sessionuser");
			if (obj != null) {
				ModelUser user = (ModelUser) obj;
				map.put("userId", user.getNo());
				map.put("usertype", user.getLevel());
			} else {
				map.put("userId", null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("maintainitems", map);
	}

	@RequestMapping("/recommendService.do")
	@ResponseBody
	public List<ModelService> recommendService(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		List<ModelService> listAppraise = new ArrayList<ModelService>();
		String classId = req.getParameter("classId");
		String carId = req.getParameter("carId");
		String yearid = req.getParameter("yearid");
		String moverange = req.getParameter("moverange");
		try {
			listAppraise = ServiceIA.recommendService(classId, carId, yearid,
					Integer.parseInt(moverange));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if(RequestUtils.getReqJsonp(req,res,listAppraise)){
			  return null;
	    }
		return listAppraise;
	}

	@RequestMapping("/brandlist.do")
	public ModelAndView brandlist(HttpServletRequest req, ModelMap map)
			throws Exception {
		// 把上次选择的车型放进来
		String classId = req.getParameter("classId");
		String lastselcarid = req.getParameter("lastselcarid");
		List<ModelCarBrand> brands = this.carService.getCarBrandList();
		map.put("classId", classId);
		map.put("lastselcarid", lastselcarid);
		map.put("brandlist", brands);
		return new ModelAndView("carbrandlist", map);
	}

	@RequestMapping("/series.do")
	public ModelAndView series(HttpServletRequest req, ModelMap map)
			throws Exception {
		String classId = req.getParameter("classId");
		String lastselcarid = req.getParameter("lastselcarid");
		map.put("classId", classId);
		map.put("lastselcarid", lastselcarid);
		String brandid = req.getParameter("brandid");
		List<ModelCarSerise> series = this.carService
				.getCarSeriseListByBrand(brandid);
		map.put("series", series);
		return new ModelAndView("carseries", map);
	}

	@RequestMapping("/carlist.do")
	public ModelAndView carlist(HttpServletRequest req, ModelMap map)
			throws Exception {
		String serieid = req.getParameter("serieid");
		List<ModelCar> cars = this.carService.getCarListBySerise(serieid);
		String classId = req.getParameter("classId");
		String lastselcarid = req.getParameter("lastselcarid");
		map.put("classId", classId);
		map.put("lastselcarid", lastselcarid);
		map.put("cars", cars);
		return new ModelAndView("carlist", map);
	}

	@RequestMapping("/caryears.do")
	public ModelAndView caryears(HttpServletRequest req, ModelMap map)
			throws Exception {
		String carid = req.getParameter("carid");
		List<ModelCarYear> caryears = this.carService
				.getCarYearListBySerise(carid);
		String classId = req.getParameter("classId");
		String lastselcarid = req.getParameter("lastselcarid");
		map.put("classId", classId);
		map.put("carid", carid);
		map.put("lastselcarid", lastselcarid);
		map.put("caryears", caryears);
		return new ModelAndView("caryearlist", map);
	}
	@RequestMapping("/downapp.do")
	public ModelAndView downapp(HttpServletRequest req, ModelMap map)
			throws Exception {
		// 校验是微信打开还是浏览器打开！
		return new ModelAndView("downapp", map);
	}

}
