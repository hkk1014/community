package com.hk.community.community.mapper;

import com.hk.community.community.model.Comment;

/**
 * 作者: hekang
 * 时间: 2020-04-04 22:57
 * 描述:
 **/
public interface CommentExtMapper {
    int  incCommentCount(Comment comment);
}
