package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.IssuerMapper;
import com.kjuns.mapper.SectionMapper;
import com.kjuns.model.Section;
import com.kjuns.model.UserInfo;
import com.kjuns.service.SectionService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private SectionMapper sectionMapper;
	
	@Autowired
	private IssuerMapper issuerMapper;

	@Override
	public Page querySectionList(String title, Page page) {
		int total = sectionMapper.getTotalCount(title);
		List<Section> sections = sectionMapper.querySectionList(title, page.getStart(), page.getPageSize());
		for(Section section:sections){
			if(CommonUtils.notEmpty(section.getSummary())){
				if(section.getSummary().length() > 50){
					section.setSummary(section.getSummary().substring(0, 50)+".....");
				}
			}
			if(CommonUtils.notEmpty(section.getBackground())){
				section.setBackground(CommonUtils.getImage(section.getBackground()));
			}
			if(CommonUtils.notEmpty(section.getThumbnail())){
				section.setThumbnail(CommonUtils.getImage(section.getThumbnail()));
			}
		}
		page.setTotalCount(total);
		page.setList(sections);
		return page;
	}


	@Override
	public void deleteSection(Section section) {
		sectionMapper.deleteSection(section);
	}

	@Override
	public int addSection(Section section) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = sectionMapper.selectByTitle(section.getTitle());
		if (count > 1) {// 专栏名称已经存在
			return -1;
		}
		if(CommonUtils.notEmpty(section.getIssuers())){
			String [] issuersArr = section.getIssuers().split("&#%&");
			section.setIssuers(issuersArr[0]);
		}
		if (CommonUtils.notEmpty(section.getId())) {// 修改流程
			sectionMapper.updateSection(section);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			section.setId(id);
			if(CommonUtils.isEmpty(section.getCreateDate())){
				section.setCreateDate(datetime);	
			}
			section.setUpdateDate(datetime);
			sectionMapper.addSection(section);
		}
		return 1;
	}

	public Section selectById(String id) {
		Section section = sectionMapper.selectById(id);
		if(CommonUtils.notEmpty(section.getIssuers())){
			UserInfo userInfo = issuerMapper.selectById(section.getIssuers());
			section.setIssuersName(userInfo.getNickName());
		}
		if(CommonUtils.notEmpty(section.getBackground())){
			section.setBackground(CommonUtils.getImage(section.getBackground()));
		}
		if(CommonUtils.notEmpty(section.getThumbnail())){
			section.setThumbnail(CommonUtils.getImage(section.getThumbnail()));
		}
		return section;
	}
	

}
