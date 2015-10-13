package com.dream.easy.model.impl;

import com.dream.data.api.Api;
import com.dream.data.entity.ResponseImageListEntity;
import com.dream.data.listeners.BaseMultiLoadedListener;
import com.dream.easy.model.IImageListFragmentModel;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:16
 * Description: EasyFrame
 */
public class ImageListFragmentModelImpl implements IImageListFragmentModel {

    private Api mApi;

    public ImageListFragmentModelImpl(Api api) {
        mApi = api;
    }

    @Override
    public void loadImageList(String requestTag, int event_tag, String keywords, int page, BaseMultiLoadedListener<ResponseImageListEntity> listener) {
        mApi.getImagesList(requestTag, event_tag, keywords, page, listener);
    }
}
