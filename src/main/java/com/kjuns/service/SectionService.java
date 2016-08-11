package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Section;
import com.kjuns.util.pager.Page;

public interface SectionService extends Serializable {

	Page querySectionList(String title, Page page);
	
	Section selectById(String id);

	void deleteSection(Section section);

	int addSection(Section section);

}
