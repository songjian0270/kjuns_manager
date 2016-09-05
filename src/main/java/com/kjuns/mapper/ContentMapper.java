package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Content;

public interface ContentMapper extends Serializable {
	
	int selectByTitle(@Param(value="title")String title);

	int getTotalCount(@Param(value="sectionId")String sectionId, @Param(value="title")String title, @Param(value="nickName")String nickName
			,@Param(value="createDate")String createDate);

	List<Content> queryContentList(@Param(value="sectionId")String sectionId, @Param(value="title")String title, 
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize, @Param(value="nickName")String nickName
			,@Param(value="createDate")String createDate);

	Content selectById(String id);

	void deleteContent(Content content);

	void addContent(Content content);
	
	void updateContent(Content content);
	
}
