package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.IssuerMapper;
import com.kjuns.model.UserInfo;
import com.kjuns.service.IssuerService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("issuerService")
public class IssuerServiceImpl implements IssuerService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private IssuerMapper issuerMapper;

	@Override
	public Page queryIssuerList(String nickName, Page page) {
		int total = issuerMapper.getTotalCount(nickName);
		List<UserInfo> userInfos = issuerMapper.queryUserInfoList(nickName, page.getStart(), page.getPageSize());
		for(UserInfo userInfo:userInfos){
			if(CommonUtils.notEmpty(userInfo.getFaceSrc())){
				userInfo.setFaceSrc(CommonUtils.getImage(userInfo.getFaceSrc()));
			}
		}
		page.setTotalCount(total);
		page.setList(userInfos);
		return page;
	}


	@Override
	public void deleteIssuer(UserInfo userInfo) {
		issuerMapper.deleteUserInfo(userInfo);
	}

	@Override
	public int addIssuer(UserInfo userInfo) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = issuerMapper.selectByName(userInfo.getNickName());
		if (count > 1) {// 标签已经存在
			return -1;
		}
		if (CommonUtils.notEmpty(userInfo.getId())) {// 修改流程
			issuerMapper.updateUserInfo(userInfo);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			userInfo.setId(id);
			userInfo.setCreateDate(datetime);
			userInfo.setUpdateDate(datetime);
			issuerMapper.addUserInfo(userInfo);
		}
		return 1;
	}


	public UserInfo selectById(String id) {
		UserInfo userInfo = issuerMapper.selectById(id);
		if(CommonUtils.notEmpty(userInfo.getFaceSrc())){
			userInfo.setFaceSrc(CommonUtils.getImage(userInfo.getFaceSrc()));
		}
		return userInfo;
	}
	
}
