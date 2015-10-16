package com.dream.library.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * © 2012 amsoft.cn
 * 名称：AbDateUtils.java
 * 描述：日期处理类.
 * <p>
 * author 还如一梦中
 * version v1.0
 * date：2013-01-18 下午11:52:13
 */
@SuppressWarnings("unused")
public class AbDateUtils {

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";

    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 时间日期格式化到月日.
     */
    public static final String dateFormatMD = "MM/dd";

    /**
     * 时分秒.
     */
    public static final String dateFormatHMS = "HH:mm:ss";

    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";

    /**
     * long类型的年月日时分秒
     */
    public static final String dateFormatYMDHMSlong = "yyyyMMddHHmmss";

    /**
     * long类型的年月日
     */
    public static final String dateFormatYMDlong = "yyyyMMdd";

    /**
     * 上午.
     */
    public static final String AM = "AM";

    /**
     * 下午.
     */
    public static final String PM = "PM";


    /**
     * ISO 8601日期格式yyyy-MM-ddThh:mm:ss.SSSZ 转化为 正常格式yyyy-MM-dd HH:mm:ss
     * 2014-10-24T02:58:05.932Z -> 2014-10-24 10:58:05
     *
     * @param utcTime time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String convertUtc2Local(String utcTime) {
        String time = "";
        if (utcTime != null) {
            // 2014-10-24T02:58:05.932Z
            SimpleDateFormat utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
            utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gpsUTCDate = null;
            try {
                gpsUTCDate = utcFormatter.parse(utcTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat localFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            localFormatter.setTimeZone(TimeZone.getDefault());
            assert gpsUTCDate != null;
            time = localFormatter.format(gpsUTCDate.getTime());
        }
        return time;
    }

    /**
     * ISO 8601日期格式yyyy-MM-ddThh:mm:ss.SSSZ 转化为 正常格式yyyy-MM-dd HH:mm:ss
     * 2014-10-24T02:58:05.932Z -> 2014-10-24 10:58:05
     * 由于上面的 convertUtc2Local 方法在有些android版本中无法正确格式化，因此用此方法
     * 直接 +8 小时
     *
     * @param utcTime time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String convertUtc2LocalPro(String utcTime) {
        if (TextUtils.isEmpty(utcTime)) {
            return "";
        }
        // 2014-10-24T02:58:05.932Z
        SimpleDateFormat utcFormatter, localFormatter;
        utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.ENGLISH);
        Date date;
        Calendar cal = Calendar.getInstance();
        try {
            date = utcFormatter.parse(utcTime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.HOUR_OF_DAY, +8);
        date = cal.getTime();
        localFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return localFormatter.format(date);
    }

    /**
     * 获取当前毫秒值
     *
     * @return 毫秒值
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 字符串时间转毫秒
     *
     * @param dateStr 字符串时间 yyyy-MM-dd HH:mm:ss
     * @return 毫秒
     */
    public static long convertDateStr2Millis(String dateStr) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateYMDHMS(dateStr));
        return c.getTimeInMillis();
    }

    /**
     * 毫秒转字符串时间
     *
     * @param millis 毫秒
     * @param format 格式
     * @return 字符串时间
     */
    public static String convertMillis2DateStr(long millis, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date(millis));
    }

    /**
     * 毫秒转字符串时间
     *
     * @param millis 毫秒
     * @return 字符串时间
     */
    public static String convertMillis2DateStr(long millis) {
        return new SimpleDateFormat(dateFormatYMDHMS, Locale.CHINA).format(new Date(millis));
    }

    /**
     * 毫秒转时间
     *
     * @param millis 毫秒
     * @return 时间
     */
    public static Date convertMillis2Date(long millis) {
        return new Date(millis);
    }

    /**
     * 时间转字符串时间
     *
     * @param date 时间
     * @return 字符串时间
     */
    public static String convertDate2Str(Date date) {
        return new SimpleDateFormat(dateFormatYMDHMS, Locale.CHINA).format(date);
    }

    /**
     * 时间转字符串时间
     *
     * @param date   时间
     * @param format 格式
     * @return 字符串时间
     */
    public static String convertDate2Str(Date date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date());
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYMDHMS() {
        return getCurrentDate(dateFormatYMDHMS);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyy-MM-dd
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYMD() {
        return getCurrentDate(dateFormatYMD);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyy-MM
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYM() {
        return getCurrentDate(dateFormatYM);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyy-MM-dd HH:mm
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYMDHM() {
        return getCurrentDate(dateFormatYMDHM);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：MM/dd
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataMD() {
        return getCurrentDate(dateFormatMD);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：HH:mm:ss
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataHMS() {
        return getCurrentDate(dateFormatHMS);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：HH:mm
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataHM() {
        return getCurrentDate(dateFormatHM);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyyMMddHHmmss
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYMDHMSlong() {
        return getCurrentDate(dateFormatYMDHMSlong);
    }

    /**
     * 获取表示当前日期时间的字符串,格式：yyyyMMdd
     *
     * @return String类型的当前日期时间
     */
    public static String getCurrentDataYMDlong() {
        return getCurrentDate(dateFormatYMDlong);
    }

    /**
     * 描述：获取表示当前日期时间的字符串(可偏移).
     *
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            Calendar c = Calendar.getInstance();
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }


    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String类型的日期时间转化为yyyy-MM-dd HH:mm:ss格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，yyyy-MM-dd HH:mm:ss
     */
    public static Date getDateYMDHMS(String strDate) {
        return getDateByFormat(strDate, dateFormatYMDHMS);
    }

    /**
     * String类型的日期时间转化为yyyy-MM-dd格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，yyyy-MM-dd
     */
    public static Date getDateYMD(String strDate) {
        return getDateByFormat(strDate, dateFormatYMD);
    }

    /**
     * String类型的日期时间转化为yyyy-MM格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，yyyy-MM
     */
    public static Date getDateYM(String strDate) {
        return getDateByFormat(strDate, dateFormatYM);
    }

    /**
     * String类型的日期时间转化为yyyy-MM-dd HH:mm格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，yyyy-MM-dd HH:mm
     */
    public static Date getDateYMDHM(String strDate) {
        return getDateByFormat(strDate, dateFormatYMDHM);
    }

    /**
     * String类型的日期时间转化为MM/dd格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，MM/dd
     */
    public static Date getDateMD(String strDate) {
        return getDateByFormat(strDate, dateFormatMD);
    }

    /**
     * String类型的日期时间转化为HH:mm:ss格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，HH:mm:ss
     */
    public static Date getDateHMS(String strDate) {
        return getDateByFormat(strDate, dateFormatHMS);
    }

    /**
     * String类型的日期时间转化为HH:mm格式的Date类型.
     *
     * @param strDate String形式的日期时间
     * @return 格式化字符串，HH:mm
     */
    public static Date getDateHM(String strDate) {
        return getDateByFormat(strDate, dateFormatHM);
    }

    /**
     * 描述：获取偏移之后的Date.
     *
     * @param date          日期时间
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = new GregorianCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate       String形式的日期时间
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date          the date
     * @param format        the format
     * @param calendarField the calendar field
     * @param offset        the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format, int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateFormatYMDHMS, Locale.CHINA);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format, Locale.CHINA);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format       格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }


    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getDiscrepancyDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        //先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays;
        int day;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getDiscrepancyHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h;
        int day = getDiscrepancyDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getDiscrepancyMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getDiscrepancyHour(date1, date2);
        int m;
        m = m1 - m2 + h * 60;
        return m;
    }

    /***
     * 计算两个时间差，返回的是的秒s
     *
     * @param date1 str
     * @param date2 str
     * @return long
     */
    public static long calDateDifferent(String date1, String date2) {

        long diff = 0;

        Date d1;
        Date d2;

        try {
            d1 = getDateYMDHMS(date1);
            d2 = getDateYMDHMS(date2);

            // 毫秒ms
            diff = d2.getTime() - d1.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diff / 1000;
    }

    /**
     * 返回long类型的今天的日期
     *
     * @return long
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        String curDate = convertDate2Str(cal.getTime(), dateFormatYMD);
        curDate = curDate.replace("-", "");
        return Long.parseLong(curDate);
    }

    /**
     * 描述：获取本周一.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周日.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format        the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            //当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 描述：判断是否是闰年()
     * (year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 400 != 0) || year % 400 == 0;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param dateStr str
     * @return boolean
     */
    public static boolean isToday(String dateStr) {
        boolean b = false;
        Date time = getDateYMDHMS(dateStr);
        Date today = new Date();
        if (time != null) {
            String nowDate = convertDate2Str(today);
            String timeDate = convertDate2Str(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param millis 毫秒
     * @return the string
     */
    public static String friendlyTime(long millis) {
        return friendlyTime(convertMillis2DateStr(millis));
    }

    /**
     * 以友好的方式显示时间
     *
     * @param dateStr 时间
     * @return string
     */
    public static String friendlyTime(String dateStr) {
        Date time;
        if (AbTimeZoneUtil.isInEasternEightZones()) {
            time = getDateYMDHMS(dateStr);
        } else {
            time = AbTimeZoneUtil.transformTime(getDateYMDHMS(dateStr), TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        }
        if (time == null) {
            return "Unknown";
        }
        String fTime;
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = convertDate2Str(cal.getTime(), dateFormatYMD);
        String paramDate = convertDate2Str(time, dateFormatYMD);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                fTime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                fTime = hour + "小时前";
            }
            return fTime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                fTime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
            } else {
                fTime = hour + "小时前";
            }
        } else if (days == 1) {
            fTime = "昨天";
        } else if (days == 2) {
            fTime = "前天 ";
        } else if (days > 2 && days < 31) {
            fTime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            fTime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            fTime = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            fTime = "3个月前";
        } else {
            fTime = convertDate2Str(time);
        }
        return fTime;
    }

    public static String friendlyTime2(String dateStr) {
        String res = "";
        if (AbStringUtils.isEmpty(dateStr)) {
            return "";
        }

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String currentData = getCurrentDate("MM-dd");
        int currentDay = AbStringUtils.toInt(currentData.substring(3));
        int currentMoth = AbStringUtils.toInt(currentData.substring(0, 2));

        int sMoth = AbStringUtils.toInt(dateStr.substring(5, 7));
        int sDay = AbStringUtils.toInt(dateStr.substring(8, 10));
        int sYear = AbStringUtils.toInt(dateStr.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        if (sDay == currentDay && sMoth == currentMoth) {
            res = "今天 / " + weekDays[getWeekOfDate(new Date())];
        } else if (sDay == currentDay + 1 && sMoth == currentMoth) {
            res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
        } else {
            if (sMoth < 10) {
                res = "0";
            }
            res += sMoth + "/";
            if (sDay < 10) {
                res += "0";
            }
            res += sDay + " / " + weekDays[getWeekOfDate(dt)];
        }
        return res;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt 日期
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static String getWeekOfDateStr(String strDate, String inFormat) {
        String week = "星期日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat, Locale.CHINA);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @return int
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @param date date
     * @return int
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
        week = week == 0 ? 52 : week;
        return week > 0 ? week : 1;
    }

    /**
     * 根据给定的日期判断是否为上下午.
     *
     * @param strDate the str date
     * @param format  the format
     * @return the time quantum
     */
    public static String getTimeQuantum(String strDate, String format) {
        Date data = getDateByFormat(strDate, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 12) {
            return "PM";
        } else {
            return "AM";
        }
    }

    /**
     * 根据给定的毫秒数算得时间的描述.
     *
     * @param milliseconds the milliseconds
     * @return the time description
     */
    public static String getTimeDescription(long milliseconds) {
        if (milliseconds > 1000) {
            //大于一分
            if (milliseconds / 1000 / 60 > 1) {
                long minute = milliseconds / 1000 / 60;
                long second = milliseconds / 1000 % 60;
                return minute + "分" + second + "秒";
            } else {
                //显示秒
                return milliseconds / 1000 + "秒";
            }
        } else {
            return milliseconds + "毫秒";
        }
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDataYMDHMS());
        System.out.println(convertDate2Str(getDateYMDHMS(getCurrentDataYMDHMS())));
        System.out.println(friendlyTime("2015-10-16 11:43:12"));
        System.out.println(friendlyTime2("2015-10-13 11:43:12"));
        System.out.println(getToday());
        System.out.println(getCurrentDataYMDHMSlong());
    }
}
