package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Section;
import com.kjuns.service.SectionService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  专栏
 * @author James
 * @date 2016-07-22
 * @file SectionController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/section")
public class SectionController extends BaseController {
	
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String title, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = sectionService.querySectionList(title, page);
		map.addAttribute("page", page);
		map.addAttribute("title", title);
		return "section/sectionList";
	}
	
	/**
	 * 修改专栏
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		Section section = new Section();
		if (CommonUtils.notEmpty(id)) {
			section = sectionService.selectById(id);
		} 
		map.addAttribute("section", section);
		return "section/sectionEdit";
	}
	
	/**
	 * 增加专栏
	 * @param tag
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addSection")
	public String addSection(Section section, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		section.setCreateBy(my.getId());
		section.setUpdateBy(my.getId());
		if (sectionService.addSection(section)== -1) {
			map.addAttribute("message", "专栏名称已存在");
		}
		return list(null, 1, map);
	}
	
	/**
	 * 删除专栏
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Section section, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		section.setCreateBy(my.getId());
		section.setUpdateBy(my.getId());
		sectionService.deleteSection(section);
		return list(section.getTitle(), 1, map);
	}
	
}
