package com.dream.easy.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.dream.easy.R;
import com.dream.easy.base.BaseActivity;
import com.dream.easy.dagger.components.DaggerMainActivityComponent;
import com.dream.easy.dagger.components.MainActivityComponent;
import com.dream.easy.dagger.modules.MainActivityModule;
import com.dream.easy.view.IMainView;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.netstatus.NetUtils;
import com.dream.library.widgets.XViewPager;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class MainActivity extends BaseActivity implements IMainView {

    @Bind(R.id.common_toolbar) protected Toolbar mCommonToolbar;
    @Bind(R.id.main_container) XViewPager mMainContainer;
    @Bind(R.id.main_navigation_list) ListView mMainNavigationList;
    @Bind(R.id.main_drawer) DrawerLayout mMainDrawer;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
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
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .mainActivityModule(new MainActivityModule(this))
            .build();
        mainActivityComponent.inject(this);
        init();
    }

    private void init() {
        setSupportActionBar(mCommonToolbar);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mMainDrawer, mCommonToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        mMainDrawer.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }
}
