package com.dream.easy.view;

import com.dream.easy.bean.NavigationEntity;
import com.dream.library.base.BaseLibFragment;

import java.util.List;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/6 下午9:38
 * Description: EasyFrame
 */
public interface IMainActivityView {
    void init(List<BaseLibFragment> fragments, List<NavigationEntity> navigationList);
}
