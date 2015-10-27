package com.dream.library.interf;

import android.app.ProgressDialog;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/27 下午3:45
 * Description: EasyFrame
 */
public interface ProgressDialogControl {

    ProgressDialog showProgressDialog();

    ProgressDialog showNonCancelableProgressDialog();

    ProgressDialog showProgressDialog(int resId);

    ProgressDialog showNonCancelableProgressDialog(int resId);

    ProgressDialog showProgressDialog(String text);

    ProgressDialog showNonCancelableProgressDialog(String text);

    ProgressDialog showProgressDialog(String text, boolean isCancelable);

    void hideProgressDialog();
}
