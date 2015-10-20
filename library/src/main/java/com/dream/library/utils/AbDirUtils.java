package com.dream.library.utils;

import android.os.Environment;

import com.dream.library.AppConfig;

import java.io.File;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/15 下午6:07
 * Description: EasyFrame
 */
public class AbDirUtils {
    /**
     * 默认应用目录
     */
    private static String mAppPath = File.separator + AppConfig.APP_NAME + File.separator;

    /**
     * 默认应用目录.
     */
    private static String mAppDir = null;

    /**
     * 默认下载目录.
     */
    private static String mDownloadDir = null;

    /**
     * 默认缓存目录.
     */
    private static String mCacheDir = null;

    /**
     * 默认数据库文件的目录.
     */
    private static String mDbDir = null;

    /**
     * 默认日志文件的目录.
     */
    private static String mLogDir = null;

    /**
     * 默认应用目录.
     */
    public static String getAppDir() {
        if (mAppDir != null) {
            return mAppDir;
        } else {
            // 默认应用目录
            mAppDir = createDir(mAppPath);
        }
        return mAppDir;
    }

    /**
     * 默认下载目录.
     */
    public static String getDownloadDir() {
        if (mDownloadDir != null) {
            return mDownloadDir;
        } else {
            //默认下载目录.
            String downloadPath = mAppPath + AppConfig.DOWNLOAD_DIR + File.separator;
            mDownloadDir = createDir(downloadPath);
        }
        return mDownloadDir;
    }

    /**
     * 默认缓存目录.
     */
    public static String getCacheDir() {
        if (mCacheDir != null) {
            return mCacheDir;
        } else {
            //默认缓存目录.
            String cachePath = mAppPath + AppConfig.CACHE_DIR + File.separator;
            mCacheDir = createDir(cachePath);
        }
        return mCacheDir;
    }

    /**
     * 默认数据库文件的目录.
     */
    public static String getDbDir() {
        if (mDbDir != null) {
            return mDbDir;
        } else {
            //默认DB目录.
            String dbPath = mAppPath + AppConfig.DB_DIR + File.separator;
            mDbDir = createDir(dbPath);
        }
        return mDbDir;
    }

    /**
     * 默认日志文件的目录.
     */
    public static String getLogDir() {
        if (mLogDir != null) {
            return mLogDir;
        } else {
            //默认Log目录
            String logPath = mAppPath + AppConfig.LOG_DIR + File.separator;
            mLogDir = createDir(logPath);
        }
        return mLogDir;
    }

    private static String createDir(String path) {
        boolean isHaveSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (!isHaveSDCard) {
            return null;
        }
        File directory = Environment.getExternalStorageDirectory();
        File file = new File(directory.getAbsolutePath() + path);
        if (!file.exists()) {
            boolean isSuccess = file.mkdirs();
            if (isSuccess) {
                return file.getPath() + File.separator;
            } else {
                return null;
            }
        }
        return file.getPath() + File.separator;
    }
}
