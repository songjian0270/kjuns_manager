package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Tag;

public interface TagMapper extends Serializable {
	
	int selectByName(@Param(value="name")String name);

	int getTotalCount(@Param(value="name")String name);

	List<Tag> queryTagList(@Param(value="name")String name, 
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

	Tag selectById(String id);

	void deleteTag(Tag tag);

	void addTag(Tag tag);
	
	void updateTag(Tag tag);
	
}
