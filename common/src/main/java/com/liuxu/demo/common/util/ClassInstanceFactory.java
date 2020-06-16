//package com.liuxu.demo.common.util;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ClassInstanceFactory {
//
//    private ClassInstanceFactory(){}
//
//    public static <T> T create(Class<?> cls, String value){
//        try {
//            // Object object = cls.newInstance();
//            Object object = cls.getDeclaredConstructor().newInstance();
//            setValue(object, value);
//            return (T) object;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static void setValue(Object object, String value){
//
//            String[] results = value.split("\\|");
//            for (String result : results) {
//                String[] attVal = result.split(":");
//                try {
//                    Field field = object.getClass().getDeclaredField(attVal[0]);
//                    Method setMethod = object.getClass().getDeclaredMethod("set"+ initcap(attVal[0]), field.getType());
//                    Object val = convertAttributeValue(attVal[1], field.getType().getName());
//                    setMethod.invoke(object, val);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//    }
//
//    private static String initcap(String str) {
//        if (str == null || "".equals(str)){
//            return str;
//        }
//        if (str.length() == 1){
//            return str.toUpperCase();
//        }else
//            return str.substring(0,1).toUpperCase() + str.substring(1);
//    }
//
//    private static Object convertAttributeValue(String value, String Type){
//        switch (Type){
//            case "long":
//            case "java.lang.Long":
//                return Long.parseLong(value);
//
//            case "int":
//            case "java.lang.Integer":
//                return Integer.parseInt(value);
//
//            case "double":
//            case "java.lang.Double":
//                return Double.parseDouble(value);
//
//            case "java.util.Date":
//                SimpleDateFormat simpleDateFormat = null;
//                if (value.matches("\\d{4}-\\d{2}-\\d{2}")){
//                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                }else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
//                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                }else {
//                    return new Date();
//                }
//                try {
//                    return simpleDateFormat.parse(value);
//                } catch (ParseException e) {
//                    return new Date();
//                }
//        }
//        return value;
//    }
//}
