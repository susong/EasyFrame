package com.dream.data.api;

import android.os.Environment;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/29 下午3:34
 * Description: EasyFrame
 */
public interface ApiConstants {

    interface Urls {
        String BAIDU_IMAGES_URLS = "http://image.baidu.com/data/imgs";
        String YOUKU_VIDEOS_URLS = "https://openapi.youku.com/v2/searches/video/by_keyword.json";
        String YOUKU_USER_URLS = "https://openapi.youku.com/v2/users/show.json";
        String DOUBAN_PLAY_LIST_URLS = "http://www.douban.com/j/app/radio/people";
    }

    interface Paths {
        String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        String IMAGE_LOADER_CACHE_PATH = "/EasyFrame/Images/";
    }

    interface Integers {
        int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }
}
