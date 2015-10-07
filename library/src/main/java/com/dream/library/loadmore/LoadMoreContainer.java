package com.dream.library.loadmore;

import android.view.View;
import android.widget.AbsListView;

public interface LoadMoreContainer {

    void setShowLoadingForFirstPage(boolean showLoading);

    void setAutoLoadMore(boolean autoLoadMore);

    void setOnScrollListener(AbsListView.OnScrollListener l);

    void setLoadMoreView(View view);

    void setLoadMoreUIHandler(LoadMoreUIHandler handler);

    void setLoadMoreHandler(LoadMoreHandler handler);

    /**
     * When data has loaded
     */
    void loadMoreFinish(boolean emptyResult, boolean hasMore);

    /**
     * When something unexpected happened while loading the data
     */
    void loadMoreError(int errorCode, String errorMessage);
}
