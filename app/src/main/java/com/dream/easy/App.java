package com.dream.easy;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * EasyFrame
 * Created by Su on 15/9/28 下午2:08.
 */
public class App extends Application {

    public static final String APP_NAME = "EasyFrame";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initLogger();
    }

    private void initLogger() {
        Logger
            .init(APP_NAME)               // default PRETTYLOGGER or use just init()
//            .setMethodCount(2)          // default 2
//            .hideThreadInfo()           // default shown
//            .setMethodOffset(2)         // default 0
            .setLogLevel(LogLevel.FULL);  // default LogLevel.FULL
    }
}
