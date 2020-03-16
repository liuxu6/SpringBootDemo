package com.liuxu.demo.exception;

import com.liuxu.demo.result.ResultCode;

import java.io.Serializable;
import java.util.Date;

public class DemoException extends Exception implements Serializable {

    private static final long serialVersionUID = 4818496246533237841L;

    private int code;
    private String message;

    public DemoException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
