package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.model.ModelServiceClass;
import com.chedb.service.ServiceClassService;
import com.chedb.util.RequestUtils;

@Controller
public class ServiceClassController {
	@Resource(name = "serviceClassServiceImpl")
	private ServiceClassService serviceClassService;

	@RequestMapping("/queryServiceClassInfo.do")
	@ResponseBody
	public ModelServiceClass queryServiceClassInfo(HttpServletRequest req,HttpServletResponse res,String serviceClassId)
			throws Exception {
		ModelServiceClass modeServiceClass = serviceClassService.queryServiceClassById(serviceClassId);
		if(RequestUtils.getReqJsonp(req,res,modeServiceClass)){
			  return null;
 	    }
		return modeServiceClass;
	}
	
	@RequestMapping("/queryAllServiceClass.do")
	@ResponseBody
	public List<ModelServiceClass> queryAllServiceClass(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		List<ModelServiceClass> modelServiceClasses = serviceClassService.queryAllServiceClass();
		if(RequestUtils.getReqJsonp(req,res,modelServiceClasses)){
			  return null;
	    }
		return modelServiceClasses;
	}
}
