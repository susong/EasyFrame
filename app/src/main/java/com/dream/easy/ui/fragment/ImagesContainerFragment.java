package com.dream.easy.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.easy.bean.BaseEntity;
import com.dream.easy.dagger.modules.ImageContainerFragmentModule;
import com.dream.easy.presenter.IImagesContainerPresenter;
import com.dream.easy.ui.MainActivity;
import com.dream.easy.view.ICommonContainerView;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.widgets.XViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:55
 * Description: EasyFrame
 */
public class ImagesContainerFragment extends BaseFragment implements ICommonContainerView {

    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @Bind(R.id.viewPager) XViewPager mViewPager;

    @Inject IImagesContainerPresenter mPresenter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_images_container;
    }

    @Override
    protected void initViewsAndEvents() {
        ((MainActivity) mContext).getMainActivityComponent()
            .plus(new ImageContainerFragmentModule(this))
            .inject(this);
        mPresenter.init();
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

    @Override
    public void init(List<BaseEntity> entityList) {
        if (entityList != null && !entityList.isEmpty()) {
            mViewPager.setOffscreenPageLimit(entityList.size());
            mViewPager.setAdapter(new ImagesContainerPagerAdapter(getSupportFragmentManager(), entityList));
            mTabLayout.setupWithViewPager(mViewPager);
            mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    super.onTabSelected(tab);
                }
            });
        }
    }

    private class ImagesContainerPagerAdapter extends FragmentPagerAdapter {

        private List<BaseEntity> mEntityList;

        public ImagesContainerPagerAdapter(FragmentManager fm, List<BaseEntity> entityList) {
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
