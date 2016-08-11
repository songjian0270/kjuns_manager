package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.ContentType;

public interface ContentTypeMapper extends Serializable {
	
	int selectByName(@Param(value="name")String name);

	int getTotalCount(@Param(value="name")String name);

	List<ContentType> queryTypeList(@Param(value="name")String name, 
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);
	
	List<Map<String, Object>> querySelectList();

	ContentType selectById(String id);

	void deleteType(ContentType type);

	void addType(ContentType type);
	
	void updateType(ContentType type);
	
}
