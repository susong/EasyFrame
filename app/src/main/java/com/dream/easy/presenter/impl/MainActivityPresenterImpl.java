package com.dream.easy.presenter.impl;

import com.dream.easy.model.IMainActivityModel;
import com.dream.easy.presenter.IMainActivityPresenter;
import com.dream.easy.ui.activity.MainActivity;
import com.dream.easy.view.IMainActivityView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:43
 * Description: EasyFrame
 */
public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private MainActivity mMainActivity;
    private IMainActivityView mMainActivityView;
    private IMainActivityModel mMainActivityModel;

    public MainActivityPresenterImpl(MainActivity mainActivity,
                                     IMainActivityView mainActivityView,
                                     IMainActivityModel mainActivityModel) {
        mMainActivity = mainActivity;
        mMainActivityView = mainActivityView;
        mMainActivityModel = mainActivityModel;
    }

    @Override
    public void init() {
        mMainActivityView.init(mMainActivityModel.getPagerFragments(), mMainActivityModel.getNavigationListData(mMainActivity));
    }
}
