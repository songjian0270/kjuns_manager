package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Banner;
import com.kjuns.util.pager.Page;

public interface BannerService extends Serializable {

	Page queryBannerList(String title, String content, Page page);
	
	Banner selectById(String id);

	void deleteBanner(Banner banner);
	
	int addBanner(Banner banner);

}
