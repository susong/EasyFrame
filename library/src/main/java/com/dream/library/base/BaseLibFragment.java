package com.dream.library.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.library.R;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.interf.IEmptyControl;
import com.dream.library.interf.IProgressDialogControl;
import com.dream.library.interf.IToastControl;
import com.dream.library.utils.AbStringUtils;
import com.dream.library.widgets.empty.EmptyViewHelperController;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午5:38
 * Description: EasyFrame
 */
@SuppressWarnings("unused")
public abstract class BaseLibFragment extends Fragment implements IEmptyControl, IProgressDialogControl, IToastControl {

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

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    private EmptyViewHelperController mEmptyViewHelperController = null;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
        if (isBindEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutId() != 0) {
            return inflater.inflate(getContentViewLayoutId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (null != getLoadingTargetView()) {
            mEmptyViewHelperController = new EmptyViewHelperController(getLoadingTargetView());
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        initViewsAndEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
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
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract void onFirstUserVisible();

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract void onUserVisible();

    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract void onUserInvisible();

    /**
     * get loading target view
     */
    protected abstract View getLoadingTargetView();

    /**
     * when event coming
     *
     * @param eventCenter EventCenter
     */
    protected abstract void onEventComing(EventCenter eventCenter);

    /**
     * is bind eventBus
     *
     * @return boolean
     */
    protected abstract boolean isBindEventBus();

    /**
     * get the support fragment manager
     *
     * @return FragmentManager
     */
    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    /**
     * startActivity
     *
     * @param clazz Class
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  Class
     * @param bundle Bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       Class
     * @param requestCode requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       Class
     * @param requestCode requestCode
     * @param bundle      Bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * show toast
     *
     * @param msg String
     */
    public void showToast(String msg) {
        if (null != msg && !AbStringUtils.isEmpty(msg)) {
            Snackbar.make(((Activity) mContext).getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
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
        FragmentActivity activity = getActivity();
        if (activity instanceof IProgressDialogControl) {
            return ((IProgressDialogControl) activity).showProgressDialog(text, isCancelable);
        }
        return null;
    }

    public void hideProgressDialog() {
        FragmentActivity activity = getActivity();
        if (activity instanceof IProgressDialogControl) {
            ((IProgressDialogControl) activity).hideProgressDialog();
        }
    }

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComing(eventCenter);
        }
    }
}
