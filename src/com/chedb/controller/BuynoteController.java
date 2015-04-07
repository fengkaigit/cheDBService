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

import com.chedb.model.ModelBusinote;
import com.chedb.model.ModelUser;
import com.chedb.service.BuynoteService;
import com.chedb.util.RequestUtils;

@Controller
public class BuynoteController {
	@Resource(name = "buynoteServiceImpl")
	private BuynoteService buynoteService;

	@RequestMapping("/queryBuynoteList.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteList(HttpServletRequest req)
			throws Exception {
		String classId = req.getParameter("classId");
		String status = req.getParameter("status");
		String providerId = req.getParameter("providerId");
		String userId = req.getParameter("userId");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");

		System.out.println("queryBuynoteList: providerId=" + providerId
				+ ", status=" + status + ",classId=" + classId + ", userId="
				+ userId + ", startTime=" + startTime + ", endTime=" + endTime);

		return this.buynoteService.queryBuynoteList(classId, status,
				providerId, userId, startTime, endTime);

	}

	@RequestMapping("/queryBuynoteByUserId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByUserId(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String userId = req.getParameter("userId");
		String page = req.getParameter("page");
		List<ModelBusinote> modelBusinotes = this.buynoteService.queryBuynoteByUserId(userId,
				Integer.parseInt(page));
		if(RequestUtils.getReqJsonp(req,res,modelBusinotes)){
			  return null;
	    }
		return modelBusinotes;
	}

	@RequestMapping("/queryBuynoteByProviderId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByProviderId(HttpServletRequest req)
			throws Exception {
		String providerId = req.getParameter("providerId");
		String page = req.getParameter("page");
		return this.buynoteService.queryBuynoteByProviderId(providerId,
				Integer.parseInt(page));
		// return new ArrayList<ModelBusinote>();
	}

	@RequestMapping("/queryBusinoteById.do")
	@ResponseBody
	public ModelBusinote queryBusinoteById(HttpServletRequest req)
			throws Exception {
		String busiNo = req.getParameter("busiNo");
		ModelBusinote businote = buynoteService.queryBusinoteById(busiNo);
		return businote;
	}

	@RequestMapping("/updateBusinoteInfo.do")
	@ResponseBody
	public String updateBusinoteInfo(HttpServletRequest req,HttpServletResponse res) {
		String busiNo = req.getParameter("busiNo");
		String newStatus = req.getParameter("newStatus");
		String score = req.getParameter("score");
		String jsonStr = "failed";
		try {
			this.buynoteService.updateBusinoteInfo(busiNo, newStatus, score);
			jsonStr = "success";
		} catch (Exception ex) {
			jsonStr = "failed";
		}
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/buyService.do")
	@ResponseBody
	public String buyService(HttpServletRequest req,HttpServletResponse res) {
		String buynoteStr = req.getParameter("buynoteStr");
		String jsonStr = "failed";
		try {
			this.buynoteService.buyService(buynoteStr);
			jsonStr = "success";
		} catch (Exception ex) {
			jsonStr = "failed";
		}
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/queryBuynoteListByUserId.do")
	public ModelAndView queryBuynoteListByUserId(HttpServletRequest req,
			ModelMap map) throws Exception {
		Object obj = req.getSession().getAttribute("sessionuser");
		if (obj == null) {
			return new ModelAndView("redirect:myself.do");
		}
		ModelUser user = (ModelUser) req.getSession().getAttribute(
				"sessionuser");
		if (user.getLevel() != 1) {
//			return new ModelAndView("queryBuynoteListByUserId");
			req.getSession().removeAttribute("sessionuser");
			return new ModelAndView("redirect:myself.do");
		}
		String userId = user.getNo();
		String page = req.getParameter("page");
		if (page == null || page.equalsIgnoreCase("")) {
			page = "1";
		}
		List<ModelBusinote> modelBusinotes = this.buynoteService
				.paingqueryBuynote(userId, Integer.parseInt(page), 1);
		Integer rowcount = this.buynoteService.rowcount(userId, 1);
		Integer pagecount = rowcount / 10;
		if (rowcount % 10 != 0) {
			pagecount = pagecount + 1;
		}
		if (pagecount == Integer.parseInt(page)) {
			map.put("endpage", 1);
			map.put("page", Integer.parseInt(page));
		} else {
			map.put("page", Integer.parseInt(page) + 1);
			map.put("endpage", 0);
		}
		map.put("modelBusinotes", modelBusinotes);

		return new ModelAndView("myorderlist", map);
	}

	@RequestMapping("/queryBuynoteListByProviderId.do")
	public ModelAndView queryBuynoteListByProviderId(HttpServletRequest req,
			ModelMap map) throws Exception {
		Object obj = req.getSession().getAttribute("sessionuser");
		if (obj == null) {
			return new ModelAndView("redirect:sailermyself.do");
		}
		ModelUser user = (ModelUser) req.getSession().getAttribute(
				"sessionuser");
		String userId = user.getNo();
		if (user.getLevel() != 2) {
			req.getSession().removeAttribute("sessionuser");
			return new ModelAndView("redirect:sailermyself.do");
		}
		String page = req.getParameter("page");
		if (page == null || page.equalsIgnoreCase("")) {
			page = "1";
		}
		List<ModelBusinote> modelBusinotes = this.buynoteService
				.paingqueryBuynote(userId, Integer.parseInt(page), 2);
		for (ModelBusinote modelBusinote : modelBusinotes) {
			if (modelBusinote.getStatus() == 1) {
				String buynoteNo = modelBusinote.getBuynoteNo();
				// String tailno=buynoteNo.split("-")[2];
				buynoteNo = buynoteNo.split("-")[0] + "-"
						+ buynoteNo.split("-")[1] + "-???";
				modelBusinote.setTempbuynoteNo(buynoteNo);
			}
		}
		Integer rowcount = this.buynoteService.rowcount(userId, 2);
		Integer pagecount = rowcount / 10;
		if (rowcount % 10 != 0) {
			pagecount = pagecount + 1;
		}
		if (pagecount == Integer.parseInt(page)) {
			map.put("endpage", 1);
			map.put("page", Integer.parseInt(page));
		} else {
			map.put("page", Integer.parseInt(page) + 1);
			map.put("endpage", 0);
		}
		// 对订单号进行特殊处理
		map.put("modelBusinotes", modelBusinotes);
		map.put("userId", userId);
		return new ModelAndView("sellerorderlist", map);
	}
}
