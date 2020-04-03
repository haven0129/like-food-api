package com.likefood.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map buildMap(String key, Object value){
        Map map = new HashMap();
        map.put(key, value);
        return map;
    }

    public static Map buildMap(String key, Object value, String key2, Object value2){
        Map map = buildMap(key, value);
        map.put(key2, value2);
        return map;
    }

    public static Map buildMap(String key, Object value, String key2, Object value2, String key3, Object value3){
        Map map = buildMap(key, value, key2, value2);
        map.put(key3, value3);
        return map;
    }


}
