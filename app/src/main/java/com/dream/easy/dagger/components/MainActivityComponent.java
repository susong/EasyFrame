package com.dream.easy.dagger.components;

import com.dream.easy.dagger.modules.ImageContainerFragmentModule;
import com.dream.easy.dagger.modules.MainActivityModule;
import com.dream.easy.ui.MainActivity;
import com.dream.library.dagger.ActivityScope;

import dagger.Subcomponent;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午3:28
 * Description: EasyFrame
 */
@ActivityScope
@Subcomponent(
    modules = MainActivityModule.class
)
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);

    ImageContainerFragmentComponent plus(ImageContainerFragmentModule imageContainerFragmentModule);
}
