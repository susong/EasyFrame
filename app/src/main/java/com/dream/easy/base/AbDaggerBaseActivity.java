package com.dream.easy.base;

import android.os.Bundle;

import com.dream.easy.dagger.components.ApplicationComponent;
import com.dream.library.AbBaseActivity;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:52
 * Description: EasyFrame
 */
public abstract class AbDaggerBaseActivity extends AbBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AbDaggerApplication) getApplication()).getApplicationComponent();
    }
}
