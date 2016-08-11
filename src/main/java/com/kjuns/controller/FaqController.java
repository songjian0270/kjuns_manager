package com.kjuns.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Faq;
import com.kjuns.service.FaqService;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  反馈
 * @author James
 * @date 2016-07-22
 * @file FaqController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/faq")
public class FaqController extends BaseController {
	
	@Resource
	private FaqService faqService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(Faq faq, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = faqService.queryFaqList(faq, page);
		map.addAttribute("page", page);
		map.addAttribute("faq", faq);
		return "faq/faqList";
	}
	
	/**
	 * faq修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap map) {
		Faq faq = new Faq();
		if (CommonUtils.notEmpty(id)) {
			faq = faqService.selectById(id);
		} 
		map.addAttribute("faq", faq);
		return "faq/faqEdit";
	}
	
}
