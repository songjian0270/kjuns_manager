package com.kjuns.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.UserAccountMapper;
import com.kjuns.mapper.UserInfoMapper;
import com.kjuns.model.UserAccount;
import com.kjuns.model.UserInfo;
import com.kjuns.service.UserInfoService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.pager.Page;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserAccountMapper userAccountMapper;

	@Override
	public Page queryUserInfoList(UserInfo userInfo, Page page) {
		int total = userInfoMapper.getTotalCount(userInfo.getNickName(), userInfo.getMobilePhone());
		List<UserInfo> userInfos = userInfoMapper.queryUserInfoList(userInfo.getNickName(), userInfo.getMobilePhone()
				, page.getStart(), page.getPageSize());
		for(UserInfo info:userInfos){
			try {
				Date d = CommonConstants.DATETIME_SEC.parse(info.getCreateDate());
				info.setCreateDate(CommonConstants.DATETIME_SEC.format(d));
			} catch (ParseException e) {
			}
		}
		page.setTotalCount(total);
		page.setList(userInfos);
		return page;
	}


	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(userInfo.getAccountId());
		userAccount.setUpdateBy(userInfo.getUpdateBy());
		userAccount.setUpdateDate(userInfo.getUpdateDate());
		userAccount.setDataFlag(userInfo.getDataFlag());
		userAccountMapper.deleteUserAccount(userAccount);
		userInfoMapper.deleteUserInfo(userInfo);
	}
	
}
