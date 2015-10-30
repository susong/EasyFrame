package com.dream.library.interf;

import android.view.View;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/27 下午3:54
 * Description: EasyFrame
 */
public interface IEmptyControl {

    void showLoading();

    void showLoading(String msg);

    void showEmpty();

    void showEmpty(String msg);

    void showEmpty(View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showError();

    void showError(String msg);

    void showError(View.OnClickListener onClickListener);

    void showError(String msg, View.OnClickListener onClickListener);

    void showNetworkError();

    void showNetworkError(View.OnClickListener onClickListener);

    void clearEmpty();
}
