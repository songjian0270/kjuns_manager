package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Banner;
import com.kjuns.service.BannerService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  banner
 * @author James
 * @date 2016-07-22
 * @file TagController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String title,String content, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = bannerService.queryBannerList(title, content, page);
		map.addAttribute("page", page);
		map.addAttribute("title", title);
		map.addAttribute("content", content);
		return "banner/bannerList";
	}
	
	/**
	 * tag修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		Banner banner = new Banner();
		if (CommonUtils.notEmpty(id)) {
			banner = bannerService.selectById(id);
		} 
		map.addAttribute("banner", banner);
		return "banner/bannerEdit";
	}
	
	/**
	 * 增加Banner
	 * @param tag
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addBanner")
	public String addTag(Banner banner, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		banner.setCreateBy(my.getId());
		banner.setUpdateBy(my.getId());
		if (bannerService.addBanner(banner) == -1) {
			map.addAttribute("message", "bannner已存在");
		}
		return list(null, null, 1, map);
	}
	
	/**
	 * 删除Banner
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Banner banner, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		banner.setCreateBy(my.getId());
		banner.setUpdateBy(my.getId());
		bannerService.deleteBanner(banner);
		return list(banner.getTitle(), banner.getContent(), 1, map);
	}
	
}
