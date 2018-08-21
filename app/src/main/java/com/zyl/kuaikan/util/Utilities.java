package com.zyl.kuaikan.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    /**
     * 获得网络时间
     * @return
     */
    public static Date getNetTime(){
        String webUrl = "http://www.ntsc.ac.cn";
        try {
            URL url = new URL(webUrl);
            URLConnection uc = url.openConnection();
            uc.setReadTimeout(5000);
            uc.setConnectTimeout(5000);
            uc.connect();
            long correctTime = uc.getDate();
            Date date = new Date(correctTime);
            return date;
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * 验证手机号码的合法性
     * @param telephone
     * @return
     */
    public static boolean verifyPhoneNum(String telephone){
        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码的合法性
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password){
        if(password.length()<8||password.length()>30){
            return false;
        }
        Pattern pattern=Pattern.compile("^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
