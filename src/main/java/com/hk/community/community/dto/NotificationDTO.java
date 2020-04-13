package com.hk.community.community.dto;

import com.hk.community.community.model.User;
import lombok.Builder;
import lombok.Data;

/**
 * 作者: hekang
 * 时间: 2020-04-11 22:38
 * 描述:
 **/
@Builder
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private Long outerid;
    private String outerTitle;
    private String typeName;
    private Integer type;

    /*public NotificationDTO() {

    }*/
}
