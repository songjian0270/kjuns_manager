package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.AuthMapper;
import com.kjuns.model.Authority;
import com.kjuns.service.AuthService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Override
	public Authority selectById(Long id) {
		return authMapper.selectById(id);
	}

	@Override
	public void deleteAuth(Long id) {
		authMapper.deleteAuth(id);
		authMapper.deleteRoleAuth(id);
	}

	@Override
	public Page queryAuthList(Page page) {
		int total = authMapper.getTotalCount();
		List<Authority> auths = authMapper.queryAuthList(page.getStart(), page.getPageSize());
		page.setTotalCount(total);
		page.setList(auths);
		return page;
	}

	@Override
	public int addAuth(Authority authority) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		if (CommonUtils.notEmpty(authority.getId())) {
			authMapper.updateAuth(authority);
		} else {
			Authority auth = authMapper.selectByName(authority.getName());
			if (auth != null) {
				return -1;
			}
			authority.setCreateDate(datetime);
			authority.setUpdateDate(datetime);
			authMapper.addAuth(authority);
		}
		return 1;
	}

	@Override
	public List<Authority> queryAllAuth() {
		return authMapper.queryAllAuth();
	}

}
