package com.liuxu.demo.exception;

import com.liuxu.demo.result.ResultCode;

import java.io.Serializable;
import java.util.Date;

public class MyException extends Exception implements Serializable {

    private static final long serialVersionUID = 4818496246533237841L;

    private int code;
    private String message;

    public MyException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
