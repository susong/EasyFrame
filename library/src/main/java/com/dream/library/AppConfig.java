package com.dream.library;

import android.content.Context;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午5:29:00
 */
public class AppConfig {

    public static String APP_NAME = "EasyFrame";

    public static boolean IS_DEGUB = true;

    public final static String APP_CONFIG = "config";

    public final static String CONF_COOKIE = "cookie";

    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";

    /**
     * 默认 SharePreferences文件名.
     */
    public static final String SHARED_PATH = "app_share";

    /**
     * 默认图片下载地址.
     */
    public static final String IMAGE_DOWNLOAD_DIR = "images_download";

    /**
     * 默认文件下载地址.
     */
    public static final String FILE_DOWNLOAD_DIR = "download";

    /**
     * APP文件缓存目录.
     */
    public static final String FILE_CACHE_DIR = "cache";

    /**
     * APP图片缓存目录.
     */
    public static final String IMAGES_CACHE_DIR = "images_cache";

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
