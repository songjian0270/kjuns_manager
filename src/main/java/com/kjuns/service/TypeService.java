package com.kjuns.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.kjuns.model.ContentType;
import com.kjuns.util.pager.Page;

public interface TypeService extends Serializable {

	Page queryTypeList(String name, Page page);
	
	List<Map<String, Object>> queryTypeList();
	
	ContentType selectById(String id);

	void deleteType(ContentType type);

	int addType(ContentType type);

}
