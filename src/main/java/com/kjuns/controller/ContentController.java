package com.kjuns.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Content;
import com.kjuns.model.UserInfo;
import com.kjuns.service.ContentService;
import com.kjuns.service.IssuerService;
import com.kjuns.service.TagService;
import com.kjuns.service.TypeService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  内容
 * @author James
 * @date 2016-07-22
 * @file ContentController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/content")
public class ContentController extends BaseController {
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private IssuerService issuerService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(String sectionId, String title, String nickName, String createDate, 
			int pageNumber, ModelMap map){
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = contentService.queryContentList(sectionId, title, nickName, createDate, page);
		List<Map<String, Object>> list = typeService.queryTypeList();
		map.addAttribute("typeList", list);
		map.addAttribute("page", page);
		map.addAttribute("title", title);
		map.addAttribute("nickName", nickName);
		map.addAttribute("createDate", createDate);
		map.addAttribute("sectionId", sectionId);
		return "content/contentList";
	}
	
	/**
	 * 修改内容
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, String sectionId, ModelMap map) {
		Content content = new Content();
		if (CommonUtils.notEmpty(id)) {
			content = contentService.selectById(id);
		} 
		content.setSectionId(sectionId);
		List<Map<String, Object>> list = typeService.queryTypeList();
		map.addAttribute("typeList", list);
		map.addAttribute("content", content);
		return "content/contentEdit";
	}
	
	/**
	 * 增加内容
	 * @param programa
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addContent")
	public String addContent(Content content, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		content.setCreateBy(my.getId());
		content.setUpdateBy(my.getId());
		if (contentService.addContent(content)== -1) {
			map.addAttribute("message", "名称已存在");
		}
		return list(content.getSectionId(), null, null, null, 1, map);
	}
	
	/**
	 * 删除内容
	 * @param programa
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Content content, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		content.setCreateBy(my.getId());
		content.setUpdateBy(my.getId());
		contentService.deleteContent(content);
		return list(content.getSectionId(), content.getTitle(),  content.getNickName(), content.getCreateDate(), 1, map);
	}
	
	@RequestMapping(value="relatedArticles/list", method= RequestMethod.GET)
	public String relatedArticlessList(String title, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = contentService.queryContentList( null,title, null, null, page);
		map.addAttribute("page", page);
		map.addAttribute("title", title);
		return "content/relatedArticlesList";
	}
	
	@RequestMapping(value="tag/list", method= RequestMethod.GET)
	public String tagList(String name, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = tagService.queryTagList(name, page);
		map.addAttribute("page", page);
		map.addAttribute("name", name);
		return "content/tagList";
	}
	
	@RequestMapping(value="issuer/list", method= RequestMethod.GET)
	public String issuerList(UserInfo userInfo, int pageNumber, ModelMap map){
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = issuerService.queryIssuerStopList(userInfo.getNickName(), page);
		map.addAttribute("page", page);
		map.addAttribute("userInfo", userInfo);
		return "content/issuerList";
	}
	
	@RequestMapping(value="pushNotifi", method= RequestMethod.GET)
	public String pushNotifi(String id, ModelMap map, HttpSession session){

		contentService.pushNotifi(id);
		return list(null,null,null,null, 1, map);
	}
	
}
