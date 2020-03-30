package com.hk.community.community.exception;

/**
 * 作者: 何康先生
 * 时间: 2020-03-30 23:58
 * 描述:
 **/
public class CustomExceptioin extends RuntimeException {
    private String message;



    public CustomExceptioin(ICustoizeErrorCode code) {
        this.message=code.getMessage();
    }
    public CustomExceptioin(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
