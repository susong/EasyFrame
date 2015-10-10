package com.dream.easy.dagger.components;

import com.dream.easy.dagger.modules.ImageContainerFragmentModule;
import com.dream.easy.ui.fragment.ImagesContainerFragment;
import com.dream.library.dagger.FragmentScope;

import dagger.Subcomponent;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/10 下午4:27
 * Description: EasyFrame
 */
@FragmentScope
@Subcomponent(
    modules = ImageContainerFragmentModule.class
)
public interface ImageContainerFragmentComponent {
    ImagesContainerFragment inject(ImagesContainerFragment imagesContainerFragment);
}
