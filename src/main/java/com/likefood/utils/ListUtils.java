package com.likefood.utils;

import java.util.List;

public class ListUtils {
    public static <T> Boolean isNull(List<T> list) {
        if(list == null || list.size() == 0){
            return true;
        }
        return false;
    }
    public static <T> Boolean isNotNull(List<T> list) {
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }
}
