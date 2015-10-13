package com.dream.easy.dagger.modules;

import com.dream.data.api.Api;
import com.dream.easy.dagger.scope.ImageListFragmentScope;
import com.dream.easy.model.IImageListFragmentModel;
import com.dream.easy.model.impl.ImageListFragmentModelImpl;
import com.dream.easy.presenter.IImageListFragmentPresenter;
import com.dream.easy.presenter.impl.ImageListFragmentPresenterImpl;
import com.dream.easy.ui.fragment.ImageListFragment;
import com.dream.easy.view.IImageListFragmentView;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:22
 * Description: EasyFrame
 */
@Module
public class ImageListFragmentModule {

    private ImageListFragment mImageListFragment;

    public ImageListFragmentModule(ImageListFragment imageListFragment) {
        mImageListFragment = imageListFragment;
    }

    @Provides
    @ImageListFragmentScope
    ImageListFragment provideImageListFragment() {
        return mImageListFragment;
    }

    @Provides
    @ImageListFragmentScope
    IImageListFragmentView provideIImageListFragmentView() {
        return mImageListFragment;
    }

    @Provides
    @ImageListFragmentScope
    IImageListFragmentModel provideIImageListFragmentModel(Api api) {
        return new ImageListFragmentModelImpl(api);
    }

    @Provides
    @ImageListFragmentScope
    IImageListFragmentPresenter provideIImageListFragmentPresenter(
        ImageListFragment imageListFragment,
        IImageListFragmentView iImageListFragmentView,
        IImageListFragmentModel iImageListFragmentModel) {
        return new ImageListFragmentPresenterImpl(
            imageListFragment,
            iImageListFragmentView,
            iImageListFragmentModel);
    }
}
