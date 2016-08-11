package com.kjuns.mapper;

import java.io.Serializable;

import com.kjuns.model.UserAccount;

public interface UserAccountMapper extends Serializable {

	void deleteUserAccount(UserAccount userAccount);
	
}
