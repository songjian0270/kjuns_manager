package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.IssuerMapper;
import com.kjuns.mapper.CampMapper;
import com.kjuns.model.Camp;
import com.kjuns.model.UserInfo;
import com.kjuns.service.CampService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("campService")
public class CampServiceImpl implements CampService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private CampMapper campMapper;
	
	@Autowired
	private IssuerMapper issuerMapper;

	@Override
	public Page queryCampList(String id, String title, Page page) {
		int total = campMapper.getTotalCount(id, title);
		List<Camp> Camps = campMapper.queryCampList(id, title, page.getStart(), page.getPageSize());
		for(Camp Camp:Camps){
			if(CommonUtils.notEmpty(Camp.getThumbnail())){
				Camp.setThumbnail(CommonUtils.getImage(Camp.getThumbnail()));
			}
		}
		page.setTotalCount(total);
		page.setList(Camps);
		return page;
	}


	@Override
	public void deleteCamp(Camp camp) {
		campMapper.deleteCamp(camp);
	}

	@Override
	public int addCamp(Camp camp) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = campMapper.selectByTitle(camp.getTitle());
		if (count > 1) {// 专栏名称已经存在
			return -1;
		}
		if(CommonUtils.notEmpty(camp.getIssuers())){
			String [] issuersArr = camp.getIssuers().split("&#%&");
			camp.setIssuers(issuersArr[0]);
		}
		if (CommonUtils.notEmpty(camp.getId())) {// 修改流程
			campMapper.updateCamp(camp);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			camp.setId(id);
			if(CommonUtils.isEmpty(camp.getCreateDate())){
				camp.setCreateDate(datetime);	
			}
			camp.setUpdateDate(datetime);
			campMapper.addCamp(camp);
		}
		return 1;
	}

	public Camp selectById(String id) {
		Camp camp = campMapper.selectById(id);
		if(CommonUtils.notEmpty(camp.getIssuers())){
			UserInfo userInfo = issuerMapper.selectById(camp.getIssuers());
			camp.setIssuersName(userInfo.getNickName());
		}
		if(CommonUtils.notEmpty(camp.getThumbnail())){
			camp.setThumbnail(CommonUtils.getImage(camp.getThumbnail()));
		}
		return camp;
	}
	

}
