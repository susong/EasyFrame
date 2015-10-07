package com.dream.library.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.dream.library.base.BaseApplication;


/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: Toast 工具类
 */
public class AbToastUtils {

    private AbToastUtils() {
        throw new AssertionError();
    }

    private static final String TEXT = "TEXT";

    private static Context mContext = null;

    public static final int SHOW_TOAST = 0;

    private static Toast mToast;

    public static void show(int resId, Object... args) {
        show(resId, Toast.LENGTH_SHORT, args);
    }

    public static void show(String text, Object... args) {
        show(text, Toast.LENGTH_SHORT, args);
    }

    public static void show(int resId, int duration, Object... args) {
        show(BaseApplication.getInstance(), resId, duration, args);
    }

    public static void show(String text, int duration, Object... args) {
        show(BaseApplication.getInstance(), text, duration, args);
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, resId, Toast.LENGTH_SHORT, args);
    }

    public static void show(Context context, String text, Object... args) {
        show(context, text, Toast.LENGTH_SHORT, args);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, context.getResources().getString(resId), duration, args);
    }

    // 核心方法
    public static void show(Context context, String text, int duration, Object... args) {
        mContext = context;
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(context, String.format(text, args), duration);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    public static void showInThread(Context context, int resId, int duration) {
        showInThread(context, context.getResources().getString(resId), duration);
    }

    public static void showInThread(Context context, String text, int duration) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TEXT, text);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }

    public static void showInThread(Context context, int resId) {
        showInThread(context, context.getResources().getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showInThread(Context context, String text) {
        showInThread(context, text, Toast.LENGTH_SHORT);
    }

    public static void showInThread(int resId) {
        showInThread(BaseApplication.getInstance(),resId, Toast.LENGTH_SHORT);
    }

    public static void showInThread(String text) {
        showInThread(BaseApplication.getInstance(),text, Toast.LENGTH_SHORT);
    }

    private static final Handler baseHandler = new MyHandler();

    private static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TOAST:
                    show(mContext, msg.getData().getString(TEXT));
                    break;
                default:
                    break;
            }
        }
    }
}
