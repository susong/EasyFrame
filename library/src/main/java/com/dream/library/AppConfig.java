package com.dream.library;

import android.content.Context;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 * <p>
 * author FireAnt（http://my.oschina.net/LittleDY）
 * created 2014年9月25日 下午5:29:00
 */
public class AppConfig {

    public static String APP_NAME = "EasyFrame";

    public static boolean IS_DEBUG = true;

    /**
     * UI设计的基准宽度.
     */
    public static final int UI_WIDTH = 720;

    /**
     * UI设计的基准高度.
     */
    public static final int UI_HEIGHT = 1080;

    public final static String APP_CONFIG = "config";

    public final static String CONF_COOKIE = "cookie";

    /**
     * 默认 SharePreferences文件名.
     */
    public static final String SHARED_PATH = "app_share";

    /**
     * 默认文件下载地址.
     */
    public static final String DOWNLOAD_DIR = "download";

    /**
     * APP文件缓存目录.
     */
    public static final String CACHE_DIR = "cache";

    /**
     * DB目录.
     */
    public static final String DB_DIR = "db";

    /**
     * LOG目录
     */
    public static final String LOG_DIR = "log";


    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }


}
