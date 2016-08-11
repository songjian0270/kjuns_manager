package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.AdminMapper;
import com.kjuns.mapper.RoleMapper;
import com.kjuns.model.Admin;
import com.kjuns.model.Role;
import com.kjuns.service.AdminService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private transient AdminMapper adminMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Admin queryByUsername(String username) {
		return adminMapper.selectByUsername(username);
	}

	@Override
	public Page queryAdminList(Page page) {
		int total = adminMapper.getTotalCount(0);
		List<Admin> admins = adminMapper.queryAdminList(page.getStart(), page.getPageSize());
		page.setTotalCount(total);
		page.setList(admins);
		return page;
	}

	@Override
	public Admin selectById(Long id) {
		Admin admin = adminMapper.selectById(id);
		List<Role> allRoles = roleMapper.queryAllRole();
		List<Role> hasRoles = roleMapper.queryAdminRole(id);
		for (Role r : allRoles) {
			for (Role hr : hasRoles) {
				if (r.getId().equals(hr.getId())) {
					r.setSelected(true);
					break;
				}
			}
		}
		admin.setRoles(allRoles);
		return admin;
	}

	@Override
	public void deleteAdmin(Long id) {
		adminMapper.deleteAdmin(id);
		adminMapper.deleteAdminRole(id);
	}

	@Override
	public int addAdmin(Admin admin) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		if (CommonUtils.notEmpty(admin.getId())) {// 修改流程
			adminMapper.deleteAdminRole(admin.getId());
		} else {
			Admin adm = adminMapper.selectByUsername(admin.getUsername());
			if (adm != null) {// 用户名存在了
				return -1;
			}
			admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
			admin.setCreateDate(datetime);
			admin.setUpdateDate(datetime);
			adminMapper.addAdmin(admin);
		}
		if (admin.getRoleIds() != null) {
			// 保存关联关系
			for (String roleId : admin.getRoleIds()) {
				adminMapper.addAdminRole(admin.getId(), roleId);
			}
		}
		return 1;
	}
	
	/**
	 * 修改密码
	 */
	public boolean updatePwd(Long id, String pwd, String oldPwd) {
		Admin newAdmin = adminMapper.selectById(id);
		if (newAdmin.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
			adminMapper.updatePwd(newAdmin.getId(), DigestUtils.md5Hex(pwd));
			return true;
		} else {
			return false;
		}
	}

}
