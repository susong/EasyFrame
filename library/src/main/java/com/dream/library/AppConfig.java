package com.dream.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午5:29:00
 */
public class AppConfig {

    /**
     * UI设计的基准宽度.
     */
    public static final int UI_WIDTH = 720;

    /**
     * UI设计的基准高度.
     */
    public static final int UI_HEIGHT = 1080;

    private final static String APP_CONFIG = "config";

    public final static String CONF_COOKIE = "cookie";

    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";

    public static final String APP_NAME = "EasyFrame";

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

    /**
     * 缓存大小  单位10M.
     */
    public static final int MAX_CACHE_SIZE_INBYTES = 10 * 1024 * 1024;

    /**
     * The Constant CONNECTEXCEPTION.
     */
    public static final String CONNECT_EXCEPTION = "无法连接到网络";

    /**
     * The Constant UNKNOWNHOSTEXCEPTION.
     */
    public static final String UNKNOWN_HOST_EXCEPTION = "连接远程地址失败";

    /**
     * The Constant SOCKETEXCEPTION.
     */
    public static final String SOCKET_EXCEPTION = "网络连接出错，请重试";

    /**
     * The Constant SOCKETTIMEOUTEXCEPTION.
     */
    public static final String SOCKET_TIMEOUT_EXCEPTION = "连接超时，请重试";

    /**
     * The Constant NULLPOINTEREXCEPTION.
     */
    public static final String NULL_POINTER_EXCEPTION = "抱歉，远程服务出错了";

    /**
     * The Constant NULLMESSAGEEXCEPTION.
     */
    public static final String NULL_MESSAGE_EXCEPTION = "抱歉，程序出错了";

    /**
     * The Constant CLIENTPROTOCOLEXCEPTION.
     */
    public static final String CLIENTP_ROTOCOL_EXCEPTION = "Http请求参数错误";

    /**
     * 参数个数不够.
     */
    public static final String MISSING_PARAMETERS = "参数没有包含足够的值";

    /**
     * The Constant REMOTESERVICEEXCEPTION.
     */
    public static final String REMOTE_SERVICE_EXCEPTION = "抱歉，远程服务出错了";

    /**
     * 页面未找到.
     */
    public static final String NOT_FOUND_EXCEPTION = "页面未找到";

    /**
     * 其他异常.
     */
    public static final String UNTREATED_EXCEPTION = "未处理的异常";


    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    /**
     * 获取Preference设置
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator
                + APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String... key) {
        Properties props = get();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }
}
