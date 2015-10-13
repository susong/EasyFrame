package com.dream.easy.presenter.impl;

import com.dream.data.entity.ResponseImageListEntity;
import com.dream.data.listeners.BaseMultiLoadedListener;
import com.dream.easy.Constants;
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
public class ImageListFragmentPresenterImpl implements IImageListFragmentPresenter, BaseMultiLoadedListener<ResponseImageListEntity> {

    private ImageListFragment mImageListFragment;
    private IImageListFragmentView mImageListFragmentView;
    private IImageListFragmentModel mImageListFragmentModel;

    public ImageListFragmentPresenterImpl(ImageListFragment imageListFragment,
                                          IImageListFragmentView imageListFragmentView,
                                          IImageListFragmentModel imageListFragmentModel) {
        mImageListFragment = imageListFragment;
        mImageListFragmentView = imageListFragmentView;
        mImageListFragmentModel = imageListFragmentModel;
    }

    @Override
    public void loadImageList(String requestTag, int event_tag, String keywords, int page, boolean isShowRefresh) {
        mImageListFragmentView.hideLoading();
        if (isShowRefresh) {
            mImageListFragmentView.showLoading("Loading...");
        }
        mImageListFragmentModel.loadImageList(requestTag, event_tag, keywords, page, this);
    }

    @Override
    public void onSuccess(int event_tag, ResponseImageListEntity data) {
        mImageListFragmentView.hideLoading();
        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            mImageListFragmentView.refreshListData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            mImageListFragmentView.addMoreListData(data);
        }
    }

    @Override
    public void onError(String msg) {
        mImageListFragmentView.hideLoading();
        mImageListFragmentView.showError(msg);
    }

    @Override
    public void onException(String msg) {
        mImageListFragmentView.hideLoading();
        mImageListFragmentView.showError(msg);
    }
}
