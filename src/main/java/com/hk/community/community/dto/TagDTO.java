package com.hk.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * 作者: hekang
 * 时间: 2020-04-09 21:59
 * 描述:
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
