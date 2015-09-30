package com.dream.easy.dagger.components;

import android.app.Application;

import com.dream.easy.dagger.modules.ApplicationModule;
import com.dream.library.dagger.ForApplication;

import dagger.Component;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:48
 * Description: EasyFrame
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ForApplication
    Application application();
}
