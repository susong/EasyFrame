package com.dream.easy.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.easy.bean.BaseEntity;
import com.dream.easy.dagger.components.ImageContainerFragmentComponent;
import com.dream.easy.dagger.modules.ImageContainerFragmentModule;
import com.dream.easy.presenter.IImageContainerFragmentPresenter;
import com.dream.easy.ui.MainActivity;
import com.dream.easy.view.IImagesContainerFragmentView;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.widgets.XViewPager;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:55
 * Description: EasyFrame
 */
public class ImageContainerFragment extends BaseFragment implements Serializable, IImagesContainerFragmentView {

    @Bind(R.id.tabLayout) TabLayout mTabLayout;
    @Bind(R.id.viewPager) XViewPager mViewPager;

    @Inject IImageContainerFragmentPresenter mPresenter;
    private ImageContainerFragmentComponent mImageContainerFragmentComponent;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_images_container;
    }

    @Override
    protected void initViewsAndEvents() {
        initDagger();
        init();
    }

    private void initDagger() {
        mImageContainerFragmentComponent = ((MainActivity) mContext).getMainActivityComponent()
            .plus(new ImageContainerFragmentModule(this));
        mImageContainerFragmentComponent.inject(this);
    }

    private void init() {
        mPresenter.init();
    }

    public ImageContainerFragmentComponent getImageContainerFragmentComponent() {
        return mImageContainerFragmentComponent;
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
            return ImageListFragment.getInstance(ImageContainerFragment.this, mEntityList.get(position));
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
