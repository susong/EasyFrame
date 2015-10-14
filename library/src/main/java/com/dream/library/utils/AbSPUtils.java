package com.dream.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/14 下午11:19
 * Description: EasyFrame
 */
public class AbSPUtils {

    private static final String TAG = AbSPUtils.class.getSimpleName();

    private static AbSPUtils mAbSPUtils;

    private Context mContext;

    private AbSPUtils() {

    }

    public static AbSPUtils init(Context context) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        if (mAbSPUtils == null) {
            synchronized (AbSPUtils.class) {
                if (mAbSPUtils == null) {
                    mAbSPUtils = new AbSPUtils();
                    mAbSPUtils.mContext = context;
                }
            }
        }
        return mAbSPUtils;
    }

    public static AbSPUtils getAbSPUtils() {
        if (mAbSPUtils == null) {
            throw new RuntimeException("please invoke init(context) method first");
        }
        return mAbSPUtils;
    }

    /**
     * 获取Preference设置
     */
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

}
