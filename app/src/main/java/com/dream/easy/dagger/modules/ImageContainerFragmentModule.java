package com.dream.easy.dagger.modules;

import com.dream.easy.model.ICommonContainerModel;
import com.dream.easy.model.impl.ImagesContainerModelImpl;
import com.dream.easy.presenter.IPresenter;
import com.dream.easy.presenter.impl.ImagesContainerPresenterImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午11:26
 * Description: EasyFrame
 */
@Module
public class ImageContainerFragmentModule {
    @Provides
    @Singleton
    ICommonContainerModel provideImagesContainerModel(ImagesContainerModelImpl imagesContainerModel) {
        return imagesContainerModel;
    }

    @Provides
    @Singleton
    @Named("ImagesContainer")
    IPresenter provideImagesContainerPresenter(ImagesContainerPresenterImpl imagesContainerPresenter) {
        return imagesContainerPresenter;
    }
}
