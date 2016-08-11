package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.UserInfo;
import com.kjuns.service.UserInfoService;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  用户管理
 * @author James
 * @date 2016-07-22
 * @file UserInfoController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/user/info")
public class UserInfoController extends BaseController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(UserInfo userInfo, int pageNumber, ModelMap map){
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = userInfoService.queryUserInfoList(userInfo, page);
		map.addAttribute("page", page);
		map.addAttribute("userInfo", userInfo);
		return "user/userList";
	}
	
	/**
	 * 封号
	 * @param programa
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(UserInfo userInfo, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		userInfo.setCreateBy(my.getId());
		userInfo.setUpdateBy(my.getId());
		userInfoService.deleteUserInfo(userInfo);
		return list(userInfo, 1, map);
	}
	
}
