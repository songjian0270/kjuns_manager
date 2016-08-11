package com.kjuns.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.ContentTypeMapper;
import com.kjuns.model.ContentType;
import com.kjuns.service.TypeService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private ContentTypeMapper typeMapper;

	@Override
	public Page queryTypeList(String name, Page page) {
		int total = typeMapper.getTotalCount(name);
		List<ContentType> types = typeMapper.queryTypeList(name, page.getStart(), page.getPageSize());
		for(ContentType type:types){
			try {
				Date d = CommonConstants.DATETIME_SEC.parse(type.getCreateDate());
				type.setCreateDate(CommonConstants.DATETIME_SEC.format(d));
			} catch (ParseException e) {
			}
		}
		page.setTotalCount(total);
		page.setList(types);
		return page;
	}


	@Override
	public void deleteType(ContentType type) {
		typeMapper.deleteType(type);
	}

	@Override
	public int addType(ContentType type) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = typeMapper.selectByName(type.getName());
		if (count > 1) {// 标签已经存在
			return -1;
		}
		if (CommonUtils.notEmpty(type.getId())) {// 修改流程
			typeMapper.updateType(type);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			type.setId(id);
			type.setCreateDate(datetime);
			type.setUpdateDate(datetime);
			typeMapper.addType(type);
		}
		return 1;
	}


	public ContentType selectById(String id) {
		return typeMapper.selectById(id);
	}


	@Override
	public List<Map<String, Object>> queryTypeList() {
		List<Map<String, Object>> types = typeMapper.querySelectList();
		return types;
	}
	
	
}
