package com.dream.easy.base;

import com.dream.easy.dagger.components.ApplicationComponent;
import com.dream.easy.dagger.components.DaggerApplicationComponent;
import com.dream.easy.dagger.modules.ApplicationModule;
import com.dream.library.base.BaseLibApplication;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 上午10:51
 * Description: EasyFrame
 */
public abstract class BaseApplication extends BaseLibApplication {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
