package com.chedb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.model.ModelCarYear;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelUser;
import com.chedb.service.ProviderService;
import com.chedb.service.UserService;
import com.chedb.util.GsonUtil;
import com.chedb.util.RequestUtils;
import com.google.gson.reflect.TypeToken;

@Controller
public class UserController {
	@Resource(name = "userServiceimpl")
	private UserService userService;
	@Resource(name = "providerServiceImpl")
	private ProviderService providerService;

	@RequestMapping("/Login.do")
	@ResponseBody
	public ModelUser Login(HttpServletRequest req,HttpServletResponse res) throws Exception {
		boolean ok = false;
		String userNo = req.getParameter("userNo");
		String password = req.getParameter("password");
		String type = req.getParameter("type");
		System.out.println("UserServlet : userNo=" + userNo + ", password="
				+ password);
		String jsonStr = "failed";
		String appId = req.getParameter("appId");
		if (appId != null && !appId.equalsIgnoreCase("")) {
			userService.addUserConfig("2002", appId, userNo);
		}
		// 根据用户编码获得用户对象，然后根据密码参数对比，判断是否匹配
		ModelUser user = userService.getUser(userNo);
		if (user == null) {
			user = new ModelUser();
			user.setNo("-");
			jsonStr = "nouser";
		} else {
			if (!type.equalsIgnoreCase("" + user.getLevel())) {// 用户类型不同则不让登陆
				jsonStr = "nouser";
				user.setOk(ok);
				user.setLoginmess(jsonStr);
				if(RequestUtils.getReqJsonp(req,res,user)){
					return null;
				}
				return user;
			}
			if (user.getPasswd() == null) {
				if (password == null || password.equals("")) {
					req.getSession().setAttribute("sessionuser", user);
					ok = true;
				} else {
					jsonStr = "errorpasswd";
					user.setNo("+");
				}
			} else {
				if (user.getPasswd().equals(password) == false) {
					jsonStr = "errorpasswd";
					user.setNo("+");
				} else {
					// 给session添加信息
					req.getSession().setAttribute("sessionuser", user);
					ok = true;
				}
			}
		}
		user.setOk(ok);
		user.setLoginmess(jsonStr);
		if(RequestUtils.getReqJsonp(req,res,user)){
			  return null;
	    }
		return user;
	}

	@RequestMapping("/modifyUserInfo.do")
	@ResponseBody
	public String modifyUserInfo(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String userId = req.getParameter("userId");
		String infoType = req.getParameter("infoType");
		String content = req.getParameter("content");
		String jsonStr = "failed";
		System.out.println("UserServlet: userId=" + userId + ", infoType="
				+ infoType + ", content=" + content);

		if (userService.update(userId, infoType, content) == true) {
			jsonStr = "success";
		}
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;

	}

	@RequestMapping("/existApp.do")
	@ResponseBody
	public String existApp(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String appId = req.getParameter("appId");
		userService.addUserConfig("1002", appId, "");
		jsonStr = "success";
		return jsonStr;
	}

