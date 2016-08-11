package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import com.kjuns.model.Admin;

public interface AdminMapper extends Serializable {

	Admin selectByUsername(String username);

	int getTotalCount(int type);

	List<Admin> queryAdminList(int pageNo, int pageSize);

	Admin selectById(Long id);

	void deleteAdmin(Long id);

	void addAdmin(Admin admin);

	void deleteAdminRole(Long adminId);

	void addAdminRole(Long adminId, String roleId);

	void updatePwd(Long id, String pwd);
	
}
