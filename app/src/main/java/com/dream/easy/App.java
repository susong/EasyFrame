package com.dream.easy;

import com.dream.data.api.ApiConstants;
import com.dream.easy.base.BaseApplication;
import com.dream.easy.dagger.components.AppComponent;
import com.dream.easy.dagger.components.DaggerAppComponent;
import com.dream.easy.dagger.modules.AppModule;
import com.dream.library.utils.logger.AbLog;
import com.dream.library.utils.logger.LogLevel;
import com.dream.library.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class App extends BaseApplication {

    public static final String APP_NAME = "EasyFrame";
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
        initLogger();

//        Fresco.initialize(this);
    }

    /**
     * 初始化日志打印工具类
     */
    private void initLogger() {
        AbLog
            .init(APP_NAME)               // default PRETTYLOGGER or use just init()
//            .setMethodCount(2)          // default 2
//            .hideThreadInfo()           // default shown
//            .setMethodOffset(2)         // default 0
            .setLogLevel(LogLevel.FULL);  // default LogLevel.FULL
    }

    @Override
    protected void initImageLoader(boolean isNeed) {
        if (!isNeed) {
            return;
        }
        // 初始化ImageLoader
        ImageLoader.getInstance().init(
            ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(ApiConstants.Paths.IMAGE_LOADER_CACHE_PATH));
    }
}
