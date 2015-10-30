package com.dream.library.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 * <p>
 * author liux (http://my.oschina.net/liux)
 * version 1.0
 * created 2012-3-21
 */
@SuppressWarnings("unused")
public class AbStringUtils {

    private final static Pattern EMAIL = Pattern
        .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern PHONE = Pattern
        .compile("^1\\d{10}$");

    private final static Pattern IMG_URL = Pattern
        .compile(".*?(gif|jpeg|png|jpg|bmp)");

    private final static Pattern URL = Pattern
        .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~/])+$");

    public final static Pattern LEGAL_NAME = Pattern
        .compile("^[a-zA-Z_0-9\\.`~!@#$%^&*()+-=/\\u4e00-\\u9fa5]{4,20}$");

    public final static Pattern LEGAL_PASSWORD = Pattern
        .compile("^[a-zA-Z_0-9\\.`~!@#$%^&*()+-=/]{6,20}$");

    private final static ThreadLocal<SimpleDateFormat> dateFormatterLong = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormatterShort = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
    };

    /**
     * 判断给定字符串是否空白串。若输入字符串为null或空字符串，返回true
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equalsIgnoreCase("null");
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmptyStrict(String str) {
        if (isEmpty(str)) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 用户名（可自定义）
     *
     * @param name 用户名
     * @return boolean
     */
    public static boolean isLegalName(String name) {
        return !isEmpty(name) && LEGAL_NAME.matcher(name).matches();
    }

    /**
     * 密码（可自定义）
     *
     * @param password 密码
     * @return boolean
     */
    public static boolean isLegalPassword(String password) {
        return !isEmpty(password) && LEGAL_PASSWORD.matcher(password).matches();
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email email
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return !isEmpty(email) && EMAIL.matcher(email).matches();
    }

    /**
     * 简单判断是不是一个合法的手机号码
     *
     * @param phone phone
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return !isEmpty(phone) && PHONE.matcher(phone).matches();
    }

    /**
     * 判断一个url是否为图片url
     *
     * @param url url
     * @return boolean
     */
    public static boolean isImgUrl(String url) {
        return !isEmpty(url) && IMG_URL.matcher(url).matches();
    }

    /**
     * 判断是否为一个合法的url地址
     *
     * @param url url
     * @return boolean
     */
    public static boolean isUrl(String url) {
        return !isEmpty(url) && URL.matcher(url).matches();
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * 字符串转整数
     *
     * @param str      str
     * @param defValue int
     * @return int
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj string
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b string
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is InputStream
     * @return String
     */
    public static String toConvertString(InputStream is) {
        StringBuilder res = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line).append("<br>");
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
                read.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

    /***
     * 截取字符串
     *
     * @param start 从那里开始，0算起
     * @param num   截取多少个
     * @param str   截取的字符串
     * @return String
     */
    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > length) {
            start = length;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > length) {
            end = length;
        }
        return str.substring(start, end);
    }
}
