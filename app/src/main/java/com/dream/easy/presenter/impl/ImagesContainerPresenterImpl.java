package com.dream.easy.presenter.impl;

import android.content.Context;

import com.dream.easy.model.ICommonContainerModel;
import com.dream.easy.presenter.IPresenter;
import com.dream.easy.view.ICommonContainerView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午10:13
 * Description: EasyFrame
 */
@Singleton
public class ImagesContainerPresenterImpl implements IPresenter {

    private Context mContext;
    private ICommonContainerView mCommonContainerView;
    private ICommonContainerModel mCommonContainerModel;

    public void setCommonContainerView(ICommonContainerView commonContainerView) {
        mCommonContainerView = commonContainerView;
    }

    @Inject
    public ImagesContainerPresenterImpl(@Named("MainActivity") Context context, ICommonContainerModel commonContainerModel) {
        this.mContext = context;
        this.mCommonContainerModel = commonContainerModel;
    }

    @Override
    public void init() {
        mCommonContainerView.init(mCommonContainerModel.getCommonCategoryList(mContext));
    }
}
