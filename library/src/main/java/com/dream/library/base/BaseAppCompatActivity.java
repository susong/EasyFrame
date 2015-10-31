package com.dream.library.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dream.library.R;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.interf.IEmptyControl;
import com.dream.library.interf.IProgressDialogControl;
import com.dream.library.interf.IToastControl;
import com.dream.library.utils.AbAppManager;
import com.dream.library.utils.AbSmartBarUtils;
import com.dream.library.utils.AbStringUtils;
import com.dream.library.utils.AbSystemBarTintManager;
import com.dream.library.utils.AbToastUtils;
import com.dream.library.utils.netstatus.AbNetUtils;
import com.dream.library.utils.netstatus.NetChangeObserver;
import com.dream.library.utils.netstatus.NetStateReceiver;
import com.dream.library.widgets.empty.EmptyViewHelperController;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午5:38
 * Description: EasyFrame
 */
@SuppressWarnings("unused")
public abstract class BaseAppCompatActivity extends AppCompatActivity implements IEmptyControl, IProgressDialogControl, IToastControl {

    /**
     * Log tag
     */
    protected static String TAG_LOG = null;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * context
     */
    protected Context mContext = null;

    /**
     * network status
     */
    protected NetChangeObserver mNetChangeObserver = null;

    /**
     * loading view controller
     */
    private EmptyViewHelperController mEmptyViewHelperController = null;

    /**
     * 界面是否可见
     */
    protected boolean mIsVisible;
    /**
     * 进度条
     */
    private ProgressDialog mProgressDialog;

    /**
     * overridePendingTransition mode
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE, DEFAULT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                default:
                    overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
                    break;
            }
        }
        super.onCreate(savedInstanceState);
        // base setup
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        if (isBindEventBus()) {
            EventBus.getDefault().register(this);
        }
        AbSmartBarUtils.hide(getWindow().getDecorView());

        setTranslucentStatus(isApplyStatusBarTranslucency());
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }

        mContext = this;
        TAG_LOG = this.getClass().getSimpleName();
        AbAppManager.getAbAppManager().addActivity(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        if (getContentViewLayoutId() != 0) {
            setContentView(getContentViewLayoutId());
        }

        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(AbNetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };

        NetStateReceiver.registerNetChangeObserver(mNetChangeObserver);

        mIsVisible = true;

        initViewsAndEvents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mEmptyViewHelperController = new EmptyViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    public void finish() {
        super.finish();
        AbAppManager.getAbAppManager().finishActivity(this);
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                default:
                    overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mIsVisible = false;
        NetStateReceiver.unRegisterNetChangeObserver(mNetChangeObserver);
        if (isBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutId();

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    /**
     * get bundle data
     *
     * @param extras extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * when event coming
     *
     * @param eventCenter eventCenter
     */
    protected abstract void onEventComing(EventCenter eventCenter);

    /**
     * get loading target view
     */
    protected abstract View getLoadingTargetView();

    /**
     * network connected
     */
    protected abstract void onNetworkConnected(AbNetUtils.NetType type);

    /**
     * network disconnected
     */
    protected abstract void onNetworkDisConnected();

    /**
     * is applyStatusBarTranslucency
     *
     * @return is applyStatusBarTranslucency
     */
    protected abstract boolean isApplyStatusBarTranslucency();

    protected abstract boolean isApplyKitKatTranslucency();

    /**
     * is bind eventBus
     *
     * @return is bind eventBus
     */
    protected abstract boolean isBindEventBus();

    /**
     * is toggle overridePendingTransition
     *
     * @return is toggle overridePendingTransition
     */
    protected abstract boolean toggleOverridePendingTransition();

    /**
     * get the overridePendingTransition mode
     */
    protected abstract TransitionMode getOverridePendingTransitionMode();

    /**
     * startActivity
     *
     * @param clazz class
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  class
     * @param bundle bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz class
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz  class
     * @param bundle bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       class
     * @param requestCode requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       clazz
     * @param requestCode requestCode
     * @param bundle      bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * show toast
     *
     * @param msg msg
     */
    public void showToast(String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !AbStringUtils.isEmpty(msg)) {
            if (getLoadingTargetView() != null) {
                Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT).show();
            } else {
                AbToastUtils.show(msg);
            }
        }
    }

    /**
     * show loading
     */
    public void showLoading() {
        showLoading(null);
    }

    /**
     * show loading
     */
    public void showLoading(String msg) {
        if (null == mEmptyViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mEmptyViewHelperController.showLoading(msg);
    }

    /**
     * show empty
     */
    public void showEmpty() {
        showEmpty(null, null);
    }

    /**
     * show empty
     */
    public void showEmpty(String msg) {
        showEmpty(msg, null);
    }

    /**
     * show empty
     */
    public void showEmpty(View.OnClickListener onClickListener) {
        showEmpty(null, onClickListener);
    }

    /**
     * show empty
     */
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
        if (null == mEmptyViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mEmptyViewHelperController.showEmpty(msg, onClickListener);
    }

    /**
     * show error
     */
    public void showError() {
        showError(null, null);
    }

    /**
     * show error
     */
    public void showError(String msg) {
        showError(msg, null);
    }

    /**
     * show error
     */
    public void showError(View.OnClickListener onClickListener) {
        showError(null, onClickListener);
    }

    /**
     * show error
     */
    public void showError(String msg, View.OnClickListener onClickListener) {
        if (null == mEmptyViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mEmptyViewHelperController.showError(msg, onClickListener);
    }

    /**
     * show error
     */
    public void showNetworkError() {
        showNetworkError(null);
    }

    /**
     * show network error
     */
    public void showNetworkError(View.OnClickListener onClickListener) {
        if (null == mEmptyViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mEmptyViewHelperController.showNetworkError(onClickListener);
    }

    public void clearEmpty() {
        if (null == mEmptyViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        mEmptyViewHelperController.restore();
    }

    public ProgressDialog showProgressDialog() {
        return showProgressDialog(getString(R.string.common_loading_msg), true);
    }

    public ProgressDialog showNonCancelableProgressDialog() {
        return showProgressDialog(getString(R.string.common_loading_msg), false);
    }

    public ProgressDialog showProgressDialog(int resId) {
        return showProgressDialog(getString(resId), true);
    }

    public ProgressDialog showNonCancelableProgressDialog(int resId) {
        return showProgressDialog(getString(resId), false);
    }

    public ProgressDialog showProgressDialog(String text) {
        return showProgressDialog(text, true);
    }

    public ProgressDialog showNonCancelableProgressDialog(String text) {
        return showProgressDialog(text, false);
    }

    public ProgressDialog showProgressDialog(String text, boolean isCancelable) {
        if (mIsVisible) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
            }
            mProgressDialog.setMessage(text);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(isCancelable);
            mProgressDialog.show();
            return mProgressDialog;
        }
        return null;
    }

    public void hideProgressDialog() {
        if (mIsVisible && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComing(eventCenter);
        }
    }

    /**
     * use SytemBarTintManager
     *
     * @param tintDrawable tintDrawable
     */
    protected void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AbSystemBarTintManager mTintManager = new AbSystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }

    }

    /**
     * set status bar translucency
     *
     * @param on on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
}
