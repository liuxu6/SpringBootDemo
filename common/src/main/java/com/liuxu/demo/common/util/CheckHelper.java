package com.liuxu.demo.common.util;

import com.liuxu.demo.common.exception.MyException;
import com.liuxu.demo.common.exception.MyExceptionHandler;
import com.liuxu.demo.common.result.ResultCode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckHelper {
    public static void checkIsNotEmpty(String key, Object... value) throws MyException {
        boolean emptyFlag = true;
        if (value != null && value.length > 0) {
            for (Object o : value) {
                if (o instanceof String) {
                    if (!"".equals(o)) {
                        emptyFlag = false;
                    }
                } else if (o != null) {
                    emptyFlag = false;
                }
            }
        }
        if (emptyFlag) {
            MyExceptionHandler.publish(ResultCode.VALUE_NOT_EMPTY, key);
        }
    }

    public static String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }
}
