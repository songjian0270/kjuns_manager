package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import com.kjuns.model.Authority;

public interface AuthMapper extends Serializable {

	Authority selectByName(String authName);
	
	List<String> queryAdminAuth(Long adminId);

	int getTotalCount();

	List<Authority> queryAuthList(int pageNo, int pageSize);

	Authority selectById(Long id);

	void deleteAuth(Long id);

	void deleteRoleAuth(Long authId);

	void addAuth(Authority auth);
	
	List<Authority> queryAllAuth();
	
	List<Authority> queryRoleAuth(Long roleId);
	
	void updateAuth(Authority auth);
	
}
