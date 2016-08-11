package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.UserInfo;
import com.kjuns.util.pager.Page;

public interface IssuerService extends Serializable {

	Page queryIssuerList(String name, Page page);
	
	UserInfo selectById(String id);

	void deleteIssuer(UserInfo userInfo);

	int addIssuer(UserInfo userInfo);

}
