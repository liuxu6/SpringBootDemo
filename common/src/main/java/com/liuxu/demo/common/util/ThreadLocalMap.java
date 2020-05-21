/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.liuxu.demo.common.util;

import java.util.Map;

/** 
 * <Description> 保存session相关的信息<br>
 */
public class ThreadLocalMap {
    
    /**
     * currentSession
     */
    private static ThreadLocal<Map<String, Object>> currentSession = new ThreadLocal<Map<String, Object>>();
    

    public static void setUp(Map<String, Object> map) {
        currentSession.set(map);
    }


    public static void set(String key, Object value) {
        Map<String, Object> session = currentSession.get();
        if (session != null) {
            session.put(key, value);
        }
    }


    public static Object get(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null) {
            return session.get(key);
        }
        else {
            return null;
        }
    }

    public static Long getLong(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null && session.get(key) != null) {
            return (Long) session.get(key);
        }
        else {
            return null;
        }
    }

    public static String getString(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null && session.get(key) != null) {
            return session.get(key).toString();
        }
        else {
            return null;
        }
    }

    public static Boolean getBoolean(String key) {
        Map<String, Object> session = currentSession.get();
        if (session != null && session.get(key) != null) {
            return (Boolean) session.get(key);
        }
        else {
            return null;
        }
    }
    public static String setUserName() {
        return getString("USER_NAME");
    }
}
