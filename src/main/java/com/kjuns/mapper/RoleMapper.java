package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import com.kjuns.model.Role;

public interface RoleMapper extends Serializable {

	Role selectByName(String roleName);

	int getTotalCount();

	List<Role> queryRoleList(int pageNo, int pageSize);

	Role selectById(Long id);

	void deleteRole(Long id);

	void deleteRoleAuth(Long roleId);

	void deleteAdminRole(Long roleId);

	void addRole(Role role);

	void addRoleAuth(Long roleId, Long authId);
	
	List<Role> queryAllRole();
	
	List<Role> queryAdminRole(Long adminId);
	
	void updateRole(Role role);
	
}
