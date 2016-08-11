package com.kjuns.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
import com.kjuns.model.Authority;
import com.kjuns.model.Role;
import com.kjuns.service.AdminService;
import com.kjuns.service.AuthService;
import com.kjuns.service.RoleService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  后台登陆  
 * @author James
 * @date 2015-12-3
 * @file AdminController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="index", method= RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="toLogin", method= RequestMethod.GET)
	public String toLogin(){
		logger.info("进入方法，跳转>>>>>>>");
		return "login";
	}
	
	@RequestMapping(value="login", method= RequestMethod.POST)
	public String login(String username, String password, ModelMap map) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password));
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			map.addAttribute("username", username);
			map.addAttribute("error", true);
			return "login";
		}
		return "redirect:main";
	}
	
	@RequestMapping("logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "login";
	}
	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("top")
	public String top() {
		return "top";
	}

	@RequestMapping("left")
	public String left() {
		return "left";
	}

	@RequestMapping("index")
	public String index(ModelMap map) {
		return "index";
	}

	@RequestMapping("notAuth")
	public String notAuth() {
		return "notAuth";
	}
	
	/**
	 * 管理员列表
	 * @param pageNumber
	 * @param map
	 * @return
	 */
	@RequestMapping("adminList")
	public String adminList(Integer pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = adminService.queryAdminList(page);
		map.addAttribute("page", page);
		return "admin/adminList";
	}
	
	/**
	 * 增加管理员
	 * @param admin
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addAdmin")
	public String addAdmin(Admin admin, ModelMap map, HttpSession session) {
		Admin my = this.getContent(session);
		if (CommonUtils.notEmpty(admin.getUsername()) && 
				CommonUtils.notEmpty(admin.getId())) {
			map.addAttribute("message", "请输入用户名");
		} else {
			admin.setCreateBy(my.getId());
			admin.setUpdateBy(my.getId());
			if (adminService.addAdmin(admin) == -1) {
				map.addAttribute("message", "用户名已存在");
			}
		}
		return adminList(1, map);
	}
	
	/**
	 * 删除管理员信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Long id, ModelMap map) {
		Admin admin = adminService.selectById(id);
		if (admin != null) {
			if (admin.getUsername().equals("admin")) {
				map.addAttribute("message", "不能删除admin账户");
			} else {
				adminService.deleteAdmin(id);
			}
		}
		return adminList(1, map);
	}
	
	/**
	 * 修改管理员信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(Long id, ModelMap map) {
		Admin admin = new Admin();
		if (CommonUtils.notEmpty(id)) {
			admin = adminService.selectById(id);
		} else {
			admin.setRoles(roleService.queryAllRole());
		}
		map.addAttribute("admin", admin);
		return "admin/adminEdit";
	}

	/**
	 * 角色列表
	 * @param pageNumber
	 * @param map
	 * @return
	 */
	@RequestMapping("roleList")
	public String roleList(Integer pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = roleService.queryRoleList(page);
		map.addAttribute("page", page);
		return "admin/roleList";
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param map
	 * @return
	 */
	@RequestMapping("addRole")
	public String addRole(Role role, ModelMap map) {
		if (CommonUtils.notEmpty(role.getName())) {
			if (roleService.addRole(role) == -1) {
				map.addAttribute("message", "角色名已存在");
			}
		}
		return roleList(1, map);
	}

	/**
	 * 引导修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEditRole")
	public String toEditRole(Long id, ModelMap map) {
		Role role = new Role();
		if (CommonUtils.notEmpty(id)) {
			role = roleService.selectById(id);
		} else {
			role.setAuths(authService.queryAllAuth());
		}
		map.addAttribute("role", role);
		return "admin/roleEdit";
	}

	/**
	 * 删除角色
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("deleteRole")
	public String deleteRole(Long id, ModelMap map) {
		Role role = roleService.selectById(id);
		if (role != null) {
			if (role.getName().equals("超级管理员")) {
				map.addAttribute("message", "不能删除超级管理员角色");
			} else {
				roleService.deleteRole(id);
			}
		}
		return roleList(1, map);
	}
	
	/**
	 * 权限列表
	 * @param pageNumber
	 * @param map
	 * @return
	 */
	@RequestMapping("authList")
	public String authList(Integer pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = authService.queryAuthList(page);
		map.addAttribute("page", page);
		return "admin/authList";
	}
	
	/**
	 * 添加权限
	 * @param auth
	 * @param map
	 * @return
	 */
	@RequestMapping("addAuth")
	public String addAuth(Authority auth, ModelMap map) {
		if (CommonUtils.notEmpty(auth.getName())) {
			if (authService.addAuth(auth) == -1) {
				map.addAttribute("message", "权限名已存在");
			}
		}
		return authList(1, map);
	}

	/**
	 *  引导权限修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEditAuth")
	public String toEditAuth(Long id, ModelMap map) {
		Authority auth = new Authority();
		if (id != null) {
			auth = authService.selectById(id);
		}
		map.addAttribute("auth", auth);
		return "admin/authEdit";
	}

	/**
	 * 删除权限
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("deleteAuth")
	public String deleteAuth(Long id, ModelMap map) {
		authService.deleteAuth(id);
		return authList(1, map);
	}
	
	/**
	 * 跳转修改密码页面
	 * @return
	 */
	@RequestMapping("toUpdatePwd")
	public String toUpdatePwd() {
		return "updatePwd";
	}
	
	/**
	 * 修改密码
	 * @param oldPwd 
	 * @param password
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("updatePwd")
	public String updatePwd(String oldPwd, String password, ModelMap map, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("loginAdmin");
		boolean flag = adminService.updatePwd(admin.getId(), password, oldPwd);
		if (flag) {
			map.addAttribute("message", "修改成功");
			return this.logout();
		} else {
			map.addAttribute("message", "原密码不正确");
		}
		return "updatePwd";
	}
	
}
