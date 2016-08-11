package com.kjuns.service;

import java.util.List;

import com.kjuns.model.Role;
import com.kjuns.util.pager.Page;

public interface RoleService {
	
	Role selectById(Long id);

	void deleteRole(Long id);

	Page queryRoleList(Page page);
	
	int addRole(Role role);
	
	List<Role> queryAllRole();
}
