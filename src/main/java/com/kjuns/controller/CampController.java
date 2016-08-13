package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Camp;
import com.kjuns.service.CampService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  阵营
 * @author James
 * @date 2016-07-22
 * @file CampController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/camp")
public class CampController extends BaseController {
	
	@Autowired
	private CampService campService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String title, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = campService.queryCampList(title, page);
		map.addAttribute("page", page);
		map.addAttribute("title", title);
		return "camp/campList";
	}
	
	/**
	 * camp修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		Camp camp = new Camp();
		if (CommonUtils.notEmpty(id)) {
			camp = campService.selectById(id);
		} 
		map.addAttribute("camp", camp);
		return "camp/campEdit";
	}
	
	/**
	 * 增加camp
	 * @param camp
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addCamp")
	public String addcamp(Camp camp, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		camp.setCreateBy(my.getId());
		camp.setUpdateBy(my.getId());
		if (campService.addCamp(camp) == -1) {
			map.addAttribute("message", "标题已存在");
		}
		return list(null, 1, map);
	}
	
	/**
	 * 删除camp
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Camp camp, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		camp.setCreateBy(my.getId());
		camp.setUpdateBy(my.getId());
		campService.deleteCamp(camp);
		return list(camp.getTitle(), 1, map);
	}
	
}
