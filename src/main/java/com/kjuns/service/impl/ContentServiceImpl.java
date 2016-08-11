package com.kjuns.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjuns.mapper.ContentMapper;
import com.kjuns.mapper.ContentRelatedArticlesMapper;
import com.kjuns.mapper.ContentTagMapper;
import com.kjuns.mapper.IssuerMapper;
import com.kjuns.model.Content;
import com.kjuns.model.ContentRelatedArticles;
import com.kjuns.model.ContentTag;
import com.kjuns.model.UserInfo;
import com.kjuns.service.ContentService;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.UUIDUtils;
import com.kjuns.util.pager.Page;


@Service("contentService")
public class ContentServiceImpl implements ContentService {

	private static final long serialVersionUID = 1348182655382431034L;

	@Autowired
	private ContentMapper contentMapper;
	
	@Autowired
	private ContentTagMapper contentTagMapper;
	
	@Autowired
	private IssuerMapper issuerMapper;
	
	@Autowired
	private ContentRelatedArticlesMapper contentRelatedArticlesMapper;

	@Override
	public Page queryContentList(String sectionId, String title, Page page) {
		int total = contentMapper.getTotalCount(sectionId, title);
		List<Content> contents = contentMapper.queryContentList(sectionId, title, page.getStart(), page.getPageSize());
		for(Content content:contents){
			if(CommonUtils.notEmpty(content.getMindMap())){
				content.setMindMap(CommonUtils.getImage(content.getMindMap()));
			}
			if(CommonUtils.notEmpty(content.getThumbnail())){
				content.setThumbnail(CommonUtils.getImage(content.getThumbnail()));
			}
		}
		page.setTotalCount(total);
		page.setList(contents);
		return page;
	}


	@Override
	public void deleteContent(Content content) {
		contentMapper.deleteContent(content);
	}

	@Override
	public int addContent(Content content) {
		
		String datetime = CommonConstants.DATETIME_SEC.format(new Date());
		int count = contentMapper.selectByTitle(content.getTitle());
		if (count > 1) {// 专栏名称已经存在
			return -1;
		}
		if(CommonUtils.notEmpty(content.getIssuers())){
			String [] issuersArr = content.getIssuers().split("&#%&");
			content.setIssuers(issuersArr[0]);
		}
		if (CommonUtils.notEmpty(content.getId())) {// 修改流程
			contentMapper.updateContent(content);
			ContentTag ct = new ContentTag();
			ct.setContentId(content.getId());
			ct.setUpdateBy(content.getUpdateBy());
			ct.setUpdateDate(datetime);
			contentTagMapper.deleteContentTag(ct);
			
			ContentRelatedArticles cr = new ContentRelatedArticles();
			cr.setContentId(content.getId());
			cr.setUpdateBy(content.getUpdateBy());
			cr.setUpdateDate(datetime);
			contentRelatedArticlesMapper.deleteContentRelatedArticles(cr);
			
			ContentRelatedArticles cra = new ContentRelatedArticles();
			cra.setRelatedArticlesId(content.getId());
			cra.setRelatedArticlesName(content.getTitle());
			contentRelatedArticlesMapper.updateContentRelatedArticles(cra);
		} else {
			String id = UUIDUtils.getUUID().toString().replace("-", "");
			content.setId(id);
			if(CommonUtils.isEmpty(content.getCreateDate())){
				content.setCreateDate(datetime);	
			}
			content.setUpdateDate(datetime);
			contentMapper.addContent(content);

		}
		
		if(CommonUtils.notEmpty(content.getTags())){
			String [] tagsArr = content.getTags().split(",");
			for(String tag: tagsArr){
				String tagKey = UUIDUtils.getUUID().toString().replace("-", "");
				String [] idName = tag.split("&#%&");
				ContentTag ct = new ContentTag();
				ct.setContentId(content.getId());
				ct.setTagId(idName[0]);
				ct.setTagName(idName[1]);
				ct.setId(tagKey);
				ct.setCreateBy(content.getUpdateBy());
				ct.setCreateDate(datetime);
				contentTagMapper.addContentTag(ct);
			}
		}
		
		if(CommonUtils.notEmpty(content.getRelatedArticless())){
			String [] relatedArticlessArr = content.getRelatedArticless().split(",");
			for(String relatedArticles: relatedArticlessArr){
				String relatedArticlesKey = UUIDUtils.getUUID().toString().replace("-", "");
				String [] idName = relatedArticles.split("&#%&");
				ContentRelatedArticles cr = new ContentRelatedArticles();
				cr.setContentId(content.getId());
				cr.setRelatedArticlesId(idName[0]);
				cr.setRelatedArticlesName(idName[1]);
				cr.setId(relatedArticlesKey);
				cr.setCreateBy(content.getUpdateBy());
				cr.setCreateDate(datetime);
				contentRelatedArticlesMapper.addContentRelatedArticles(cr);
			}
		}
		return 1;
	}

	public Content selectById(String id) {
		Content content = contentMapper.selectById(id);
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfName = new StringBuffer();
		List<ContentTag> tagList = contentTagMapper.queryContentTagForContentId(content.getId());
		if(null != tagList && tagList.size() > 0){
			for(int i=0; i< tagList.size(); i++){
		        if(tagList.size() == 1 || i== tagList.size() - 1){
		        	sbf.append(tagList.get(i).getTagId()).append("&#%&")
			        .append(tagList.get(i).getTagName());
			        sbfName.append(tagList.get(i).getTagName());
				}else if(i < tagList.size() && tagList.size() > 1){
					sbf.append(tagList.get(i).getTagId()).append("&#%&")
			        .append(tagList.get(i).getTagName()).append(",");
			        sbfName.append(tagList.get(i).getTagName()).append(",");
				}	
		    }
			content.setTags(sbf.toString());
			content.setTagsName(sbfName.toString());
		}
		sbf = new StringBuffer();
		sbfName = new StringBuffer();
		List<ContentRelatedArticles> relatedArticlesList = 
				contentRelatedArticlesMapper.queryContentRelatedArticlesForContentId(content.getId());
		if(null != relatedArticlesList && relatedArticlesList.size() > 0){
			for(int i=0; i< relatedArticlesList.size(); i++){
		        if(relatedArticlesList.size() == 1 || i== relatedArticlesList.size() - 1){
		        	sbf.append(relatedArticlesList.get(i).getRelatedArticlesId()).append("&#%&")
				        .append(relatedArticlesList.get(i).getRelatedArticlesName());
		        	sbfName.append(relatedArticlesList.get(i).getRelatedArticlesName());
				}else if(i < relatedArticlesList.size() && relatedArticlesList.size() > 1){
					sbf.append(relatedArticlesList.get(i).getRelatedArticlesId()).append("&#%&")
			        .append(relatedArticlesList.get(i).getRelatedArticlesName()).append(",");
					sbfName.append(relatedArticlesList.get(i).getRelatedArticlesName()).append(",");
				}	
		    }
			content.setRelatedArticless(sbf.toString());
			content.setRelatedArticlessName(sbfName.toString());
		}
		if(CommonUtils.notEmpty(content.getIssuers())){
			UserInfo userInfo = issuerMapper.selectById(content.getIssuers());
			content.setIssuersName(userInfo.getNickName());
		}
		if(CommonUtils.notEmpty(content.getMindMap())){
			content.setMindMap(CommonUtils.getImage(content.getMindMap()));
		}
		if(CommonUtils.notEmpty(content.getThumbnail())){
			content.setThumbnail(CommonUtils.getImage(content.getThumbnail()));
		}
		return content;
	}

}
