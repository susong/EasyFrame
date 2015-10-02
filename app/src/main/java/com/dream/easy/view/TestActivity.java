package com.dream.easy.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dream.easy.R;
import com.dream.library.base.BaseAppCompatActivity;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.netstatus.NetUtils;
import com.orhanobut.logger.AbLog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午10:21
 * Description: EasyFrame
 */
public class TestActivity extends BaseAppCompatActivity {

  @Bind(R.id.btn_show_loading) Button mBtnShowLoading;
  @Bind(R.id.btn_show_empty) Button mBtnShowEmpty;
  @Bind(R.id.btn_show_error) Button mBtnShowError;
  @Bind(R.id.btn_network_error) Button mBtnNetworkError;
  @Bind(R.id.content) LinearLayout mContent;

  @OnClick({
    R.id.btn_show_loading,
    R.id.btn_show_empty,
    R.id.btn_show_error,
    R.id.btn_network_error,
    R.id.btn_clean})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_show_loading:
        toggleShowLoading(true, null);
        break;
      case R.id.btn_show_empty:
        toggleShowEmpty(true, "empty", null);
        break;
      case R.id.btn_show_error:
        toggleShowError(true, "error", null);
        break;
      case R.id.btn_network_error:
        toggleNetworkError(true, null);
        break;
      case R.id.btn_clean:
        toggleClear();
        break;
    }
  }

  /**
   * get bundle data
   *
   * @param extras extras
   */
  @Override
  protected void getBundleExtras(Bundle extras) {

  }

  /**
   * bind layout resource file
   *
   * @return id of layout resource
   */
  @Override
  protected int getContentViewLayoutID() {
    return R.layout.activity_test;
  }

  /**
   * when event coming
   *
   * @param eventCenter eventCenter
   */
  @Override
  protected void onEventComing(EventCenter eventCenter) {

  }

  /**
   * get loading target view
   */
  @Override
  protected View getLoadingTargetView() {
    return mContent;
  }

  /**
   * init all views and add events
   */
  @Override
  protected void initViewsAndEvents() {

  }

  /**
   * network connected
   *
   * @param type type
   */
  @Override
  protected void onNetworkConnected(NetUtils.NetType type) {
    AbLog.i("onNetworkConnected type : " + type);
  }

  /**
   * network disconnected
   */
  @Override
  protected void onNetworkDisConnected() {
    AbLog.i("onNetworkDisConnected");
  }

  /**
   * is applyStatusBarTranslucency
   *
   * @return is applyStatusBarTranslucency
   */
  @Override
  protected boolean isApplyStatusBarTranslucency() {
    return false;
  }

  /**
   * is bind eventBus
   *
   * @return is bind eventBus
   */
  @Override
  protected boolean isBindEventBus() {
    return false;
  }

  /**
   * is toggle overridePendingTransition
   *
   * @return is toggle overridePendingTransition
   */
  @Override
  protected boolean toggleOverridePendingTransition() {
    return false;
  }

  /**
   * get the overridePendingTransition mode
   */
  @Override
  protected TransitionMode getOverridePendingTransitionMode() {
    return null;
  }
}
