package com.chedb.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.model.AccessToken;
import com.chedb.model.WechatAccountEntity;
import com.chedb.service.WechatManager;
import com.chedb.util.WeixinUtil;

@Controller
public class Auth2Dispatcher {
	@Resource(name = "wechatManagerImpl")
	private WechatManager wechatManager;
	public static WechatAccountEntity defaultAccountEntity;

	/**
	 * 
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/auth2dispatcher.do")
	public ModelAndView auth2dispatcher(HttpServletRequest request,
			ModelMap model) {
		String code = request.getParameter("code");
		String redirecturl = request.getParameter("dispathurl");
		String dispatchurl = redirecturl + ".jhtml?param=1";
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramname = enumeration.nextElement();
			String paramvalue = request.getParameter(paramname);
			if (paramname.equalsIgnoreCase("dispathurl")) {
				continue;
			}
			if (paramname.equalsIgnoreCase("code")) {
				continue;
			}
			if (paramvalue != null && !paramvalue.equalsIgnoreCase("")) {
				dispatchurl = dispatchurl + "&" + paramname + "=" + paramvalue;
			}
		}
		//
		JSONObject userinfo = getUserinfoWithAuthCode(code);
		String openid = userinfo.getString("openid");
		dispatchurl = dispatchurl + "&openid=" + openid;
		dispatchurl = "redirect:" + dispatchurl;
		return new ModelAndView(dispatchurl, model);
	}

	/**
	 * 
	 * 
	 * @param authcode
	 * @return
	 */
	private JSONObject getUserinfoWithAuthCode(String authcode) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (defaultAccountEntity == null) {
				WechatAccountEntity wechatAccountEntity = wechatManager
						.getWechatAccountEntity();
				defaultAccountEntity = wechatAccountEntity;
			}
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
					+ defaultAccountEntity.getAccountappid()
					+ "&secret="
					+ defaultAccountEntity.getAccountappsecret()
					+ "&code="
					+ authcode + "&grant_type=authorization_code";

			jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);

			AccessToken accessToken = WeixinUtil.getAccessToken(
					defaultAccountEntity.getAccountappid(),
					defaultAccountEntity.getAccountappsecret());//
			String openid = (String) jsonObject.get("openid");
			jsonObject = getUserInfoWithOpenId(openid, accessToken);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 
	 * 
	 * @param openid
	 * @param accessToken
	 * @return
	 */
	private JSONObject getUserInfoWithOpenId(String openid,
			AccessToken accessToken) {
		String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ accessToken.getToken() + "&openid=" + openid + "&lang=zh_CN";
		JSONObject jsonObject = WeixinUtil.httpRequest(getUserInfoUrl, "GET",
				null);//
		return jsonObject;
	}
}
