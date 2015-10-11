package com.dream.easy.dagger.components;

import com.dream.easy.dagger.modules.ImageListFragmentModule;
import com.dream.easy.dagger.scope.ImageListFragmentScope;
import com.dream.easy.ui.fragment.ImageListFragment;

import dagger.Subcomponent;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/11 下午11:21
 * Description: EasyFrame
 */
@ImageListFragmentScope
@Subcomponent(
    modules = ImageListFragmentModule.class
)
public interface ImageListFragmentComponent {
    ImageListFragment inject(ImageListFragment imageListFragment);
}
