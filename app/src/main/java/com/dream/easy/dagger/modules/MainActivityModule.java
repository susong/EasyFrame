package com.dream.easy.dagger.modules;

import com.dream.easy.ui.MainActivity;
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


}
