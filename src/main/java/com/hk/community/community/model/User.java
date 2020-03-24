package com.hk.community.community.model;

import lombok.Data;

/**
 * 作者: 何康先生
 * 时间: 2020-03-17 20:14
 * 描述:
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long  gmtCreate;
    private long gmtModified;
    private String avatarUrl;
}
