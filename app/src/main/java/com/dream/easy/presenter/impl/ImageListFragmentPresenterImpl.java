package com.dream.easy.presenter.impl;

import com.dream.easy.model.IImageListFragmentModel;
import com.dream.easy.presenter.IImageListFragmentPresenter;
import com.dream.easy.ui.fragment.ImageListFragment;
import com.dream.easy.view.IImageListFragmentView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:19
 * Description: EasyFrame
 */
public class ImageListFragmentPresenterImpl implements IImageListFragmentPresenter {

    private ImageListFragment mImageListFragment;
    private IImageListFragmentView mImageListFragmentView;
    private IImageListFragmentModel mImageListFragmentModel;

    public ImageListFragmentPresenterImpl(ImageListFragment imageListFragment, IImageListFragmentView imageListFragmentView, IImageListFragmentModel imageListFragmentModel) {
        mImageListFragment = imageListFragment;
        mImageListFragmentView = imageListFragmentView;
        mImageListFragmentModel = imageListFragmentModel;
    }

    public void init() {

    }
}
