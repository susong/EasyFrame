package com.dream.easy.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.easy.bean.BaseEntity;
import com.dream.easy.dagger.modules.ImageListFragmentModule;
import com.dream.easy.presenter.IImageListFragmentPresenter;
import com.dream.easy.view.IImageListFragmentView;
import com.dream.library.eventbus.EventCenter;

import javax.inject.Inject;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午4:37
 * Description: EasyFrame
 */
public class ImageListFragment extends BaseFragment implements IImageListFragmentView {

    private static final String BASE_ENTITY = "BASE_ENTITY";
    private static final String IMAGE_CONTAINER_FRAGMENT = "IMAGE_CONTAINER_FRAGMENT";
    @Inject IImageListFragmentPresenter mIImageListFragmentPresenter;
    private ImageContainerFragment mImageContainerFragment;
    private BaseEntity mBaseEntity;


    public static ImageListFragment getInstance(ImageContainerFragment imageContainerFragment, BaseEntity baseEntity) {
        ImageListFragment imageListFragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BASE_ENTITY, baseEntity);
        bundle.putSerializable(IMAGE_CONTAINER_FRAGMENT, imageContainerFragment);
        imageListFragment.setArguments(bundle);
        return imageListFragment;
    }


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_images_list;
    }

    @Override
    protected void initViewsAndEvents() {
        mBaseEntity = getArguments().getParcelable(BASE_ENTITY);
        mImageContainerFragment = (ImageContainerFragment) getArguments().getSerializable(IMAGE_CONTAINER_FRAGMENT);
        initDagger();
    }

    private void initDagger() {
        mImageContainerFragment.getImageContainerFragmentComponent()
            .plus(new ImageListFragmentModule(this))
            .inject(this);
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
