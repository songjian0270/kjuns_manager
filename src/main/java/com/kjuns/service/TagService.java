package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Tag;
import com.kjuns.util.pager.Page;

public interface TagService extends Serializable {

	Page queryTagList(String name, Page page);
	
	Tag selectById(String id);

	void deleteTag(Tag tag);

	int addTag(Tag tag);

}
