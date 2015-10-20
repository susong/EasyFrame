package com.dream.easy;

import com.dream.easy.base.BaseApplication;
import com.dream.easy.dagger.components.AppComponent;
import com.dream.easy.dagger.components.DaggerAppComponent;
import com.dream.easy.dagger.modules.AppModule;


/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class App extends BaseApplication {

    private static App mInstance;

    private AppComponent mAppComponent;


    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initDagger();
        init();
    }

    private void initDagger() {
        mAppComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * 初始化
     */
    private void init() {

    }
}
