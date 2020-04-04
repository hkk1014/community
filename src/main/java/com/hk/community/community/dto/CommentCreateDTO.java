package com.hk.community.community.dto;

import lombok.Data;

/**
 * 作者: 何康先生
 * 时间: 2020-03-31 23:47
 * 描述:
 **/
@Data
public class CommentCreateDTO {

    private  Long parentId;
    private String content;
    private Integer type;
}
