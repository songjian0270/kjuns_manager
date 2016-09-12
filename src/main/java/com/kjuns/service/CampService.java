package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Camp;
import com.kjuns.util.pager.Page;

public interface CampService extends Serializable {

	Page queryCampList(String id, String title, Page page);
	
	Camp selectById(String id);

	void deleteCamp(Camp camp);

	int addCamp(Camp camp);

}
