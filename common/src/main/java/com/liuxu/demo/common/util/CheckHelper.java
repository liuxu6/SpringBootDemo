package com.liuxu.demo.common.util;

import com.liuxu.demo.common.exception.MyException;
import com.liuxu.demo.common.exception.MyExceptionHandler;
import com.liuxu.demo.common.result.ResultCode;

public class CheckHelper {
    public static void checkIsNotEmpty(String key, Object... value) throws MyException {
        boolean emptyFlag = true;
        if (value != null && value.length > 0) {
            for (int i = 0; i < value.length; i++) {
                if (value[i] instanceof String) {
                    if (value[i] != null && !"".equals(value[i])) {
                        emptyFlag = false;
                    }
                }
                else if (value[i] != null) {
                    emptyFlag = false;
                }
            }
        }
        if (emptyFlag) {
            MyExceptionHandler.publish(ResultCode.VALUE_NOT_EMPTY, key);
        }
    }

}
