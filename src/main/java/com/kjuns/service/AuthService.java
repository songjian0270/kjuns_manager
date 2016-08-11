package com.kjuns.service;

import java.util.List;

import com.kjuns.model.Authority;
import com.kjuns.util.pager.Page;

public interface AuthService {
	
	Authority selectById(Long id);

	void deleteAuth(Long id);

	Page queryAuthList(Page page);

	int addAuth(Authority authority);
	
	List<Authority> queryAllAuth();
	
}
