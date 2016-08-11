package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Tag;
import com.kjuns.service.TagService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  标签
 * @author James
 * @date 2016-07-22
 * @file TagController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/tag")
public class TagController extends BaseController {
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String name, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = tagService.queryTagList(name, page);
		map.addAttribute("page", page);
		map.addAttribute("name", name);
		return "tag/tagList";
	}
	
	/**
	 * tag修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		Tag tag = new Tag();
		if (CommonUtils.notEmpty(id)) {
			tag = tagService.selectById(id);
		} 
		map.addAttribute("tag", tag);
		return "tag/tagEdit";
	}
	
	/**
	 * 增加标签
	 * @param tag
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addTag")
	public String addTag(Tag tag, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		tag.setCreateBy(my.getId());
		tag.setUpdateBy(my.getId());
		if (tagService.addTag(tag) == -1) {
			map.addAttribute("message", "标签已存在");
		}
		return list(null, 1, map);
	}
	
	/**
	 * 删除标签
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Tag tag, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		tag.setCreateBy(my.getId());
		tag.setUpdateBy(my.getId());
		tagService.deleteTag(tag);
		return list(tag.getName(), 1, map);
	}
	
}
