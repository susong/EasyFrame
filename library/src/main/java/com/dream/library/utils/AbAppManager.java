package com.dream.library.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: EasyFrame
 */
@SuppressWarnings("unused")
public class AbAppManager {

    private static final String TAG = AbAppManager.class.getSimpleName();

    private static AbAppManager mAbAppManager = null;
    private static Stack<Activity> mActivityStack = new Stack<>();

    private AbAppManager() {

    }

    /**
     * 单一实例
     */
    public static AbAppManager getAbAppManager() {
        if (mAbAppManager == null) {
            synchronized (AbAppManager.class) {
                if (mAbAppManager == null) {
                    mAbAppManager = new AbAppManager();
                }
            }
        }
        return mAbAppManager;
    }

    public int size() {
        return mActivityStack.size();
    }

    /**
     * 添加Activity到堆栈
     */
    public synchronized void addActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.add(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return size() > 0 ? mActivityStack.lastElement() : null;
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        finishActivity(mActivityStack.lastElement());
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity... activity) {
        if (activity == null || activity.length == 0) {
            return;
        }
        for (Activity a : activity) {
            if (!a.isFinishing()) {
                mActivityStack.remove(a);
                a.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity，可能为多个
     */
    public void finishActivity(Class<?>... cls) {
        if (cls == null || cls.length == 0) {
            return;
        }
        Stack<Activity> activityStackTemp = new Stack<>();
        for (Class<?> c : cls) {
            for (Activity a : mActivityStack) {
                if (a.getClass().getName().equals(c.getName())) {
                    activityStackTemp.add(a);
                }
            }
        }
        mActivityStack.removeAll(activityStackTemp);
        for (Activity a : activityStackTemp) {
            if (a != null && !a.isFinishing()) {
                a.finish();
            }
        }
        activityStackTemp.clear();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity a : mActivityStack) {
            if (a != null && !a.isFinishing()) {
                a.finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
