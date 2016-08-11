package com.kjuns.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.AdminMapper;
import com.kjuns.mapper.AuthMapper;
import com.kjuns.model.Admin;

@Service("authRealm")
public class AuthRealmImpl extends AuthorizingRealm {
	
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AuthMapper authMapper;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Admin admin = (Admin) SecurityUtils.getSubject().getSession().getAttribute("loginAdmin");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (admin == null) {
			return info;
		}
		// 添加权限
		List<String> pers = authMapper.queryAdminAuth(admin.getId());
		info.addStringPermissions(pers);
		return info;
	}

	// 验证登录
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String password = String.valueOf(token.getPassword());
		String username = token.getUsername();
		Admin admin = adminMapper.selectByUsername(username);
		if (admin != null && admin.getPassword().equals(password)) {
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("loginAdmin", admin);
			return new SimpleAuthenticationInfo(username, password, getName());
		} else {
			return null;
		}
	}

}
