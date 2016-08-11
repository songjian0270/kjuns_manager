package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.AuthMapper;
import com.kjuns.mapper.RoleMapper;
import com.kjuns.model.Authority;
import com.kjuns.model.Role;
import com.kjuns.service.RoleService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private AuthMapper authMapper;

	@Override
	public Role selectById(Long id) {
		Role role = roleMapper.selectById(id);
		// 所有权限
		List<Authority> allAuths = authMapper.queryAllAuth();
		// 角色拥有的权限
		List<Authority> hasAuths = authMapper.queryRoleAuth(id);
		for (Authority a : allAuths) {
			for (Authority ha : hasAuths) {
				if (a.getId() == ha.getId()) {
					a.setSelected(true);
					break;
				}
			}
		}
		role.setAuths(allAuths);
		return role;
	}

	@Override
	public void deleteRole(Long id) {
		roleMapper.deleteRole(id);
		roleMapper.deleteRoleAuth(id);
		roleMapper.deleteAdminRole(id);
	}

	@Override
	public Page queryRoleList(Page page) {
		int total = roleMapper.getTotalCount();
		List<Role> roles = roleMapper.queryRoleList(page.getStart(), page.getPageSize());
		page.setTotalCount(total);
		page.setList(roles);
		return page;
	}

	@Override
	public synchronized int addRole(Role role) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		if (CommonUtils.notEmpty(role.getId())) {// 修改流程
			roleMapper.updateRole(role);
			roleMapper.deleteRoleAuth(role.getId());
		} else {
			Role r = roleMapper.selectByName(role.getName());
			if (r != null) {
				return -1;
			}
			role.setCreateDate(datetime);
			role.setUpdateDate(datetime);
			roleMapper.addRole(role);
		}
		// 添加关联关系
		if (role.getAuthIds() != null) {
			for (String authId : role.getAuthIds()) {
				roleMapper.addRoleAuth(role.getId(), Long.valueOf(authId));
			}
		}
		return 1;
	}

	@Override
	public List<Role> queryAllRole() {
		return roleMapper.queryAllRole();
	}

}
