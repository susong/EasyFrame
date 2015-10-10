package com.dream.easy.dagger.modules;

import com.dream.easy.model.IMainActivityModel;
import com.dream.easy.model.impl.MainActivityModelImpl;
import com.dream.easy.presenter.IMainActivityPresenter;
import com.dream.easy.presenter.impl.MainActivityPresenterImpl;
import com.dream.easy.ui.MainActivity;
import com.dream.easy.view.IMainActivityView;
import com.dream.library.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午3:29
 * Description: EasyFrame
 */
@Module
public class MainActivityModule {

    private MainActivity mActivity;

    public MainActivityModule(MainActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    IMainActivityView provideIMainActivityView() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    IMainActivityModel provideIMainActivityModel() {
        return new MainActivityModelImpl();
    }

    @Provides
    @ActivityScope
    IMainActivityPresenter provideIMainActivityPresenter(
        MainActivity mainActivity,
        IMainActivityView mainActivityView,
        IMainActivityModel mainActivityModel) {
        return new MainActivityPresenterImpl(mainActivity, mainActivityView, mainActivityModel);
    }
}
