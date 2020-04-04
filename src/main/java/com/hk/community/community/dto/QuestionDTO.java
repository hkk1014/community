package com.hk.community.community.dto;

import com.hk.community.community.model.User;
import lombok.Data;

/**
 * 作者: 何康先生
 * 时间: 2020-03-21 21:03
 * 描述:
 **/
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
