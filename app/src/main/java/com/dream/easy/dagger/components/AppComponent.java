package com.dream.easy.dagger.components;

import com.dream.easy.dagger.modules.AppModule;
import com.dream.easy.dagger.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:48
 * Description: EasyFrame
 */
@Singleton
@Component(
    modules = AppModule.class
)
public interface AppComponent {
    MainActivityComponent plus(MainActivityModule mainActivityModule);
}
