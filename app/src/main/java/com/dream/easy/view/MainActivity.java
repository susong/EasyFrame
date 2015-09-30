package com.dream.easy.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.dream.data.api.Api;
import com.dream.easy.base.AbDaggerBaseActivity;
import com.dream.easy.dagger.components.DaggerMainActivityComponent;
import com.dream.easy.dagger.components.MainActivityComponent;
import com.dream.easy.dagger.modules.MainActivityModule;
import com.dream.library.dagger.ForActivity;
import com.dream.library.dagger.ForApplication;
import com.orhanobut.logger.AbLog;

import javax.inject.Inject;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class MainActivity extends AbDaggerBaseActivity {

    @Inject Api mApi;
    @Inject @ForApplication Application mApplication;
    @Inject @ForActivity Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .mainActivityModule(new MainActivityModule(this))
            .build();
        mainActivityComponent.inject(this);


        AbLog.d(mApi.toString());
        AbLog.d(mApplication.toString());
        AbLog.d(mActivity.toString());

    }

}
