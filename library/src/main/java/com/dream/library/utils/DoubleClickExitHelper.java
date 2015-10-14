package com.dream.library.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.dream.library.R;


/***
 * 双击退出
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2015年1月5日 下午7:07:44
 */
public class DoubleClickExitHelper {

    private final Activity mActivity;

    private boolean mIsOneKeyBack = false;
    private Handler mHandler;
    private Toast mToast;

    public void setIsOneKeyBack(boolean isOneKeyBack) {
        mIsOneKeyBack = isOneKeyBack;
    }

    public DoubleClickExitHelper(Activity activity) {
        mActivity = activity;
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Activity onKeyDown事件
     */
    public boolean onKeyDown(int keyCode) {
        return onKeyDown(keyCode, null);
    }

    public boolean onKeyDown(int keyCode, View view) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (mIsOneKeyBack) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mToast != null) {
                mToast.cancel();
            }
            // 退出
            AbAppManager.getAbAppManager().AppExit();
            return true;
        } else {
            mIsOneKeyBack = true;
            if (view == null) {
                if (mToast == null) {
                    mToast = Toast.makeText(mActivity, R.string.tip_double_click_exit, Toast.LENGTH_SHORT);
                }
                mToast.show();
            } else {
                // MaterialDesign 中的 Snackbar 与 Toast 类似
                Snackbar.make(view, R.string.tip_double_click_exit, Snackbar.LENGTH_SHORT).show();
            }
            mHandler.postDelayed(onBackTimeRunnable, 2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            mIsOneKeyBack = false;
            if (mToast != null) {
                mToast.cancel();
            }
        }
    };
}
