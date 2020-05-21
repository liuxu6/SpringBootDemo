package com.liuxu.demo.common.result;

public enum ResultCode {

    SUCCESS(200,"成功"),

    USER_NOT_LOGIN(500,"用户未登录"),

    PASSWORD_IS_ERROR(500, "用户名不存在或密码错误"),

    VALUE_NOT_EMPTY(500,"{0}不能为空"),

    DATA_NOT_EXISTS(500,"{0}不存在"),

    DATA_IS_EXISTS(500,"{0}已存在");


    private int code;
    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }



}
