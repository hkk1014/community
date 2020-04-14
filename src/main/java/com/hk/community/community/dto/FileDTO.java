package com.hk.community.community.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 作者: hekang
 * 时间: 2020-04-13 11:01
 * 描述:
 **/
@Builder
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
