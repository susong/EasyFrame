package com.dream.easy.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.dream.data.api.ApiConstants;
import com.dream.data.api.UriHelper;
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
import com.dream.library.adapter.CommonAdapterHelper;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.netstatus.AbNetUtils;
import com.dream.library.widgets.XSwipeRefreshLayout;
import com.dream.library.widgets.pla.PLA_AdapterView;
import com.dream.library.widgets.pla.PLA_LoadMoreListView;
import com.dream.library.widgets.pla.PLA_MultiColumnListView;
import com.dream.library.widgets.pla.util.PLA_ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/8 下午4:37
 * Description: EasyFrame
 */
public class ImageListFragment extends BaseFragment implements IImageListFragmentView, PLA_AdapterView.OnItemClickListener, PLA_MultiColumnListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String BASE_ENTITY = "BASE_ENTITY";
    @Inject IImageListFragmentPresenter mIImageListFragmentPresenter;
    @Bind(R.id.view_main) RelativeLayout mViewMain;
    @Bind(R.id.swipe_refresh_layout) XSwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.pla_load_more_list_view) PLA_LoadMoreListView mPlaLoadMoreListView;
    private static ImageContainerFragment mImageContainerFragment;
    private BaseEntity mBaseEntity;

    /**
     * the page number
     */
    private int mCurrentPage = 0;
    private CommonAdapter<ImageEntity> mCommonAdapter;

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

        if (AbNetUtils.isNetworkConnected(mContext)) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_REFRESH_DATA, mBaseEntity.getId(), mCurrentPage, true);
                    }
                }, ApiConstants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
            }
        } else {
            toggleNetworkError(true, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_REFRESH_DATA, mBaseEntity.getId(), mCurrentPage, true);
                }
            });
        }

        mCommonAdapter = new CommonAdapter<ImageEntity>(mContext, R.layout.item_lv_image_list) {
            @Override
            public void convert(CommonAdapterHelper helper, ImageEntity imageEntity) {
                int width = imageEntity.getImageWidth();
                int height = imageEntity.getImageHeight();
                PLA_ImageView image = helper.getView(R.id.iv_image);
                ImageLoader.getInstance().displayImage(imageEntity.getImageUrl(), image);
                image.setImageWidth(width);
                image.setImageHeight(height);
            }
        };

        mPlaLoadMoreListView.setOnItemClickListener(this);
        mPlaLoadMoreListView.setOnLoadMoreListener(this);
        mPlaLoadMoreListView.setAdapter(mCommonAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(
            getResources().getColor(R.color.gplus_color_1),
            getResources().getColor(R.color.gplus_color_2),
            getResources().getColor(R.color.gplus_color_3),
            getResources().getColor(R.color.gplus_color_4));
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
    public void showError(String msg) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_REFRESH_DATA, mBaseEntity.getId(), mCurrentPage, false);
            }
        });
    }

    @Override
    public void refreshListData(ResponseImageListEntity responseImageListEntity) {
        if (null != mSwipeRefreshLayout) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (responseImageListEntity != null) {
            if (mCommonAdapter != null) {
                mCommonAdapter.replaceAll(responseImageListEntity.getImgs());
            }
            if (mPlaLoadMoreListView != null && mCommonAdapter != null) {
                if (calculateTotalPages(mCommonAdapter.getCount()) > mCurrentPage) {
                    mPlaLoadMoreListView.setCanLoadMore(true);
                } else {
                    mPlaLoadMoreListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void addMoreListData(ResponseImageListEntity responseImageListEntity) {
        if (mPlaLoadMoreListView != null) {
            mPlaLoadMoreListView.onLoadMoreComplete();
        }
        if (responseImageListEntity != null) {
            if (mCommonAdapter != null) {
                mCommonAdapter.addAll(responseImageListEntity.getImgs());
            }
            if (mPlaLoadMoreListView != null && mCommonAdapter != null) {
                if (calculateTotalPages(mCommonAdapter.getCount()) > mCurrentPage) {
                    mPlaLoadMoreListView.setCanLoadMore(true);
                } else {
                    mPlaLoadMoreListView.setCanLoadMore(false);
                }
            }
        }
    }

    @Override
    public void onItemClick(PLA_AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_REFRESH_DATA, mBaseEntity.getId(), mCurrentPage, false);
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mIImageListFragmentPresenter.loadImageList(TAG_LOG, Constants.EVENT_LOAD_MORE_DATA, mBaseEntity.getId(), mCurrentPage, false);
    }

    public int calculateTotalPages(int totalNumber) {
        if (totalNumber > 0) {
            return totalNumber % UriHelper.PAGE_LIMIT != 0 ? (totalNumber / UriHelper.PAGE_LIMIT + 1) : totalNumber / UriHelper.PAGE_LIMIT;
        } else {
            return 0;
        }
    }
}
