package com.likefood.utils;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class EncodingUtils {
    // s 为 Base64 编码的 byte 数组
    public static String toUTF8(String s) {
        String charset = "GB18030";
        String rtn = s;

        try {
            byte[] bytes = s.getBytes("ISO-8859-1");

//            CharsetDetector detector = new CharsetDetector();
//            detector.setText(bytes);
//
//            CharsetMatch cm = detector.detect();
//
//            if (cm != null) {
//                int confidence = cm.getConfidence();
////                System.out.println("Encoding: " + cm.getName() + " - Confidence: " + confidence + "%");
//                if (confidence > 50) {
//                    charset = cm.getName();
//                }
//            }
            rtn = new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {

        }
        return rtn;
    }

    public static String getEncoding(String s) {
        String charset = "GB18030";

//        try {
//            byte[] bytes = s.getBytes("ISO-8859-1");
//
//            CharsetDetector detector = new CharsetDetector();
//            detector.setText(bytes);
//
//            CharsetMatch cm = detector.detect();
//
//            if (cm != null) {
//                int confidence = cm.getConfidence();
//                if (confidence > 50) {
//                    charset = cm.getName();
//                }
//            }
//        } catch (UnsupportedEncodingException e) {
//
//        }
        return charset;
    }
    public static byte[] encode(String s, String encodingName) {
        byte[] rtn = null;
        try {
            rtn = new String(s.getBytes("UTF-8"), encodingName).getBytes(encodingName);
        } catch (Exception e){

        }
        return rtn;
    }
}
