package com.chedb.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.dao.impl.UserDaoImpl;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelSysItem;
import com.chedb.service.ProviderItemService;
import com.chedb.service.ProviderService;
import com.chedb.service.SystemItemService;
import com.chedb.service.UserService;
import com.chedb.util.FileData;
import com.chedb.util.RequestUtils;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Resource(name = "userServiceimpl")
	private UserService userService;
	@Resource(name = "providerServiceImpl")
	private ProviderService providerService;
	@Resource(name = "systemItemServiceimpl")
	private SystemItemService systemItemService;
	@Resource(name = "providerItemServiceImpl")
	private ProviderItemService providerItemService;

	@RequestMapping(value = { "/summary.do" })
	@ResponseBody
	public String summary(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("商家摘要信息，可以填写以下内容：<br/>");
		buffer.append("1、行业资质，如类别；<br/>");
		buffer.append("2、占地面积，营业时间；<br/>");
		buffer.append("3、创建时间，注册资金；<br/>");
		buffer.append("4、服务宗旨等；<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/phone.do" })
	@ResponseBody
	public String phone(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("商家电话，一定要填，车主需要电话咨询。");
		return buffer.toString();
	}

	@RequestMapping(value = { "/remark.do" })
	@ResponseBody
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("商家说明，可以填写背景信息，服务内容等，如：<br/>");
		buffer.append("1、员工人数，各级技工人数；<br/>");
		buffer.append("2、主要服务品牌，如大众、别克；<br/>");
		buffer.append("3、硬件设施介绍，如休息室、停车场等；<br/>");
		buffer.append("4、维修设备介绍等；<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/addrNotDw.do" })
	@ResponseBody
	public String addrNotDw(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("你还没有为自己的店定位，这样车主在地图上看不到你，根据距离查询时也查不到你。<br/>");
		buffer.append("1、你首先要在自己的店里；<br/>");
		buffer.append("2、点击“在地图上定位”按钮；<br/>");
		buffer.append("3、点击“保存”就可以了；<br/>");
		buffer.append("<br/>");
		buffer.append("软件会自动根据你所在的位置在地图上做标记；<br/>");
		buffer.append("以上操作只需要做一次，除非你的店搬家了；<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/getImage.do" })
	@ResponseBody
	public String getImage(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgId = req.getParameter("imgId");
		String ss = req.getContextPath();
		StringBuffer buffer = new StringBuffer();
		if (imgId.equals("-1")) {
			buffer.append("当前没有任何商家图片。<br/>");
			buffer.append("上传图片可以提高商家形象。可以上传店面外观、车间、员工合影、员工工作照、先进设备、行业资质等照片。<br/>");
			buffer.append("最多上传8张图片。<br/>");
			buffer.append("<br/>点击右上角的“+”按钮添加照片。<br/>");
			return "";
		}
		// 得到图片的真实路径
		// String imagePath1 = "D:\\pic\\102"+imgId+".jpg";
		String imagePath = ss + "/img/provider/" + providerId + "/" + imgId
				+ ".jpg";
		System.out.println("商家图片：" + imagePath);
		return sendImgFile(imagePath);
	}

	@RequestMapping(value = { "/queryProviderList.do" })
	@ResponseBody
	public List<ModelProvider> queryProviderList(HttpServletRequest req)
			throws Exception {
		String level = req.getParameter("level");
		String sort = req.getParameter("sort");
		String priceStart = req.getParameter("priceStart");
		String priceEnd = req.getParameter("priceEnd");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String strItemIdList = req.getParameter("strItemIdList");
		return this.providerService.getProviderList(level, strItemIdList, sort,
				priceStart, priceEnd, latitude, longitude);
	}

	@RequestMapping(value = { "/queryProviderListBySearch.do" })
	@ResponseBody
	public List<ModelProvider> queryProviderListBySearch(HttpServletRequest req)
			throws Exception {
		String searchStr = req.getParameter("searchStr");
		return providerService.getProviderListBySearch(searchStr);
	}

	@RequestMapping(value = { "/queryProviderImg.do" })
	@ResponseBody
	public String queryProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgId = req.getParameter("imgId");
		String s = req.getSession().getServletContext().getRealPath("/");
		String imagePath = s + "img/provider/" + providerId + "/" + imgId
				+ ".jpg";
		String jsonStr = FileData.SendFileImg(imagePath, 120, true);
		return jsonStr;
	}

	@RequestMapping(value = { "/unloadProviderImg.do" })
	@ResponseBody
	public String unloadProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgString = req.getParameter("imgString");
		String s = req.getSession().getServletContext().getRealPath("/");
		String providerPath = s + "img/provider/" + providerId + "/";
		FileData.dirCreate(providerPath);// 创建图片所在的目录
		String imgId = this.providerService
				.getImgIdAndUpdateImgList(providerId);// 访问数据库得到
		String imagePath = providerPath + imgId + ".jpg";
		boolean ret = FileData.writeFileImg(imagePath, imgString);
		String jsonStr = "failed";
		if (ret == true) {
			jsonStr = imgId;
		}
		return jsonStr;
	}

	/**
	 * 修改图片位置，删除图片
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/editProviderImg.do" })
	@ResponseBody
	public String editProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String oldImgIdList = req.getParameter("oldImgIdList");
		String imgId = req.getParameter("imgId");
		String edittype = req.getParameter("edittype");// 修改类型：1后移，0删除，-1前移
		String jsonStr = "failed";
		String ret = this.providerService.editImgSpace(providerId,
				oldImgIdList, imgId, edittype);// 访问数据库得到
		if (ret != null && edittype.equals("0")) {// 删除图片
			// 得到图片的真实路径
			String s = req.getSession().getServletContext().getRealPath("/");
			String imagePath = s + "img/provider/" + providerId + "/" + imgId
					+ ".jpg";
			FileData.delFileImg(imagePath);
		}
		if (ret != null) {
			jsonStr = ret;
		}
		System.out.println("editProviderImg:" + jsonStr);
		return jsonStr;
	}

	@RequestMapping(value = { "/queryProviderByUserId.do" })
	@ResponseBody
	public ModelProvider queryProviderByUserId(HttpServletRequest req)
			throws Exception {
		String userId = req.getParameter("userId");
		ModelProvider provider = this.providerService.queryProviderById(userId);
		return provider;
	}

	@RequestMapping(value = { "/queryProviderById.do" })
	@ResponseBody
	public ModelProvider queryProviderById(HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		String providerId = req.getParameter("providerId");
		String showMap = req.getParameter("showMap");
		// String jsonStr = "failed";
		ModelProvider provider = this.providerService
				.queryProviderById(providerId);
		String appId = req.getParameter("appId");
		if (appId != null && appId.length() > 0) {
//			UserDaoImpl userDaoImpl = new UserDaoImpl();
//			userDaoImpl.addUserConfig("3002", appId, providerId);
			userService.addUserConfig("3002", appId, providerId);
		}
		if(showMap==null)
		     this.providerService.appendBrowseCount(providerId);
		if(RequestUtils.getReqJsonp(req,res,provider)){
			  return null;
	    }
		return provider;
	}

	@RequestMapping(value = { "/appendProviderBrowse.do" })
	@ResponseBody
	public String appendProviderBrowse(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		System.out.println("ProviderServlet: providerId=" + providerId);
		String jsonStr = "failed";
		if (providerService.appendBrowseCount(providerId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping(value = { "/modifyProviderInfo.do" })
	@ResponseBody
	public String modifyProviderInfo(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String infoType = req.getParameter("infoType");
		String content = req.getParameter("content");
		String jsonStr = "failed";
		try{
		if (providerService.update(providerId, infoType, content) == true) {
			jsonStr = "success";
		}
		}catch(Exception ex){
			 jsonStr = "failed";
		}
		return jsonStr;
	}

	private String sendImgFile(String imgfile) throws ServletException,
			IOException {
		// 测试git同步
		String con = "<html><body style='background-color: transparent;'>"
				+ "<div style='text-align:center'><img src='" + imgfile
				+ "'></div>" + "</body></html>";
		return con;
	}

	@RequestMapping("/providerindex.do")
	public ModelAndView providerindex(HttpServletRequest req, ModelMap map)
			throws Exception {
		List<ModelSysItem> items = systemItemService.getSysItemClass("1");
		// 获取跟每个大类对应的明细
		for (ModelSysItem modelSysItem : items) {
			List<ModelSysItem> childitems = this.systemItemService
					.getSysItemByClassId(modelSysItem.getLabelId());
			modelSysItem.setChilditems(childitems);
		}
		map.put("items", items);
		JSONObject json = new JSONObject();
		json.put("items", items);
		map.put("serviceItems", json.toString());
		return new ModelAndView("searchservice", map);
	}

	@RequestMapping("/providerdetail.do")
	public ModelAndView providerdetail(HttpServletRequest req, ModelMap map)
			throws Exception {
		String providerId = req.getParameter("providerId");
		ModelProvider modelProvider = this.providerService
				.queryProviderById(providerId);
		if (modelProvider.getImgIdListStr() != null
				&& !modelProvider.getImgIdListStr().equalsIgnoreCase("")) {
			String imgpaths[] = modelProvider.getImgIdListStr().split(",");
			List<String> imgsrcs = Arrays.asList(imgpaths);
			map.put("imgsrcs", imgsrcs);
		}
		map.put("modelProvider", modelProvider);
		// 获取此商家能够提供的服务列表
		List<ModelSysItem> items = this.providerItemService
				.getProviderItemBySysItemId(providerId, null);
		map.put("items", items);
		return new ModelAndView("servicedetail", map);
	}
	@RequestMapping(value = { "/queryProviderListForSearch.do" })
	public ModelAndView queryProviderListForSearch(HttpServletRequest req,
			ModelMap map) throws Exception {
		String leve = req.getParameter("level");
		String strSysItemList = req.getParameter("strSysItemList");
		String sort = req.getParameter("sort");
		String priceStart = req.getParameter("priceStart");
		String priceEnd = req.getParameter("priceEnd");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		ModelSysItem modelSysItem = systemItemService
				.getSysItemById(strSysItemList);
		List<ModelProvider> providers = providerService
				.getProviderList(leve, strSysItemList, sort, priceStart,
						priceEnd, latitude, longitude);
		map.put("providers", providers);
		if (priceEnd == null) {
			priceEnd = "";
		}
		if (priceStart == null) {
			priceStart = "";
		}
		String pricestr = "";
		if (!priceEnd.equalsIgnoreCase("") && priceStart.equalsIgnoreCase("")) {
			pricestr = "价格低于" + priceEnd + "元,";
		} else if (!priceEnd.equalsIgnoreCase("")
				&& !priceStart.equalsIgnoreCase("")) {
			pricestr = "价格介于" + priceStart + "-" + priceEnd + "元,";
		} else if (priceEnd.equalsIgnoreCase("")
				&& !priceStart.equalsIgnoreCase("")) {
			pricestr = "价格高于" + priceStart + "元,";
		}
		map.put("strSysItemList", req.getParameter("strSysItemList"));
		map.put("level", leve);
		map.put("sort", sort);
		map.put("itemname", modelSysItem.getTitle());
		JSONObject json = new JSONObject();
		json.put("items", providers);
		map.put("serviceItems", json.toString());
		map.put("pricestr", pricestr);
		return new ModelAndView("servicelist", map);
	}

	@RequestMapping(value = { "/providerimglist.do" })
	public ModelAndView providerimglist(HttpServletRequest req, ModelMap map)
			throws Exception {
		String providerId = req.getParameter("providerId");
		ModelProvider modelProvider = this.providerService
				.queryProviderById(providerId);
		if (modelProvider.getImgIdListStr() != null
				&& !modelProvider.getImgIdListStr().equalsIgnoreCase("")) {
			String imgpaths[] = modelProvider.getImgIdListStr().split(",");
			List<String> imgsrcs = Arrays.asList(imgpaths);
			map.put("imgsrcs", imgsrcs);
		}
		map.put("modelProvider", modelProvider);
		return new ModelAndView("providerimglist", map);
	}

	@RequestMapping(value = { "/locateprovider.do" })
	public ModelAndView locateprovider(HttpServletRequest req, ModelMap map)
			throws Exception {
		String providerid = req.getParameter("providerid");
		ModelProvider modelProvider = this.providerService
				.queryProviderById(providerid);
		map.put("modelProvider", modelProvider);
		return new ModelAndView("locatprovider", map);

	}
}
