package com.liuxu.demo.exception;

import com.liuxu.demo.result.ResultCode;

public class MyExceptionHandler {

    public static void publish(ResultCode resultCode) throws MyException {
        throw new MyException(resultCode.getCode(), resultCode.getMessage());
    }

    public static void publish(ResultCode resultCode, String arg) throws MyException {
        String msg = resultCode.getMessage().replace("{0}",arg);
        throw new MyException(resultCode.getCode(), msg);
    }

}
