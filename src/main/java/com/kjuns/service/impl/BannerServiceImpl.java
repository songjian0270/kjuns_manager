package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.BannerMapper;
import com.kjuns.model.Banner;
import com.kjuns.service.BannerService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("bannerService")
public class BannerServiceImpl implements BannerService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public Page queryBannerList(String title, String content, Page page) {
		int total = bannerMapper.getTotalCount(title, content);
		List<Banner> banners = bannerMapper.queryBannerList(title, content, page.getStart(), page.getPageSize());
		for(Banner ber:banners){
			ber.setBackground(CommonUtils.getImage(ber.getBackground()));
		}
		page.setTotalCount(total);
		page.setList(banners);
		return page;
	}


	@Override
	public void deleteBanner(Banner banner) {
		bannerMapper.deleteBanner(banner);
	}

	public Banner selectById(String id) {
		Banner banner = bannerMapper.selectById(id);
		banner.setBackground(CommonUtils.getImage(banner.getBackground()));
		return banner;
	}
	
	@Override
	public int addBanner(Banner banner) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = bannerMapper.selectBytitle(banner.getTitle());
		if (count > 1) {
			return -1;
		}
		if (CommonUtils.notEmpty(banner.getId())) {// 修改流程
			banner.setUpdateDate(datetime);
			bannerMapper.updateBanner(banner);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			banner.setId(id);
			banner.setCreateDate(datetime);
			banner.setUpdateDate(datetime);
			bannerMapper.addBanner(banner);
		}
		return 1;
	}
	
}
