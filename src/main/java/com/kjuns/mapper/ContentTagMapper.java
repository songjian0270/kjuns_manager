package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.ContentTag;

public interface ContentTagMapper extends Serializable {
	
	List<ContentTag> queryContentTagForContentId(@Param("contentId")String contentId);
	
	void addContentTag(ContentTag contentTag);
	
	void deleteContentTag(ContentTag contentTag);
	
	void updateContentTag(ContentTag contentTag);

}
