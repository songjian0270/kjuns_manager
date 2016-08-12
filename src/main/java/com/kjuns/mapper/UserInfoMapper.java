package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.UserInfo;

public interface UserInfoMapper extends Serializable {
	
	int getTotalCount(@Param(value="nickName")String nickName,
			@Param(value="mobilePhone")String mobilePhone);
	
	List<UserInfo> queryUserInfoList(@Param(value="nickName")String nickName,
			@Param(value="mobilePhone")String mobilePhone,
			@Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);
	
	void deleteUserInfo(UserInfo userInfo);
	
	UserInfo selectById(@Param(value="id")String id);

}
