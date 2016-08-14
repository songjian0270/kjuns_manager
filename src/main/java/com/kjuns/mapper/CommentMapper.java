package com.kjuns.mapper;

import com.kjuns.model.UserComment;

public interface CommentMapper {
	
	/**
	 * 删除一条动态评论
	 * @param id
	 * @return
	 */
	int delContentCommentsById(UserComment userComment);
	
}
