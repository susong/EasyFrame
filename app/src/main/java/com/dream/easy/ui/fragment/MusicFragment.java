package com.dream.easy.ui.fragment;

import android.view.View;

import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.library.eventbus.EventCenter;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午5:19
 * Description: EasyFrame
 */
public class MusicFragment extends BaseFragment {
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }
}
