package com.dream.easy.dagger.modules;

import android.app.Application;

import com.dream.easy.base.AbDaggerApplication;
import com.dream.library.dagger.ForApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:48
 * Description: EasyFrame
 */
@Module
public class ApplicationModule {

    private final AbDaggerApplication application;

    public ApplicationModule(AbDaggerApplication application) {
        this.application = application;
    }

    @Provides
    @ForApplication
    Application provideApplication() {
        return application;
    }
}
