package com.dream.easy.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dream.easy.App;
import com.dream.easy.R;
import com.dream.easy.view.base.IBaseView;
import com.dream.library.base.BaseAppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:52
 * Description: EasyFrame
 */
public abstract class BaseActivity extends BaseAppCompatActivity implements IBaseView {

    @Bind(R.id.common_toolbar) protected Toolbar mCommonToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mCommonToolbar) {
            setSupportActionBar(mCommonToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected App getApp() {
        return (App) getApplication();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void showException(String msg) {
        showError(msg);
    }

    @Override
    public void showNetError() {
        showNetworkError();
    }

    @Override
    public void showLoading(String msg) {
        super.showLoading(msg);
    }

    @Override
    public void hideLoading() {
        showLoading();
    }
}
