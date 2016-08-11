package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Faq;
import com.kjuns.util.pager.Page;

public interface FaqService extends Serializable {

	Page queryFaqList(Faq faq, Page page);
	
	Faq selectById(String id);

	void deleteFaq(Faq faq);

}
