package com.liuxu.demo.common.exception;

import java.io.Serializable;

public class MyException extends Exception implements Serializable {

    private static final long serialVersionUID = 4818496246533237841L;

    private final int code;
    private final String message;

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
