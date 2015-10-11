package com.dream.easy.model.impl;

import android.content.Context;

import com.dream.easy.R;
import com.dream.easy.bean.BaseEntity;
import com.dream.easy.model.IImageContainerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午11:02
 * Description: EasyFrame
 */
public class ImageContainerModelImpl implements IImageContainerModel {

    @Override
    public List<BaseEntity> getCommonCategoryList(Context context) {
        List<BaseEntity> resultData = new ArrayList<>();
        String[] imagesCategoryArrayId = context.getResources().getStringArray(R.array.images_category_list_id);
        String[] imagesCategoryArrayName = context.getResources().getStringArray(R.array.images_category_list_name);
        for (int i = 0; i < 6; i++) {
            resultData.add(new BaseEntity(imagesCategoryArrayId[i], imagesCategoryArrayName[i]));
        }
        return resultData;
    }
}
