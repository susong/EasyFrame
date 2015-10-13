package com.dream.easy.view;

import com.dream.data.entity.ResponseImageListEntity;
import com.dream.easy.view.base.IBaseView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:17
 * Description: EasyFrame
 */
public interface IImageListFragmentView extends IBaseView {
    void refreshListData(ResponseImageListEntity responseImageListEntity);

    void addMoreListData(ResponseImageListEntity responseImageListEntity);

}
