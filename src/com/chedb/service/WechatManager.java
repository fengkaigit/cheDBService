package com.chedb.service;

import java.util.List;

import com.chedb.model.WechatAccountEntity;

public interface WechatManager {
	
	public WechatAccountEntity getWechatAccountEntity()
			throws Exception;

	/**
	 * 
	 * 
	 * @param mediaid
	 * @param author
	 * @param title
	 * @param contentsourceurl
	 * @param content
	 * @param digest
	 * @param showcoverpic
	 * @throws Exception
	 */
	public void addnewitem(String mediaid, String author, String title,
			String contentsourceurl, String content, String digest,
			Integer showcoverpic) throws Exception;

	/**
	 * 
	 * @param newsitemids
	 * @throws Exception
	 */
	public void uploadMapnews(List<String> newsitemids) throws Exception;
}
