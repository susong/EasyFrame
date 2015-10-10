package com.dream.easy.presenter.impl;

import com.dream.easy.model.ICommonContainerModel;
import com.dream.easy.presenter.IImagesContainerPresenter;
import com.dream.easy.ui.fragment.ImagesContainerFragment;
import com.dream.easy.view.ICommonContainerView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午10:13
 * Description: EasyFrame
 */
public class ImagesContainerPresenterImpl implements IImagesContainerPresenter {

    private ImagesContainerFragment mImagesContainerFragment;
    private ICommonContainerView mCommonContainerView;
    private ICommonContainerModel mCommonContainerModel;

    public ImagesContainerPresenterImpl(ImagesContainerFragment imagesContainerFragment, ICommonContainerView commonContainerView, ICommonContainerModel commonContainerModel) {
        this.mImagesContainerFragment = imagesContainerFragment;
        this.mCommonContainerView = commonContainerView;
        this.mCommonContainerModel = commonContainerModel;
    }

    @Override
    public void init() {
        mCommonContainerView.init(mCommonContainerModel.getCommonCategoryList(mImagesContainerFragment.getContext()));
    }
}
