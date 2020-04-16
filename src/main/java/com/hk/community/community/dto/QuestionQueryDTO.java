package com.hk.community.community.dto;

import lombok.Data;

/**
 * 作者: hekang
 * 时间: 2020-04-14 14:53
 * 描述:
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private String tag;
    private Integer page;
    private Integer size;
}
