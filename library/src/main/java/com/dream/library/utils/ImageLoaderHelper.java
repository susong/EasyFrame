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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/15 上午12:37
 * Description: ImageLoaderHelper
 * <p>
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <p>
 * Acceptable URIs examples
 * "http://site.com/image.png" // from Web
 * "file:///mnt/sdcard/image.png" // from SD card
 * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
 * "content://media/external/images/media/13" // from content provider
 * "content://media/external/video/media/13" // from content provider (video thumbnail)
 * "assets://image.png" // from assets
 * "drawable://" + R.drawable.img // from drawables (non-9patch images)
 * NOTE: Use drawable:// only if you really need it! Always consider the native way to load drawables - ImageView.setImageResource(...) instead of using of ImageLoader.
 * <p>
 * 注意事项
 * 1.上述提到的2个权限必须加入，否则会出错
 * 2.ImageLoaderConfiguration必须配置并且全局化的初始化这个配置ImageLoader.getInstance().init(config);  否则也会出现错误提示
 * 3.ImageLoader是根据ImageView的height，width确定图片的宽高。
 * 4.如果经常出现OOM（别人那边看到的，觉得很有提的必要）
 * ①减少配置之中线程池的大小.threadPoolSize(3).推荐1-5；
 * ②使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
 * ③使用.imageScaleType(ImageScaleType.IN_SAMPLE_INT)或者try.imageScaleType(ImageScaleType.EXACTLY)；
 * ④避免使用RoundedBitmapDisplayer,他会创建新的ARGB_8888格式的Bitmap对象；
 * ⑤使用.memoryCache(new WeakMemoryCache());
 * ⑥不要使用.cacheInMemory();
 */
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
            .showImageForEmptyUri(R.drawable.ic_picture_loading)//设置图片Uri为空或是错误的时候显示的图片
            .showImageOnLoading(R.drawable.ic_picture_loading)//设置图片在下载期间显示的图片
            .showImageOnFail(R.drawable.ic_picture_loadfailed)//设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
            .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
            .considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
//            .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//            .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//            .preProcessor(BitmapProcessor preProcessor)  //设置图片加入缓存前，对bitmap进行设置
//            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//            .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//            .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
            .build();

        /**

         以上配置中的：

         1）.imageScaleType(ImageScaleType imageScaleType)  是设置 图片的缩放方式

         缩放类型mageScaleType:

         EXACTLY :图像将完全按比例缩小的目标大小

         EXACTLY_STRETCHED:图片会缩放到目标大小完全

         IN_SAMPLE_INT:图像将被二次采样的整数倍

         IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小

         NONE:图片不会调整

         2）.displayer(BitmapDisplayer displayer)   是设置 图片的显示方式

         显示方式displayer：

         RoundedBitmapDisplayer（int roundPixels）设置圆角图片

         FakeBitmapDisplayer（）这个类什么都没做

         FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间

         SimpleBitmapDisplayer()正常显示一张图片　　
         */
    }

    public DisplayImageOptions getDisplayOptions() {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(R.color.default_image_background)
            .showImageForEmptyUri(R.color.default_image_background)
            .showImageOnFail(R.color.default_image_background)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .considerExifParams(true)
            .build();
    }

    public DisplayImageOptions getDisplayOptions(Drawable drawable) {
        return new DisplayImageOptions.Builder()
            .showImageOnLoading(drawable)
            .showImageForEmptyUri(drawable)
            .showImageOnFail(drawable)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .considerExifParams(true)
            .build();
    }

    public DisplayImageOptions getDisplayOptions(int round) {
        return new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
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
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
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
            .cacheOnDisk(isCacheOnDisk)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
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
            cacheDir = StorageUtils.getOwnCacheDirectory(mContext, filePath);//自定义缓存目录
        } else {
            cacheDir = StorageUtils.getCacheDirectory(mContext);//默认缓存目录，查看源码了解储存在哪
        }
        builder.diskCache(new UnlimitedDiskCache(cacheDir));//自定义缓存路径
//        builder.diskCacheSize(512 * 1024 * 1024);//缓存的文件大小
//        builder.diskCacheFileCount(1000);//缓存的文件数量
//        builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());//将保存的时候的URI名称用MD5加密
//        builder.diskCacheExtraOptions(720, 1280, null);// 设置缓存的详细信息，最好不要设置这个

        // Memory
//        builder.memoryCache(new LruMemoryCache(2 * 1024 * 1024));//你可以通过自己的内存缓存实现
//        builder.memoryCacheSize(2 * 1024 * 1024);//内存缓存大小
        builder.memoryCacheSizePercentage(15);//内存缓存百分比大小
        builder.denyCacheImageMultipleSizesInMemory();
        builder.memoryCacheExtraOptions(720, 1280);//保存的每个缓存文件的最大长宽

        // Thread
        builder.threadPoolSize(ImageLoaderConfiguration.Builder.DEFAULT_THREAD_POOL_SIZE);//线程池内加载的数量
        builder.threadPriority(ImageLoaderConfiguration.Builder.DEFAULT_THREAD_PRIORITY);//线程执行的优先权

        // DisplayImageOptions
        if (displayImageOptions == null) {
            builder.defaultDisplayImageOptions(getDisplayOptions());
        } else {
            builder.defaultDisplayImageOptions(displayImageOptions);
        }

        return builder.build();
    }
}

