package com.kjuns.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kjuns.model.Admin;
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
	public String list(Integer contentTypet, String dataFlagt, int pageNumber, ModelMap map) {
		Report report = new Report();
		report.setContentType(contentTypet == null ? 99 :contentTypet);
		report.setDataFlag(dataFlagt);
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page = reportService.queryReportList(report, page);
		map.addAttribute("page", page);
		map.addAttribute("report", report);
		map.addAttribute("contentTypet", contentTypet);
		System.out.println("dataFlagt:"+dataFlagt);
		map.addAttribute("dataFlagt", dataFlagt);
		return "report/reportList";
	}
	
	/**
	 * faq修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Report report,Integer contentTypet, String dataFlagt,
			String id, ModelMap map, HttpSession session) {
		if(contentTypet == null ){
			contentTypet =99;
		}
		Admin my = this.getContent(session);
		report.setCreateBy(my.getId());
		report.setUpdateBy(my.getId());
		reportService.deleteReport(report);
		map.addAttribute("contentTypet", contentTypet);
		map.addAttribute("dataFlagt", dataFlagt);
		report = new Report();
		report.setContentType(contentTypet);
		report.setDataFlag(dataFlagt);
		return list( contentTypet,  dataFlagt, 1, map);
	}
	
	@RequestMapping("detail")
	public void detail(String contentId, int contentType,ModelMap map){
		String content = reportService.getDetail(contentId, contentType);
		map.addAttribute("content", content);
	}
	
}
