package com.dream.easy;

import com.dream.easy.base.AbDaggerApplication;
import com.orhanobut.logger.AbLog;
import com.orhanobut.logger.LogLevel;


/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class App extends AbDaggerApplication {

    public static final String APP_NAME = "EasyFrame";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initLogger();
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
}
