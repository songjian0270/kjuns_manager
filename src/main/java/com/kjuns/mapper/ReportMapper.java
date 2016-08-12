package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Report;

public interface ReportMapper extends Serializable {

	int getTotalCount(@Param(value="contentType")String contentType, @Param(value="dataFlag")String dataFlag);

	List<Report> queryReportList(@Param(value="contentType")String contentType, @Param(value="dataFlag")String dataFlag
			, @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);
	
	void deleteReport(Report report);

	
}
