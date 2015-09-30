package com.dream.data.entity.mapper;

import com.dream.data.entity.ImageEntity;
import com.dream.domain.ImageDomain;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:40
 * Description: EasyFrame
 */
@Singleton
public class ImageEntityMapper {
    @Inject
    public ImageEntityMapper() {

    }

    public ImageDomain transform(ImageEntity imageEntity) {
        ImageDomain imageDomain = null;
        if (imageEntity != null) {
            imageDomain = new ImageDomain(imageEntity.getId());
            imageDomain.setImageUrl(imageEntity.getImageUrl());
        }
        return imageDomain;
    }
}
