package com.dream.easy.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.dream.data.entity.ImageEntity;
import com.dream.data.entity.ResponseImageListEntity;
import com.dream.easy.Constants;
import com.dream.easy.R;
import com.dream.easy.base.BaseFragment;
import com.dream.easy.bean.BaseEntity;
import com.dream.easy.dagger.modules.ImageListFragmentModule;
import com.dream.easy.presenter.IImageListFragmentPresenter;
import com.dream.easy.view.IImageListFragmentView;
import com.dream.library.adapter.CommonAdapter;
import com.dream.library.adapter.ViewHolder;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.widgets.XSwipeRefreshLayout;
import com.dream.library.widgets.pla.PLA_LoadMoreListView;
import com.dream.library.widgets.pla.util.PLA_ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午4:37
 * Description: EasyFrame
 */
public class ImageListFragment extends BaseFragment implements IImageListFragmentView {

    private static final String BASE_ENTITY = "BASE_ENTITY";
    @Inject IImageListFragmentPresenter mIImageListFragmentPresenter;
    @Bind(R.id.view_main) RelativeLayout mViewMain;
    @Bind(R.id.swipe_refresh_layout) XSwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.pla_load_more_list_view) PLA_LoadMoreListView mPlaLoadMoreListView;
    private static ImageContainerFragment mImageContainerFragment;
    private BaseEntity mBaseEntity;

    /**
     * this variable must be initialized.
     */
    private static String mCurrentImagesCategory = "美女";

    /**
     * the page number
     */
    private int mCurrentPage = 0;

    public static ImageListFragment getInstance(ImageContainerFragment imageContainerFragment, BaseEntity baseEntity) {
        ImageListFragment imageListFragment = new ImageListFragment();
        mImageContainerFragment = imageContainerFragment;
        Bundle bundle = new Bundle();
        bundle.putParcelable(BASE_ENTITY, baseEntity);
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
        initDagger();
        mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_REFRESH_DATA, mBaseEntity.getId(), mCurrentPage, false);
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
        return mViewMain;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    public void refreshListData(ResponseImageListEntity responseImageListEntity) {
        List<ImageEntity> imgs = responseImageListEntity.getImgs();
        mPlaLoadMoreListView.setAdapter(new CommonAdapter<ImageEntity>(mContext, imgs, R.layout.item_lv_image_list) {
            @Override
            public void convert(ViewHolder holder, ImageEntity imageEntity) {
//                int width = imageEntity.getThumbnailWidth();
//                int height = imageEntity.getThumbnailHeight();
                int width = imageEntity.getImageWidth();
                int height = imageEntity.getImageHeight();
                PLA_ImageView image = holder.getView(R.id.iv_image);
//                String url = imageEntity.getThumbnailUrl();
                String url = imageEntity.getImageUrl();
                ImageLoader.getInstance().displayImage(url, image);
//                Picasso.with(mContext).load(url).into(image);
                image.setImageWidth(width);
                image.setImageHeight(height);
            }
        });
    }

    @Override
    public void addMoreListData(ResponseImageListEntity responseImageListEntity) {

    }
}
