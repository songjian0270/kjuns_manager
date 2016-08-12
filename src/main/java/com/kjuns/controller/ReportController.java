package com.kjuns.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Report;
import com.kjuns.service.ReportService;
import com.kjuns.util.pager.Page;

/**
 * <b>Function: </b>  举报
 * @author James
 * @date 2016-07-22
 * @file FaqController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	
	@Resource
	private ReportService reportService;
	
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(Report report, int pageNumber, ModelMap map) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = reportService.queryReportList(report, page);
		map.addAttribute("page", page);
		map.addAttribute("report", report);
		return "report/reportList";
	}
	
	/**
	 * faq修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Report report, String id, ModelMap map) {
		reportService.deleteReport(report);
		map.addAttribute("report", report);
		return list(report, 1, map);
	}
	
}
