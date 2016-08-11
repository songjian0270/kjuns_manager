package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.UserInfo;
import com.kjuns.util.pager.Page;

public interface UserInfoService extends Serializable {
	
	Page queryUserInfoList(UserInfo userInfo, Page page);
	
	void deleteUserInfo(UserInfo userInfo);

}
