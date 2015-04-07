package com.chedb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import com.chedb.model.AccessToken;

/**
 * 
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	private static AccessToken staticAccessToken;
	private static ResourceBundle bundler = ResourceBundle
			.getBundle("resources/config");

	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();

				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static AccessToken getAccessToken(String appid, String appsecret) {
		if (staticAccessToken == null
				|| System.currentTimeMillis()
						- staticAccessToken.getAccesstime() >= staticAccessToken
							.getExpiresIn()) {// 没有或者超时则重新获取
			AccessToken accessToken = getToken(appid, appsecret);
			return accessToken;

		} else {
			return staticAccessToken;
		}

	}

	private static AccessToken getToken(String appid, String appsecret) {
		String requestUrl = access_token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		AccessToken accessToken = null;
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				accessToken.setAccesstime(System.currentTimeMillis());
				staticAccessToken = accessToken;
			} catch (Exception e) {
				accessToken = null;
			}
		}
		return accessToken;
	}

	public static String getAuth20Url(String appid, String directurl,
			Map<String, Object> params) throws Exception {
		String baseurl = bundler.getString("baseurl");
		if (baseurl == null) {
			baseurl = bundler.getString("baseurl");
		}
		String auth2url = baseurl + "/auth2dispatcher.do";
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			String paramname = iterator.next();
			if (index == 0) {
				auth2url = auth2url + "?" + paramname + "="
						+ params.get(paramname);
			} else {
				auth2url = auth2url + "&" + paramname + "="
						+ params.get(paramname);
			}
			index++;
		}

		if (index == 0) {
			auth2url = auth2url + "?dispathurl=" + directurl;
		} else {
			auth2url = auth2url + "&dispathurl=" + directurl;
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appid
				+ "&redirect_uri="
				+ URLEncoder.encode(auth2url, "utf-8")
				+ "&response_type=code&scope=snsapi_base&state=12#wechat_redirect";
		return url;
	}

}