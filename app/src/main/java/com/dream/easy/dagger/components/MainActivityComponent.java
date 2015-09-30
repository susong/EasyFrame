package com.dream.easy.dagger.components;

import com.dream.easy.MainActivity;
import com.dream.easy.dagger.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午3:28
 * Description: EasyFrame
 */
@Singleton
@Component(dependencies = ApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
