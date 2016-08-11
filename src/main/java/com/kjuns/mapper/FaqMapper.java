package com.kjuns.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.Faq;

public interface FaqMapper extends Serializable {

	int getTotalCount(@Param(value="title")String title, @Param(value="content")String content);

	List<Faq> queryFaqList(@Param(value="title")String title, @Param(value="content")String content
			, @Param(value="pageNo")int pageNo, @Param(value="pageSize")int pageSize);

	Faq selectById(String id);

	void deleteFaq(Faq faq);
	
	void updateFaq(Faq faq);
	
}
