package com.dream.data.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/29 下午3:37
 * Description: EasyFrame
 */
public class UriHelper {

    /**
     * 20 datas per page
     */
    public static final int PAGE_LIMIT = 20;

    public String getImagesListUrl(String category, int pageNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(ApiConstants.Urls.BAIDU_IMAGES_URLS);
        sb.append("?col=");
        try {
            sb.append(URLEncoder.encode(category, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&tag=");
        try {
            sb.append(URLEncoder.encode("全部", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&pn=");
        sb.append(pageNum * PAGE_LIMIT);
        sb.append("&rn=");
        sb.append(PAGE_LIMIT);
        sb.append("&from=1");
        return sb.toString();
    }
}
