package com.dream.easy.model;

import android.content.Context;

import com.dream.easy.bean.BaseEntity;

import java.util.List;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午10:20
 * Description: EasyFrame
 */
public interface IImagesContainerModel {
    List<BaseEntity> getCommonCategoryList(Context context);
}
