package com.likefood.utils.message;


public class RandomNumberUtils {
    public static String get6NumberStringRandom(){
        int str = (int)((Math.random()*9+1)*100000);    // Math.random()包括0不包括1
        return String.valueOf(str);
    }

}
