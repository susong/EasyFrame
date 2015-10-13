/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dream.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.dream.library.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

@SuppressWarnings("unused")
public class ImageLoaderHelper {

    private Context mContext = null;
    private static volatile ImageLoaderHelper instance = null;

    private ImageLoaderHelper() {

    }

    private ImageLoaderHelper(Context context) {
        mContext = context;
    }

    public static ImageLoaderHelper getInstance(Context context) {
        if (null == instance) {
            synchronized (ImageLoaderHelper.class) {
                if (null == instance) {
                    instance = new ImageLoaderHelper(context);
                }
            }
        }
        return instance;
    }

    public DisplayImageOptions getDisplayOptionsPic() {
        return new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_picture_loading)
            .showImageOnLoading(R.drawable.ic_picture_loading)
            .showImageOnFail(R.drawable.ic_picture_loadfailed)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    }

    public DisplayImageOptions getDisplayOptions() {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(R.color.default_image_background)
            .showImageForEmptyUri(R.color.default_image_background)
            .showImageOnFail(R.color.default_image_background)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    }

    public DisplayImageOptions getDisplayOptions(Drawable drawable) {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(drawable)
            .showImageForEmptyUri(drawable)
            .showImageOnFail(drawable)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    }

    public DisplayImageOptions getDisplayOptions(int round) {
        return new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(AbDensityUtils.dip2px(mContext, round)))
            .build();
    }

    public DisplayImageOptions getDisplayOptions(int round, Drawable drawable) {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(drawable)
            .showImageForEmptyUri(drawable)
            .showImageOnFail(drawable)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(AbDensityUtils.dip2px(mContext, round)))
            .build();
    }

    public DisplayImageOptions getDisplayOptions(boolean isCacheOnDisk) {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(R.color.default_image_background)
            .showImageForEmptyUri(R.color.default_image_background)
            .showImageOnFail(R.color.default_image_background)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(isCacheOnDisk)
            .considerExifParams(true)
            .build();
    }

    public ImageLoaderConfiguration getImageLoaderConfiguration() {
        return getImageLoaderConfiguration(null, null);
    }

    public ImageLoaderConfiguration getImageLoaderConfiguration(String filePath) {
        return getImageLoaderConfiguration(filePath, null);
    }

    public ImageLoaderConfiguration getImageLoaderConfiguration(DisplayImageOptions displayImageOptions) {
        return getImageLoaderConfiguration(null, displayImageOptions);
    }

    public ImageLoaderConfiguration getImageLoaderConfiguration(String filePath, DisplayImageOptions displayImageOptions) {

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(mContext);

        // Disk
        File cacheDir;
        if (!AbCommonUtils.isEmpty(filePath)) {
            cacheDir = StorageUtils.getOwnCacheDirectory(mContext, filePath);
        } else {
            cacheDir = StorageUtils.getCacheDirectory(mContext);
        }
        builder.diskCacheSize(512 * 1024 * 1024);
        builder.diskCacheExtraOptions(720, 1280, null);
        builder.diskCache(new UnlimitedDiskCache(cacheDir));
        builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());

        // Memory
        builder.denyCacheImageMultipleSizesInMemory();
        builder.memoryCacheSizePercentage(14);
        builder.memoryCacheSize(2 * 1024 * 1024);
        builder.memoryCacheExtraOptions(720, 1280);
        builder.memoryCache(new LruMemoryCache(2 * 1024 * 1024));

        // Thread
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY - 2);

        // DisplayImageOptions
        if (displayImageOptions == null) {
            builder.defaultDisplayImageOptions(getDisplayOptions());
        } else {
            builder.defaultDisplayImageOptions(displayImageOptions);
        }

        return builder.build();
    }
}

