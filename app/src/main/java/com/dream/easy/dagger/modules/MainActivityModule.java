package com.dream.easy.dagger.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

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

    private Activity mActivity;

    public MainActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @Named("MainActivity")
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @Named("MainActivity")
    Context provideContext() {
        return mActivity;
    }
}
