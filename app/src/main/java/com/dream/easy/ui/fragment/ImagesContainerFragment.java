package com.dream.easy.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.easy.bean.BaseEntity;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.widgets.XViewPager;

import java.util.List;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:55
 * Description: EasyFrame
 */
public class ImagesContainerFragment extends BaseFragment {

    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @Bind(R.id.viewPager) XViewPager mViewPager;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_images_container;
    }

    @Override
    protected void initViewsAndEvents() {
//        mTabLayout.setupWithViewPager(mViewPager);
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

    private class ImagesContainerPagerAdaper extends FragmentPagerAdapter {

        private List<BaseEntity> mEntityList;

        public ImagesContainerPagerAdaper(FragmentManager fm, List<BaseEntity> entityList) {
            super(fm);
            this.mEntityList = entityList;
        }

        @Override
        public Fragment getItem(int position) {
            return new ImagesListFragment();
        }

        @Override
        public int getCount() {
            return mEntityList == null ? 0 : mEntityList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mEntityList == null ? null : mEntityList.get(position).getName();
        }
    }
}
