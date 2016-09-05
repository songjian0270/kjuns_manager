package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Content;
import com.kjuns.util.pager.Page;

public interface ContentService extends Serializable {

	Page queryContentList(String sectionId, String title, String nickName, String createDate, Page page);
	
	Content selectById(String id);

	void deleteContent(Content content);

	int addContent(Content content);
	
	void pushNotifi(String id);
}
