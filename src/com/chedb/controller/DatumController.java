package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.model.ModelDatum;
import com.chedb.model.ModelDatumLabel;
import com.chedb.service.DatumService;

@Controller
public class DatumController {
	@Resource(name = "datumServiceImpl")
	private DatumService datumService;

	@RequestMapping("/getDatumLabelListByPanretId.do")
	@ResponseBody
	public List<ModelDatumLabel> getDatumLabelListByPanretId(HttpServletRequest req)
			throws Exception {
		String hasDatum = req.getParameter("hasDatum");
		String panretId = req.getParameter("panretId");
		int has = Integer.parseInt(hasDatum);
		return datumService.getDatumLabelListByPanretId(has, panretId);
	}
	
	@RequestMapping("/getDatumListByLabelId.do")
	@ResponseBody
	public List<ModelDatum> getDatumListByLabelId(HttpServletRequest req)
			throws Exception {
		String labelId = req.getParameter("labelId");
		String pageNum = req.getParameter("pageNum");
		int num = Integer.parseInt(pageNum);
		return datumService.getDatumListByLabelId(labelId, num);
	}
}
