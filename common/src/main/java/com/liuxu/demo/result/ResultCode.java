package com.liuxu.demo.result;

public enum ResultCode {

    SUCCESS(200,"成功"),

    USER_NOT_LOGIN(500,"用户未登录");


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
