package com.kjuns.mapper;

import org.apache.ibatis.annotations.Param;

import com.kjuns.model.UserComment;

public interface CommentMapper {
	
	/**
	 * 删除一条动态评论
	 * @param id
	 * @return
	 */
	int delContentCommentsById(UserComment userComment);
	
	UserComment get(@Param("table")String table, @Param(value="id")String id);
	
}
