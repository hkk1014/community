package com.hk.community.community.dto;

import com.hk.community.community.model.User;
import lombok.Data;

/**
 * 作者: hekang
 * 时间: 2020-04-03 22:44
 * 描述:
 **/
@Data
public class CommentDTO {

    private Long id;


    private Long parentId;


    private Integer type;


    private Long commentator;


    private Long gmtCreate;


    private Long gmtModified;


    private Long likeCount;


    private String content;
    private User user;
    private Long commentCount;

}
