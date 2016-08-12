package com.kjuns.service;

import java.io.Serializable;

import com.kjuns.model.Report;
import com.kjuns.util.pager.Page;

public interface ReportService extends Serializable {

	Page queryReportList(Report report, Page page);

	void deleteReport(Report report);

}
