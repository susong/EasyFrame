package com.dream.easy.presenter.impl;

import android.content.Context;

import com.dream.easy.presenter.IMainActivityPresenter;
import com.dream.easy.view.IMainView;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:43
 * Description: EasyFrame
 */
public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private Context mContext;
    private IMainView mMainView;

    public MainActivityPresenterImpl(Context context, IMainView mainView) {
        if (mainView == null) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        mContext = context;
        mMainView = mainView;
    }

    @Override
    public void init() {
    }
}
