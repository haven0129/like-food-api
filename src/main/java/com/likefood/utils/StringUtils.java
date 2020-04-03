package com.likefood.utils;

public class StringUtils {
    public static Boolean isEmptyOrNull(String str){
        if(str == null || str.trim().equals("")){
            return true;
        }
        return false;
    }

    public static Boolean isNotEmptyOrNull(String str){
        if(str == null || str.trim().equals("")){
            return false;
        }
        return true;
    }

    public static String getStringValue(String str){
        if(isEmptyOrNull(str)){
            return "";
        }
        return str;
    }



    public static String getLikeString(String param){
        String result = "%%";
        if(StringUtils.isNotEmptyOrNull(param)){
            result = "%" + param.trim() + "%";
        }
        return result;
    }


}
