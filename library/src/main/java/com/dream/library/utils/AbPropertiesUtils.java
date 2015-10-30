package com.dream.library.utils;

import android.content.Context;

import com.dream.library.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/15 上午12:37
 * Description: 用来保存配置数据与SharedPreferences用法类似
 */
@SuppressWarnings("unused")
public class AbPropertiesUtils {

    private static final String TAG = AbPropertiesUtils.class.getSimpleName();

    private static AbPropertiesUtils mAbPropertiesUtils;

    private Context mContext;

    private AbPropertiesUtils() {

    }

    public static AbPropertiesUtils init(Context context) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        if (mAbPropertiesUtils == null) {
            synchronized (AbPropertiesUtils.class) {
                if (mAbPropertiesUtils == null) {
                    mAbPropertiesUtils = new AbPropertiesUtils();
                    mAbPropertiesUtils.mContext = context;
                }
            }
        }
        return mAbPropertiesUtils;
    }

    public static AbPropertiesUtils getAbPropertiesUtils() {
        if (mAbPropertiesUtils == null) {
            throw new RuntimeException("please invoke init(context) method first");
        }
        return mAbPropertiesUtils;
    }

    public Properties getProps() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取data/data/{包名}/files目录下的
            // 文件名为AppConfig.APP_CONFIG的值(默认值config)的文件
            // fis = mContext.openFileInput(AppConfig.APP_CONFIG);

            // 读取data/data/{包名}/app_config的目录下的
            // 文件名为AppConfig.APP_CONFIG的值(默认值config)的文件
            File dirConf = mContext.getDir(AppConfig.APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + AppConfig.APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
            AbSimpleLog.e(TAG, e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                AbSimpleLog.e(TAG, e);
            }
        }
        return props;
    }

    public String get(String key) {
        Properties props = getProps();
        String value = (props != null) ? props.getProperty(key) : null;
        AbSimpleLog.i(TAG, "properties get : {" + key + " = " + value + "}");
        return value;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把文件建在data/data/{包名}/files目录下的
            // 文件名为AppConfig.APP_CONFIG的值(默认值config)
            // fos = mContext.openFileOutput(AppConfig.APP_CONFIG, Context.MODE_PRIVATE);

            // 把文件建在data/data/{包名}/app_config的目录下的
            // 文件名为AppConfig.APP_CONFIG的值(默认值config)
            File dirConf = mContext.getDir(AppConfig.APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, AppConfig.APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            AbSimpleLog.e(TAG, e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                AbSimpleLog.e(TAG, e);
            }
        }
    }

    public void set(Properties ps) {
        Properties props = getProps();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = getProps();
        props.setProperty(key, value);
        setProps(props);
        AbSimpleLog.i(TAG, "properties set : {" + key + " = " + value + "}");
    }

    public void remove(String... key) {
        Properties props = getProps();
        for (String k : key) {
            props.remove(k);
        }
        setProps(props);
        AbSimpleLog.i(TAG, "properties remove key : " + Arrays.asList(key).toString());
    }
}
