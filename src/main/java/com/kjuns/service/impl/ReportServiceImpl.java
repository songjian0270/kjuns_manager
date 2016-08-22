package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.CampMapper;
import com.kjuns.mapper.CommentMapper;
import com.kjuns.mapper.ContentMapper;
import com.kjuns.mapper.ReportMapper;
import com.kjuns.mapper.UserInfoMapper;
import com.kjuns.model.Camp;
import com.kjuns.model.Content;
import com.kjuns.model.Report;
import com.kjuns.model.UserComment;
import com.kjuns.model.UserInfo;
import com.kjuns.service.ReportService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.pager.Page;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private CampMapper campMapper;

	@Autowired
	private ContentMapper contentMapper;
	 
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public Page queryReportList(Report report, Page page) {
		int total = reportMapper.getTotalCount(report.getContentType(), report.getDataFlag());
		List<Report> reports = reportMapper.queryReportList(report.getContentType(), report.getDataFlag(), page.getStart(), page.getPageSize());
		for(Report rpt: reports){
			UserInfo ui = userInfoMapper.selectById(rpt.getUserId());
			rpt.setCreateName(ui.getNickName());
			
//			if(rpt.getContentType() == 1 || rpt.getContentType() == 3){
//				String table = "";
//				if(rpt.getContentType() == 1){
//					table = CommonConstants.KJUNS_CONTENT_COMMENTS;
//				}else if(rpt.getContentType() == 3){
//					table = CommonConstants.KJUNS_CAMP_COMMENTS;
//				}
//				UserComment userComment = commentMapper.get(table, rpt.getContentId());
//				rpt.setContent(userComment.getContent());
//			}else if(rpt.getContentType() == 0){
//				Content content = contentMapper.selectById(rpt.getId());
//				rpt.setContent(content.getContent());
//			}else if(rpt.getContentType() == 2){
//				Camp camp = campMapper.selectById(rpt.getId());
//				rpt.setContent(camp.getContent());
//			}
		}
		page.setTotalCount(total);
		page.setList(reports);
		return page;
	}

	@Override
	public void deleteReport(Report report) {
		report.setDataFlag("0");
		String dateTime = CommonConstants.DATETIME_SEC.format(new Date());
		if(report.getContentType() == 1 || report.getContentType() == 3){
			UserComment userComment = new UserComment();
			userComment.setId(report.getContentId());
			userComment.setUpdateBy(report.getUpdateBy());
			userComment.setUpdateDate(dateTime);
			if(report.getContentType() == 1){
				userComment.setTable(CommonConstants.KJUNS_CONTENT_COMMENTS);
			}else if(report.getContentType() == 3){
				userComment.setTable(CommonConstants.KJUNS_CAMP_COMMENTS);
			}
			commentMapper.delContentCommentsById(userComment);	
		}else if(report.getContentType() == 0){
			Content content = new Content();
			content.setId(report.getContentId());
			content.setUpdateBy(report.getUpdateBy());
			content.setUpdateDate(dateTime);
			contentMapper.deleteContent(content);
		}else if(report.getContentType() == 2){
			Camp camp = new Camp();
			camp.setId(report.getContentId());
			camp.setUpdateBy(report.getUpdateBy());
			camp.setUpdateDate(dateTime);
			campMapper.deleteCamp(camp);
		}else if(report.getContentType() == 9999){
			report.setDataFlag("9999");
		}
		reportMapper.deleteReport(report);
		
	}

	@Override
	public String getDetail(String contentId, int contentType) {
		if(contentType == 1 || contentType== 3){
			String table = "";
			if(contentType == 1){
				table = CommonConstants.KJUNS_CONTENT_COMMENTS;
			}else if(contentType == 3){
				table = CommonConstants.KJUNS_CAMP_COMMENTS;
			}
			UserComment userComment = commentMapper.get(table, contentId);
			if(null != userComment){
				return userComment.getContent();	
			}else{
				return "该记录不存在";
			}
		}else if(contentType == 0){
			Content content = contentMapper.selectById(contentId);
			return content.getContent();
		}else if(contentType== 2){
			Camp camp = campMapper.selectById(contentId);
			return camp.getContent();
		}
		return null;
	}
	
}
