package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.UserInfo;

public interface IssuerMapper extends Serializable {
	
	int selectByName(@Param(value="nickName")String nickName);
	
	int getStopTotalCount(@Param(value="nickName")String nickName);
	
	List<UserInfo> queryStopUserInfoList(@Param(value="nickName")String nickName,
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);
	
	int getTotalCount(@Param(value="nickName")String nickName);
	
	List<UserInfo> queryUserInfoList(@Param(value="nickName")String nickName,
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);
	
	void deleteUserInfo(UserInfo userInfo);
	
	void updateUserInfo(UserInfo userInfo);
	
	UserInfo selectById(String id);

	void addUserInfo(UserInfo userInfo);

}
