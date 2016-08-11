package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Section;

public interface SectionMapper extends Serializable {
	
	int selectByTitle(@Param(value="title")String title);

	int getTotalCount(@Param(value="title")String title);

	List<Section> querySectionList(@Param(value="title")String title, 
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

	Section selectById(String id);

	void deleteSection(Section section);

	void addSection(Section section);
	
	void updateSection(Section section);
	
}
