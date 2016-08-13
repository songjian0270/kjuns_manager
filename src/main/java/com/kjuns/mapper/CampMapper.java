package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Camp;

public interface CampMapper extends Serializable {
	
	int selectByTitle(@Param(value="title")String title);

	int getTotalCount(@Param(value="title")String title);

	List<Camp> queryCampList(@Param(value="title")String title, 
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

	Camp selectById(String id);

	void deleteCamp(Camp camp);

	void addCamp(Camp camp);
	
	void updateCamp(Camp camp);
	
}
