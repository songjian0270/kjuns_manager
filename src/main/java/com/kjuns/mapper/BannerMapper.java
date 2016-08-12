package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Banner;

public interface BannerMapper extends Serializable {

	int selectBytitle(@Param(value="title")String title);
	
	int getTotalCount(@Param(value="title")String title, @Param(value="content")String content);

	List<Banner> queryBannerList(@Param(value="title")String title, @Param(value="content")String content
			, @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

	Banner selectById(String id);

	void deleteBanner(Banner banner);
	
	void updateBanner(Banner banner);
	
	void addBanner(Banner banner);
	
}
