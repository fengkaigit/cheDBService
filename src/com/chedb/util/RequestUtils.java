package com.chedb.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chedb.util.JsonUtil;

public class RequestUtils {
     public static boolean getReqJsonp(HttpServletRequest request,HttpServletResponse response,Object valueObj){
		String callback = request.getParameter("callback");
		if (callback != null && !callback.equals("")) {
			String json = JsonUtil.toJson(valueObj);
			try {
				PrintWriter out = response.getWriter();
				out.print(callback + "(" + json + ")");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
     }
}
