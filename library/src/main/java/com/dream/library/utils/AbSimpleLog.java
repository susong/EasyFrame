package com.dream.library.utils;

import android.util.Log;

import com.dream.library.AppConfig;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: 简单日志工具类
 */
@SuppressWarnings("unused")
public class AbSimpleLog {
    /**
     * This flag to indicate the log is enabled or disabled.
     */
    private static boolean isLogEnable = AppConfig.IS_DEGUB;

    /**
     * Disable the log output.
     */
//    public static void disableLog() {
//        isLogEnable = false;
//    }

    /**
     * Enable the log output.
     */
//    public static void enableLog() {
//        isLogEnable = true;
//    }

    /**
     * Debug
     *
     * @param tag tag
     * @param msg msg
     */
    public static void d(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Information
     *
     * @param tag tag
     * @param msg msg
     */
    public static void i(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.i(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Verbose
     *
     * @param tag tag
     * @param msg msg
     */
    public static void v(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.v(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Warning
     *
     * @param tag tag
     * @param msg msg
     */
    public static void w(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.w(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Error
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (isLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.e(tag, rebuildMsg(stackTraceElement, msg));
        }
    }

    /**
     * Error
     *
     * @param tag tag
     * @param ex  Throwable
     */
    public static void e(String tag, Throwable ex) {
        if (isLogEnable) {
            StringWriter writer = new StringWriter();
            ex.printStackTrace(new PrintWriter(writer));
            String errorMsg = writer.toString();
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.e(tag, rebuildMsg(stackTraceElement, errorMsg));
        }
    }

    /**
     * Rebuild Log Msg
     *
     * @param msg msg
     * @return String
     */
    private static String rebuildMsg(StackTraceElement stackTraceElement, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("Thread:")
            .append(Thread.currentThread().getName())
            .append(" ")
            .append(getSimpleClassName(stackTraceElement.getClassName()))
            .append(".")
            .append(stackTraceElement.getMethodName())
            .append(" (")
            .append(stackTraceElement.getFileName())
            .append(":")
            .append(stackTraceElement.getLineNumber())
            .append(") ")
            .append(msg);
        return sb.toString();
    }

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }
}
