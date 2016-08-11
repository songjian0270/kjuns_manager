package com.kjuns.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.FaqMapper;
import com.kjuns.model.Faq;
import com.kjuns.service.FaqService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.pager.Page;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private FaqMapper faqMapper;

	@Override
	public Page queryFaqList(Faq faq, Page page) {
		int total = faqMapper.getTotalCount(faq.getTitle(), faq.getContent());
		List<Faq> faqs = faqMapper.queryFaqList(faq.getTitle(), faq.getContent(), page.getStart(), page.getPageSize());
		for(Faq fq:faqs){
			try {
				Date d = CommonConstants.DATETIME_SEC.parse(faq.getCreateDate());
				fq.setCreateDate(CommonConstants.DATETIME_SEC.format(d));
			} catch (ParseException e) {
			}
		}
		page.setTotalCount(total);
		page.setList(faqs);
		return page;
	}


	@Override
	public void deleteFaq(Faq faq) {
		faqMapper.deleteFaq(faq);
	}

	public Faq selectById(String id) {
		return faqMapper.selectById(id);
	}
	
}
