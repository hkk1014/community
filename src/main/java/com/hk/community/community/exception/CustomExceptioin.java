package com.hk.community.community.exception;

/**
 * 作者: 何康先生
 * 时间: 2020-03-30 23:58
 * 描述:
 **/
public class CustomExceptioin extends RuntimeException {
    private String message;

    private Integer code;



    public CustomExceptioin(ICustoizeErrorCode code) {
        this.code=code.getCode();
        this.message=code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
