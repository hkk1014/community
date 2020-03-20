package com.hk.community.community.model;

import lombok.Data;

/**
 * 作者: 何康先生
 * 时间: 2020-03-20 10:13
 * 描述:
 **/
@Data
public class Question {
    private Integer id;
    private  String title;
    private String description;
    private Long  gmtCreate;
    private Long  gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer  likeCount;
    private String tag;
}
