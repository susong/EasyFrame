package com.dream.easy.base;

import com.dream.easy.view.base.IBaseView;
import com.dream.library.base.BaseLibFragment;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:56
 * Description: EasyFrame
 */
public abstract class BaseFragment extends BaseLibFragment implements IBaseView {

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void showException(String msg) {
        super.showError(msg);
    }

    @Override
    public void showNetError() {
        super.showNetworkError();
    }

    @Override
    public void showLoading(String msg) {
        super.showLoading(msg);
    }

    @Override
    public void hideLoading() {
        clearEmpty();
    }
}
