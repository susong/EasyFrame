package com.dream.easy.model;

import com.dream.data.entity.ResponseImageListEntity;
import com.dream.data.listeners.BaseMultiLoadedListener;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:15
 * Description: EasyFrame
 */
public interface IImageListFragmentModel {

    void loadImageList(String requestTag, int event_tag, String keywords, int page, BaseMultiLoadedListener<ResponseImageListEntity> listener);
}