	@RequestMapping("/registerUser.do")
	@ResponseBody
	public String registerUser(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String jsonStr = "failed";
		String userInfo = req.getParameter("userInfo");
		ModelUser user = GsonUtil.getGson().fromJson(userInfo,
				new TypeToken<ModelUser>() {
				}.getType());

		System.out.println("UserServlet: userId=" + user.getNo());

		String appId = req.getParameter("appId");
		if (appId != null && !appId.equalsIgnoreCase("")) {
			userService.addUserConfig("2001", appId, user.getNo());
		}
		if (userService.register(user) == true) {
			jsonStr = "success";
			// 进行登录
			req.getSession().setAttribute("sessionuser", user);
		}
		if (user.getLevel() == 2) {
			// 如果是商家用户，则在商家表中添加一条记录
			ModelProvider t = new ModelProvider();
			t.setProviderId(user.getNo());
			t.setTitle("汽修服务店"); 
			// ProviderDaoImpl pdao = new ProviderDaoImpl();
			providerService.add(t);
		}
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/getUserNewNo.do")
	@ResponseBody
	public String getUserNewNo(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String jsonStr = "failed";
		String type = req.getParameter("type");
		String newId = userService.getNewId(Integer.parseInt(type));
		if (newId != null) {
			jsonStr = newId;
		}
		if(RequestUtils.getReqJsonp(req,res,jsonStr)){
			  return null;
	    }
		return jsonStr;
	}

	@RequestMapping("/getUserInfoByPhone.do")
	@ResponseBody
	public String getUserInfoByPhone(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String phone = req.getParameter("phone");
		ModelUser user = userService.getUserByPhone(phone);
		if (user != null) {
			jsonStr = "与 " + phone + " 绑定的用户登录账号为：" + user.getNo() + "，密码："
					+ user.getPasswd() + "。谢谢使用。【车大帮】";
		} else {
			jsonStr = "nouser";
		}
		return jsonStr;
	}

	@RequestMapping("/registerNewUser.do")
	@ResponseBody
	public ModelUser registerNewUser(HttpServletRequest req) throws Exception {
		String type = req.getParameter("type");
		String username = req.getParameter("userName");
		String passwd = req.getParameter("passwd");
		String phone = req.getParameter("phone");
		if (username==null) {
			username = "商家";
		}
		if (passwd == null) {
			passwd = "123456";
		}
		
		String newId = userService.getNewId(Integer.parseInt(type));
		ModelUser user = new ModelUser();
		user.setNo(newId);
		user.setName(username+"[" + newId+"]");
		user.setPasswd(passwd);
		user.setPhone(phone);
		user.setLevel(2);
		// GsonUtil.getGson().fromJson(userInfo,
		// new TypeToken<ModelUser>() {}.getType());

		System.out.println("UserServlet: userId=" + user.getNo());

		// String appId = req.getParameter("appId");
		// userDaoImpl.addUserConfig("2001", appId, user.getNo());

		if (userService.register(user) == true) {
			if (user.getLevel() == 2) {
				// 如果是商家用户，则在商家表中添加一条记录
				ModelProvider t = new ModelProvider();
				t.setProviderId(user.getNo());
				t.setTitle("汽修服务店");
				// ProviderDaoImpl pdao = new ProviderDaoImpl();
				// pdao.add(t);
				providerService.add(t);
			}
			return user;
		}
		return null;
	}

	@RequestMapping("/chooseCar.do")
	@ResponseBody
	public String chooseCar(HttpServletRequest req) throws Exception {
		String appId = req.getParameter("appId");
		String carId = req.getParameter("carId");
		String jsonStr = "failed";
		userService.addUserConfig("3001", appId, carId);

		if (userService.addChoosedCarByAppId(carId, appId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping("/queryChoosedCarItemByUser.do")
	@ResponseBody
	public List<ModelCarYear> queryChoosedCarItemByUser(HttpServletRequest req)
			throws Exception {

		String userId = req.getParameter("userId");
		String classId = req.getParameter("classId");

		String type = "1";
		if (classId.equals("02")) {
			type = "2";
		}
		List<ModelCarYear> listCar = userService.getChoosedCarItemByUser(
				userId, type);//
		// if (listCar != null) {
		// jsonStr = GsonUtil.getGson().toJson(listCar);
		// }

		// String userId = req.getParameter("userId");
		// String jsonStr = "failed";
		// List<ModelCar> listCar = userService.getChoosedCarByUser(userId);//
		// if (listCar != null) {
		// jsonStr = GsonUtil.getGson().toJson(listCar);
		// }
		// return jsonStr;
		return listCar;
	}

	@RequestMapping("/myself.do")
	public ModelAndView myself(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute("sessionuser");
		if (obj != null) {
			ModelUser user = (ModelUser) obj;
			if (user.getLevel() == 2) {
				req.getSession().removeAttribute("sessionuser");
				return new ModelAndView("redirect:sailermyself.do");
			} else {
				return new ModelAndView("redirect:queryBuynoteListByUserId.do");
			}

		}
		ModelUser modelUser = new ModelUser();
		modelUser.setName("111");
		modelUser.setPasswd("222222");
		JSONObject json = new JSONObject();
		json.put("items", modelUser);
		System.out.println(json.toString());
		return new ModelAndView("myself");
	}

	@RequestMapping("/sailermyself.do")
	public ModelAndView sailermyself(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute("sessionuser");
		if (obj != null) {
			return new ModelAndView("redirect:queryBuynoteListByProviderId.do");
		}
		ModelUser modelUser = new ModelUser();
		modelUser.setName("111");
		modelUser.setPasswd("222222");
		JSONObject json = new JSONObject();
		json.put("items", modelUser);
		System.out.println(json.toString());
		return new ModelAndView("sailermyself");
	}

	@RequestMapping("/regist.do")
	public ModelAndView regist(HttpServletRequest req) {
		return new ModelAndView("regist");
	}

	@RequestMapping("/binding.do")
	public ModelAndView binding(HttpServletRequest req, ModelMap map) {
		String openid = req.getParameter("openid");
		map.put("openid", openid);
		return new ModelAndView("binding", map);
	}

	@RequestMapping("/bindinguseraccount.do")
	@ResponseBody
	public Map<String, Object> bindinguseraccount(HttpServletRequest req)
			throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String userNo = req.getParameter("userNo");
		String password = req.getParameter("password");
		String openid = req.getParameter("openid");
		ModelUser user = userService.getUser(userNo);
		if (user == null) {
			returnMap.put("success", false);
			returnMap.put("mess", "用户名称不正确,无法绑定。");
		} else {
			if (user.getPasswd() == null) {
				if (password == null || password.equals("")) {
					// 登录成功
					this.userService.bindUser(userNo, openid);
				} else {
					// 登录失败
					returnMap.put("success", false);
					returnMap.put("mess", "用户名称或者密码不正确,无法绑定。");
				}
			} else {
				if (user.getPasswd().equals(password) == false) {
					returnMap.put("success", false);
					returnMap.put("mess", "用户名称或者密码不正确,无法绑定。");
					// 等失败
				} else {
					this.userService.bindUser(userNo, openid);
					// 登录成功
				}
			}
		}
		return returnMap;
	}

	@RequestMapping("/getUserjifen.do")
	@ResponseBody
	public ModelUser getUserjifen(HttpServletRequest req) throws Exception {
		ModelUser modelUser = (ModelUser) req.getSession().getAttribute(
				"sessionuser");
		ModelUser userinfo = this.userService.getUser(modelUser.getNo());
		return userinfo;
	}

}
