package com.dream.easy.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dream.easy.App;
import com.dream.easy.R;
import com.dream.easy.dagger.components.ApplicationComponent;
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
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
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

    protected ApplicationComponent getApplicationComponent() {
        return ((AbDaggerApplication) getApplication()).getApplicationComponent();
    }

    protected App getApp() {
        return (App) getApplication();
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    protected abstract boolean isApplyKitKatTranslucency();
}
