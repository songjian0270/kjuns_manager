package com.kjuns.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.ContentType;
import com.kjuns.service.TypeService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  类别
 * @author James
 * @date 2016-07-22
 * @file TypeController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/type")
public class TypeController extends BaseController {
	
	@Resource
	private TypeService typeService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String name, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = typeService.queryTypeList(name, page);
		map.addAttribute("page", page);
		map.addAttribute("name", name);
		return "type/typeList";
	}
	
	@RequestMapping(value="select", method= RequestMethod.GET)
	public void select(Model model){
		List<Map<String, Object>> param = typeService.queryTypeList();
		sendResponseContent(model, param);
	}
	
	/**
	 * type修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		ContentType contentType = new ContentType();
		if (CommonUtils.notEmpty(id)) {
			contentType = typeService.selectById(id);
		} 
		map.addAttribute("type", contentType);
		return "type/typeEdit";
	}
	
	/**
	 * 增加类别
	 * @param tag
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addType")
	public String addType(ContentType type, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		type.setCreateBy(my.getId());
		type.setUpdateBy(my.getId());
		if (typeService.addType(type) == -1) {
			map.addAttribute("message", "类别已存在");
		}
		return list(null, 1, map);
	}
	
	/**
	 * 删除类别
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(ContentType type, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		type.setCreateBy(my.getId());
		type.setUpdateBy(my.getId());
		typeService.deleteType(type);
		return list(type.getName(), 1, map);
	}
	
}
