package com.hk.community.community.enums;

/**
 * 作者: hekang
 * 时间: 2020-04-11 15:17
 * 描述:
 **/
public enum NotificationStatusEnum {
UNREAD(0),READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
