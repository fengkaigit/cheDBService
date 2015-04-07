package com.chedb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.chedb.controller.message.MessageUtil;
import com.chedb.controller.message.TextMessageResp;
import com.chedb.model.MenuEntity;
import com.chedb.service.MenuserviceI;

@Service("wechatService")
public class WechatService {
	@Resource(name = "menuserviceImpl")
	private MenuserviceI menuserviceI;

	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		TextMessageResp textMessage = new TextMessageResp();
		String fromUserName = "";
		String toUserName = "";
		try {

			String respContent = "";

			Map<String, String> requestMap = MessageUtil.parseXml(request);

			fromUserName = requestMap.get("FromUserName");
			String openid = requestMap.get("FromUserName");

			toUserName = requestMap.get("ToUserName");

			String msgType = requestMap.get("MsgType");
			String msgId = requestMap.get("MsgId");

			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			respMessage = MessageUtil.textMessageToXml(textMessage);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			}

			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

				String eventType = requestMap.get("Event");

				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				}

				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

				}

				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String key = requestMap.get("EventKey");

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("menuKey", key);
					List<MenuEntity> menuEntitys = this.menuserviceI
							.getMenusByParams(params);
					MenuEntity menuEntity = menuEntitys.get(0);
					if (menuEntity != null
							&& ((menuEntity.getTemplateId() != null && !""
									.equals(menuEntity.getTemplateId())) || menuEntity
									.getMsgType().equalsIgnoreCase("expand"))) {
						String type = menuEntity.getMsgType();
						if ("text".equals(type)) {
						} else if ("news".equals(type)) {
						} else if ("expand".equals(type)) {
							// TODO
							if (key.equalsIgnoreCase("queryPackageBalance")) {

							}
							textMessage.setContent("≤‚ ‘");
							respMessage = MessageUtil
									.textMessageToXml(textMessage);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(e.getMessage());
			return MessageUtil.textMessageToXml(textMessage);
		}
		return respMessage;
	}

}
