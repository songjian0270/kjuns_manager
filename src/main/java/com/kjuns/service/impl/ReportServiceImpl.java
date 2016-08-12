package com.kjuns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.ReportMapper;
import com.kjuns.mapper.UserInfoMapper;
import com.kjuns.model.Report;
import com.kjuns.model.UserInfo;
import com.kjuns.service.ReportService;
import com.kjuns.util.pager.Page;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public Page queryReportList(Report report, Page page) {
		int total = reportMapper.getTotalCount(report.getContentType(), report.getDataFlag());
		List<Report> reports = reportMapper.queryReportList(report.getContentType(), report.getDataFlag(), page.getStart(), page.getPageSize());
		for(Report rpt: reports){
			UserInfo ui = userInfoMapper.selectById(rpt.getUserId());
			rpt.setCreateName(ui.getNickName());
		}
		page.setTotalCount(total);
		page.setList(reports);
		return page;
	}

	@Override
	public void deleteReport(Report report) {
		reportMapper.deleteReport(report);
	}
	
}
