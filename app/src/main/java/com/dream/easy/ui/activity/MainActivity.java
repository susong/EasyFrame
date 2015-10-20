package com.dream.easy.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dream.easy.App;
import com.dream.easy.R;
import com.dream.easy.base.BaseActivity;
import com.dream.easy.bean.NavigationEntity;
import com.dream.easy.dagger.components.MainActivityComponent;
import com.dream.easy.dagger.modules.MainActivityModule;
import com.dream.easy.presenter.IMainActivityPresenter;
import com.dream.easy.view.IMainActivityView;
import com.dream.library.adapter.CommonAdapter;
import com.dream.library.adapter.CommonAdapterHelper;
import com.dream.library.base.BaseLibFragment;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.utils.netstatus.AbNetUtils;
import com.dream.library.utils.DoubleClickExitHelper;
import com.dream.library.widgets.XViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class MainActivity extends BaseActivity implements IMainActivityView {

    @Bind(R.id.main_container) XViewPager mMainContainer;
    @Bind(R.id.main_navigation_list) ListView mMainNavigationList;
    @Bind(R.id.main_drawer) DrawerLayout mMainDrawer;

    @Inject IMainActivityPresenter mMainActivityPresenter;

    private int mCheckedListItemColorResIds[] = {
        R.color.navigation_checked_picture_text_color,
        R.color.navigation_checked_video_text_color,
        R.color.navigation_checked_music_text_color,
    };
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int mCurrentMenuCheckedPos = 0;
    private CommonAdapter<NavigationEntity> mCommonAdapter;
    private DoubleClickExitHelper mDoubleClickExitHelper;
    private MainActivityComponent mMainActivityComponent;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return mMainContainer;
    }

    @Override
    protected void initViewsAndEvents() {
        initDagger();
        init();
    }

    private void initDagger() {
        mMainActivityComponent = App.getInstance()
            .getAppComponent()
            .plus(new MainActivityModule(this));
        mMainActivityComponent.inject(this);
    }

    public MainActivityComponent getMainActivityComponent() {
        return mMainActivityComponent;
    }

    private void init() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mMainDrawer, mCommonToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mCommonAdapter != null) {
                    setTitle(mCommonAdapter.getItem(mCurrentMenuCheckedPos).getName());
                }
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mMainDrawer.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();


        mMainNavigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentMenuCheckedPos = position;
                mCommonAdapter.notifyDataSetChanged();
                mMainDrawer.closeDrawer(GravityCompat.START);
                mMainContainer.setCurrentItem(mCurrentMenuCheckedPos, false);
            }
        });
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        mMainActivityPresenter.init();
    }


    @Override
    public void init(List<BaseLibFragment> fragments, List<NavigationEntity> navigationList) {
        mMainContainer.setLocked(true);
        mMainContainer.setOffscreenPageLimit(fragments.size());
        mMainContainer.setAdapter(new MainContainerPagerAdapter(getSupportFragmentManager(), fragments));

        mCommonAdapter = new CommonAdapter<NavigationEntity>(this, navigationList, R.layout.item_lv_navigation) {
            @Override
            public void convert(CommonAdapterHelper helper, NavigationEntity navigationEntity) {
                helper.setImageResource(R.id.list_item_navigation_icon, navigationEntity.getIconResId())
                    .setText(R.id.list_item_navigation_name, navigationEntity.getName());
                int position = helper.getPosition();
                if (position == mCurrentMenuCheckedPos) {
                    helper.setTextColor(R.id.list_item_navigation_name, mCheckedListItemColorResIds[position]);
                } else {
                    helper.setTextColor(R.id.list_item_navigation_name, R.color.black);
                }
            }
        };
        mMainNavigationList.setAdapter(mCommonAdapter);

        setTitle(navigationList.get(mCurrentMenuCheckedPos).getName());
    }

    @Override
    protected void onNetworkConnected(AbNetUtils.NetType type) {

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (mMainDrawer.isDrawerOpen(GravityCompat.START)) {
                mMainDrawer.closeDrawer(GravityCompat.START);
            } else {
                mMainDrawer.openDrawer(GravityCompat.START);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mMainDrawer.isDrawerOpen(GravityCompat.START)) {
                mMainDrawer.closeDrawer(GravityCompat.START);
                return true;
            } else {
                return mDoubleClickExitHelper.onKeyDown(keyCode);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    private class MainContainerPagerAdapter extends FragmentPagerAdapter {

        List<BaseLibFragment> mFragmentList;

        public MainContainerPagerAdapter(FragmentManager fm, List<BaseLibFragment> fragmentList) {
            super(fm);
            this.mFragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList == null ? null : mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList == null ? 0 : mFragmentList.size();
        }
    }
}
