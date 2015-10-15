package com.dream.easy.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.dream.easy.base.BaseActivity;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.utils.AbPropertiesUtils;
import com.dream.library.utils.logger.AbLog;
import com.dream.library.utils.netstatus.AbNetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/15 上午12:08
 * Description: EasyFrame
 */
public class ConfigActivity extends BaseActivity {
    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return 0;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        AbPropertiesUtils abPropertiesUtils = AbPropertiesUtils.init(this);
//        Properties ps = new Properties();
//        ps.setProperty("name","jack");
//        ps.setProperty("age","1");
//        abPropertiesUtils.set(ps);
//        abPropertiesUtils.set("like","apple");
//        abPropertiesUtils.set("工作","IT工程师");
        abPropertiesUtils.remove("工作", "like", "aaa");
        abPropertiesUtils.get("name");

        Map<String, String> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", "1");
        AbLog.i(map.toString());
    }

    @Override
    protected void onNetworkConnected(AbNetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
}
