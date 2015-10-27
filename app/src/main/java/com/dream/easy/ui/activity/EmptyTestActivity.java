package com.dream.easy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dream.easy.R;
import com.dream.library.base.BaseAppCompatActivity;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.utils.AbToastUtils;
import com.dream.library.utils.netstatus.AbNetUtils;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/27 上午11:12
 * Description: EasyFrame
 */
public class EmptyTestActivity extends BaseAppCompatActivity {

    @Bind(R.id.content) LinearLayout mContent;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_empty_test;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    public void showLoading(View view) {
        showLoading("");
    }

    public void showEmpty(View view) {
        showEmpty("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtils.show("showEmpty view click");
            }
        });
    }

    public void showError(View view) {
        showError("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtils.show("showError view click");
            }
        });
    }

    public void showNetworkError(View view) {
        showNetworkError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbToastUtils.show("showNetworkError view click");
            }
        });
    }

    public void clear(View view) {
        clearEmpty();
    }

    public void showDialog(View view) {
        showProgressDialog();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return mContent;
    }

    @Override
    protected void onNetworkConnected(AbNetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
}
