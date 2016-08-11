package com.kjuns.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.ContentTagMapper;
import com.kjuns.mapper.TagMapper;
import com.kjuns.model.ContentTag;
import com.kjuns.model.Tag;
import com.kjuns.service.TagService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;

@Service("tagService")
public class TagServiceImpl implements TagService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private TagMapper tagMapper;
	
	@Autowired
	private ContentTagMapper contentTagMapper;

	@Override
	public Page queryTagList(String name, Page page) {
		int total = tagMapper.getTotalCount(name);
		List<Tag> tags = tagMapper.queryTagList(name, page.getStart(), page.getPageSize());
		for(Tag tag:tags){
			try {
				Date d = CommonConstants.DATETIME_SEC.parse(tag.getCreateDate());
				tag.setCreateDate(CommonConstants.DATETIME_SEC.format(d));
			} catch (ParseException e) {
			}
		}
		page.setTotalCount(total);
		page.setList(tags);
		return page;
	}


	@Override
	public void deleteTag(Tag tag) {
		tagMapper.deleteTag(tag);
	}

	@Override
	public int addTag(Tag tag) {
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = tagMapper.selectByName(tag.getName());
		if (count > 1) {// 标签已经存在
			return -1;
		}
		if (CommonUtils.notEmpty(tag.getId())) {// 修改流程
			tagMapper.updateTag(tag);
			ContentTag contentTag = new ContentTag();
			
			contentTag.setTagId(tag.getId());
			contentTag.setTagName(tag.getName());
			contentTagMapper.updateContentTag(contentTag);
			
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			tag.setId(id);
			tag.setCreateDate(datetime);
			tag.setUpdateDate(datetime);
			tagMapper.addTag(tag);
		}
		return 1;
	}


	public Tag selectById(String id) {
		return tagMapper.selectById(id);
	}
	
}
