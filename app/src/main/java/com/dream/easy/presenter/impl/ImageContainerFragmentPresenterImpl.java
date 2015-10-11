package com.dream.easy.presenter.impl;

import com.dream.easy.model.IImageContainerModel;
import com.dream.easy.presenter.IImageContainerFragmentPresenter;
import com.dream.easy.ui.fragment.ImageContainerFragment;
import com.dream.easy.view.IImagesContainerFragmentView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午10:13
 * Description: EasyFrame
 */
public class ImageContainerFragmentPresenterImpl implements IImageContainerFragmentPresenter {

    private ImageContainerFragment mImageContainerFragment;
    private IImagesContainerFragmentView mCommonContainerView;
    private IImageContainerModel mCommonContainerModel;

    public ImageContainerFragmentPresenterImpl(ImageContainerFragment imageContainerFragment, IImagesContainerFragmentView commonContainerView, IImageContainerModel commonContainerModel) {
        this.mImageContainerFragment = imageContainerFragment;
        this.mCommonContainerView = commonContainerView;
        this.mCommonContainerModel = commonContainerModel;
    }

    @Override
    public void init() {
        mCommonContainerView.init(mCommonContainerModel.getCommonCategoryList(mImageContainerFragment.getContext()));
    }
}
