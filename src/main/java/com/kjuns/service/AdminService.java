package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Admin;
import com.kjuns.util.pager.Page;

public interface AdminService extends Serializable {

	Admin queryByUsername(String username);

	Page queryAdminList(Page page);
	
	Admin selectById(Long id);

	void deleteAdmin(Long id);

	int addAdmin(Admin admin);

	boolean updatePwd(Long id, String pwd, String oldPwd);

}
