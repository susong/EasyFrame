package com.dream.data.api;

import com.android.volley.Response;
import com.dream.data.entity.ImageEntity;
import com.dream.data.entity.ResponseImageListEntity;
import com.dream.library.volley.CustomRequest;
import com.dream.library.volley.VolleyHelper;
import com.orhanobut.logger.AbLog;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/29 下午3:44
 * Description: EasyFrame
 */
@Singleton
public class Api {

    @Inject
    public Api() {
    }

    public void getImagesList(String category, int pageNum) {
        VolleyHelper.getInstance().addRequest(new CustomRequest.RequestBuilder()
            .url(UriHelper.getInstance().getImagesListUrl(category, pageNum))
            .clazz(ResponseImageListEntity.class)
            .successListener(new Response.Listener<ResponseImageListEntity>() {
                @Override
                public void onResponse(ResponseImageListEntity response) {
                    List<ImageEntity> imgs = response.getImgs();
                    for (ImageEntity entity : imgs) {
                        AbLog.i(entity.getImageUrl());
                    }
                }
            })
            .build());
    }
}
