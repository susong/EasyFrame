package com.dream.library.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Assets目录工具类，用于访问各种Assets目录下的文件
 *
 * @author asus1
 */
public class AssetsUtil {

    /**
     * 安装assets目录下的文件
     *
     * @param fileName 文件名称
     */
    public static void installapk(Context context, String fileName) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream stream = assetManager.open(fileName);
            if (stream == null) {
                Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
                return;
            }

            String folder = "/mnt/sdcard/sm/";
            File f = new File(folder);
            if (!f.exists()) {
                f.mkdir();
            }
            String apkPath = "/mnt/sdcard/sm/test.apk";
            File file = new File(apkPath);
            if (!file.exists())
                file.createNewFile();
            writeStreamToFile(stream, file);
            installApk(context, apkPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeStreamToFile(InputStream stream, File file) {
        try {
            OutputStream output = null;
            try {
                output = new FileOutputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                try {
                    final byte[] buffer = new byte[1024];
                    int read;

                    while ((read = stream.read(buffer)) != -1)
                        output.write(buffer, 0, read);
                    output.flush();
                } finally {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void installApk(Context context, String apkPath) {
        AbAppUtils.installApk(context, apkPath);
    }
}
