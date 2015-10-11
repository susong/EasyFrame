package com.dream.easy.dagger.modules;

import com.dream.easy.dagger.scope.ImageContainerFragmentScope;
import com.dream.easy.model.IImageContainerModel;
import com.dream.easy.model.impl.ImageContainerModelImpl;
import com.dream.easy.presenter.IImageContainerFragmentPresenter;
import com.dream.easy.presenter.impl.ImageContainerFragmentPresenterImpl;
import com.dream.easy.ui.fragment.ImageContainerFragment;
import com.dream.easy.view.IImagesContainerFragmentView;

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

    private ImageContainerFragment mImageContainerFragment;

    public ImageContainerFragmentModule(ImageContainerFragment imageContainerFragment) {
        mImageContainerFragment = imageContainerFragment;
    }

    @Provides
    @ImageContainerFragmentScope
    ImageContainerFragment provideImagesContainerFragment() {
        return mImageContainerFragment;
    }

    @Provides
    @ImageContainerFragmentScope
    IImagesContainerFragmentView provideICommonContainerView(ImageContainerFragment imageContainerFragment) {
        return imageContainerFragment;
    }

    @Provides
    @ImageContainerFragmentScope
    IImageContainerModel provideImagesContainerModel() {
        return new ImageContainerModelImpl();
    }

    @Provides
    @ImageContainerFragmentScope
    IImageContainerFragmentPresenter provideImagesContainerPresenter(
        ImageContainerFragment imageContainerFragment,
        IImagesContainerFragmentView commonContainerView,
        IImageContainerModel commonContainerModel) {
        return new ImageContainerFragmentPresenterImpl(
            imageContainerFragment,
            commonContainerView,
            commonContainerModel);
    }
}
