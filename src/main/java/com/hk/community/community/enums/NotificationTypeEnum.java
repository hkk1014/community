package com.hk.community.community.enums;

/**
 * 作者: hekang
 * 时间: 2020-04-11 15:17
 * 描述:
 **/
public enum NotificationTypeEnum {
UNREAD(0),READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationTypeEnum(int status) {
        this.status = status;
    }
}
