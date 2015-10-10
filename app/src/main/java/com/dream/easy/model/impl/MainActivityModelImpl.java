package com.dream.easy.model.impl;

import android.content.Context;

import com.dream.easy.R;
import com.dream.easy.bean.NavigationEntity;
import com.dream.easy.model.IMainActivityModel;
import com.dream.easy.ui.fragment.ImagesContainerFragment;
import com.dream.easy.ui.fragment.MusicFragment;
import com.dream.easy.ui.fragment.VideosContainerFragment;
import com.dream.library.base.BaseLibFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/10 下午5:22
 * Description: EasyFrame
 */
public class MainActivityModelImpl implements IMainActivityModel {
    @Override
    public List<BaseLibFragment> getPagerFragments() {
        List<BaseLibFragment> fragments = new ArrayList<>();
        fragments.add(new ImagesContainerFragment());
        fragments.add(new VideosContainerFragment());
        fragments.add(new MusicFragment());
        return fragments;
    }

    @Override
    public List<NavigationEntity> getNavigationListData(Context context) {
        List<NavigationEntity> navigationEntities = new ArrayList<>();
        String[] navigationArrays = context.getResources().getStringArray(R.array.navigation_list);
        navigationEntities.add(new NavigationEntity("", navigationArrays[0], R.drawable.ic_picture));
        navigationEntities.add(new NavigationEntity("", navigationArrays[1], R.drawable.ic_video));
        navigationEntities.add(new NavigationEntity("", navigationArrays[2], R.drawable.ic_music));
        return navigationEntities;
    }
}
