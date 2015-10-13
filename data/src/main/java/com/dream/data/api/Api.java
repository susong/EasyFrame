package com.dream.data.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.data.entity.ResponseImageListEntity;
import com.dream.data.listeners.BaseMultiLoadedListener;
import com.dream.library.volley.CustomRequest;
import com.dream.library.volley.VolleyHelper;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/29 下午3:44
 * Description: EasyFrame
 */
public class Api {

    private UriHelper mUriHelper;

    public Api(UriHelper uriHelper) {
        mUriHelper = uriHelper;
    }

    public void getImagesList(String requestTag, final int eventTag, String category, int pageNum,
                              BaseMultiLoadedListener<ResponseImageListEntity> listener) {
        CustomRequest customRequest = new CustomRequest.RequestBuilder()
            .url(mUriHelper.getImagesListUrl(category, pageNum))
            .clazz(ResponseImageListEntity.class)
            .successListener(new Response.Listener<ResponseImageListEntity>() {
                @Override
                public void onResponse(ResponseImageListEntity response) {
                    listener.onSuccess(eventTag, response);
                }
            })
            .errorListener(new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onError(error.getMessage());
                }
            })
            .build();

        customRequest.setShouldCache(true);
        customRequest.setTag(requestTag);

        VolleyHelper.getInstance().addRequest(customRequest);
    }
}
