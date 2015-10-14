package com.dream.library.base;

import android.app.Application;

import com.dream.library.AppException;
import com.dream.library.utils.ImageLoaderHelper;
import com.dream.library.volley.VolleyHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:55
 * Description: EasyFrame
 */
public abstract class BaseLibApplication extends Application {

    private static BaseLibApplication instance;

    /**
     * 获得当前app运行的AppContext
     *
     * @return BaseLibApplication
     */
    public static BaseLibApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        // 错误信息捕获
        Thread.setDefaultUncaughtExceptionHandler(AppException
            .getAppExceptionHandler(this));
        initVolley(true);
        initImageLoader(true);
    }

    protected void initVolley(boolean isNeed) {
        if (!isNeed) {
            return;
        }
        // 初始化Volley
        VolleyHelper.getInstance().init(this);
    }

    protected void initImageLoader(boolean isNeed) {
        if (!isNeed) {
            return;
        }
        // 初始化ImageLoader
        ImageLoader.getInstance().init(
            ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration());
    }
}
