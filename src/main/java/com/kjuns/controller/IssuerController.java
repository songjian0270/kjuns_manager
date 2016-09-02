package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.UserInfo;
import com.kjuns.service.IssuerService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  发布人管理
 * @author James
 * @date 2016-07-22
 * @file UserInfoController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/issuer")
public class IssuerController extends BaseController {
	
	@Autowired
	private IssuerService issuerService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(UserInfo userInfo, int pageNumber, ModelMap map){
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = issuerService.queryIssuerList(userInfo.getNickName(), page);
		map.addAttribute("page", page);
		map.addAttribute("userInfo", userInfo);
		return "issuer/issuerList";
	}
	
	/**
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		UserInfo userInfo = new UserInfo();
		if (CommonUtils.notEmpty(id)) {
			userInfo = issuerService.selectById(id);
		} 
		map.addAttribute("userInfo", userInfo);
		return "issuer/issuerEdit";
	}
	
	/**
	 * 增加发布人
	 * @param tag
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addIssuer")
	public String addTag(UserInfo userInfo, ModelMap map, HttpSession session) {
		try {

		Admin my = this.getContent(session);
		userInfo.setCreateBy(my.getId());
		userInfo.setUpdateBy(my.getId());
		if (issuerService.addIssuer(userInfo) == -1) {
			map.addAttribute("message", "发布人昵称已存在");
		}
		userInfo = new UserInfo();
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		return list(userInfo, 1, map);
	}
	
	/**
	 * 停用
	 * @param programa
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(UserInfo userInfo, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		userInfo.setCreateBy(my.getId());
		userInfo.setUpdateBy(my.getId());
		issuerService.deleteIssuer(userInfo);
		return list(userInfo, 1, map);
	}
	
}
