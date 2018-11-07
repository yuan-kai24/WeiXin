//package com.imooc.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import java.util.Properties;
//
//public class Init {
//    private static Properties userInfo = new Properties();
//    private static File fl = new File("F:\\eclipse\\Weixin\\src\\ry\\WeixinUserInfo.properties");
//
//    public static String getValue(String key) {
//        String val = null;
//        BufferedReader buf = null;
//
//        try {
//            buf = new BufferedReader(new InputStreamReader(new FileInputStream(fl), "utf-8"));
//            userInfo.load(buf);
//            val = userInfo.getProperty(key);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            try {
//                if (buf != null) {
//                    buf.close();
//                }
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return val;
//    }
//
//}
//
