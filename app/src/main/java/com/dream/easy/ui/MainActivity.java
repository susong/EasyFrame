package com.dream.easy.ui;

import android.content.Context;
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

import com.dream.easy.R;
import com.dream.easy.base.BaseActivity;
import com.dream.easy.bean.NavigationEntity;
import com.dream.easy.dagger.components.DaggerMainActivityComponent;
import com.dream.easy.dagger.components.MainActivityComponent;
import com.dream.easy.dagger.modules.MainActivityModule;
import com.dream.easy.ui.fragment.ImagesContainerFragment;
import com.dream.easy.ui.fragment.MusicFragment;
import com.dream.easy.ui.fragment.VideosContainerFragment;
import com.dream.easy.view.IMainView;
import com.dream.library.adapter.CommonAdapter;
import com.dream.library.adapter.ViewHolder;
import com.dream.library.base.BaseLazyFragment;
import com.dream.library.eventbus.EventCenter;
import com.dream.library.netstatus.NetUtils;
import com.dream.library.utils.DoubleClickExitHelper;
import com.dream.library.widgets.XViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class MainActivity extends BaseActivity implements IMainView {

    @Bind(R.id.main_container) XViewPager mMainContainer;
    @Bind(R.id.main_navigation_list) ListView mMainNavigationList;
    @Bind(R.id.main_drawer) DrawerLayout mMainDrawer;

    private int mCheckedListItemColorResIds[] = {
        R.color.navigation_checked_picture_text_color,
        R.color.navigation_checked_video_text_color,
        R.color.navigation_checked_music_text_color,
    };
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int mCurrentMenuCheckedPos = 0;
    private CommonAdapter<NavigationEntity> mCommonAdapter;
    private DoubleClickExitHelper mDoubleClickExitHelper;

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
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .mainActivityModule(new MainActivityModule(this))
            .build();
        mainActivityComponent.inject(this);
        init();
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

        List<BaseLazyFragment> pagerFragments = getPagerFragments();
        mMainContainer.setLocked(true);
        mMainContainer.setOffscreenPageLimit(pagerFragments.size());
        mMainContainer.setAdapter(new MainContainerPagerAdapter(getSupportFragmentManager(), pagerFragments));

        List<NavigationEntity> navigationListData = getNavigationListData(this);
        mCommonAdapter = new CommonAdapter<NavigationEntity>(this, navigationListData, R.layout.list_item_navigation) {
            @Override
            public void convert(ViewHolder holder, NavigationEntity navigationEntity) {
                holder.setImageResource(R.id.list_item_navigation_icon, navigationEntity.getIconResId())
                    .setText(R.id.list_item_navigation_name, navigationEntity.getName());
                int position = holder.getPosition();
                if (position == mCurrentMenuCheckedPos) {
                    holder.setTextColor(R.id.list_item_navigation_name, mCheckedListItemColorResIds[position]);
                } else {
                    holder.setTextColor(R.id.list_item_navigation_name, R.color.black);
                }
            }
        };
        mMainNavigationList.setAdapter(mCommonAdapter);

        setTitle(navigationListData.get(mCurrentMenuCheckedPos).getName());

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
    }

    private List<BaseLazyFragment> getPagerFragments() {
        List<BaseLazyFragment> fragments = new ArrayList<>();
        fragments.add(new ImagesContainerFragment());
        fragments.add(new VideosContainerFragment());
        fragments.add(new MusicFragment());
        return fragments;
    }

    private List<NavigationEntity> getNavigationListData(Context context) {
        List<NavigationEntity> navigationEntities = new ArrayList<>();
        String[] navigationArrays = context.getResources().getStringArray(R.array.navigation_list);
        navigationEntities.add(new NavigationEntity("", navigationArrays[0], R.drawable.ic_picture));
        navigationEntities.add(new NavigationEntity("", navigationArrays[1], R.drawable.ic_video));
        navigationEntities.add(new NavigationEntity("", navigationArrays[2], R.drawable.ic_music));
        return navigationEntities;
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

        List<BaseLazyFragment> mFragmentList;

        public MainContainerPagerAdapter(FragmentManager fm, List<BaseLazyFragment> fragmentList) {
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
