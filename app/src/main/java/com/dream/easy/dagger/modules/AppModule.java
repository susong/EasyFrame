package com.dream.easy.dagger.modules;

import android.app.Application;

import com.dream.data.api.Api;
import com.dream.data.api.UriHelper;
import com.dream.easy.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:48
 * Description: EasyFrame
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    UriHelper provideUriHelper() {
        return new UriHelper();
    }

    @Provides
    @Singleton
    Api provideApi(UriHelper uriHelper) {
        return new Api(uriHelper);
    }
}
