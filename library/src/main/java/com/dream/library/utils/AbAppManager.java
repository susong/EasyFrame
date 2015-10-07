package com.dream.library.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/2 下午9:57
 * Description: EasyFrame
 */
public class AbAppManager {

  private static final String TAG = AbAppManager.class.getSimpleName();

  private static AbAppManager mInstance = null;
  private static List<Activity> mActivities = new LinkedList<Activity>();

  private AbAppManager() {

  }

  public static AbAppManager getInstance() {
    if (null == mInstance) {
      synchronized (AbAppManager.class) {
        if (null == mInstance) {
          mInstance = new AbAppManager();
        }
      }
    }
    return mInstance;
  }

  public int size() {
    return mActivities.size();
  }

  public synchronized Activity getForwardActivity() {
    return size() > 0 ? mActivities.get(size() - 1) : null;
  }

  public synchronized void addActivity(Activity activity) {
    mActivities.add(activity);
  }

  public synchronized void removeActivity(Activity activity) {
    if (mActivities.contains(activity)) {
      mActivities.remove(activity);
    }
  }

  public synchronized void clear() {
    for (int i = mActivities.size() - 1; i > -1; i--) {
      Activity activity = mActivities.get(i);
      removeActivity(activity);
      activity.finish();
      i = mActivities.size();
    }
  }

  public synchronized void clearToTop() {
    for (int i = mActivities.size() - 2; i > -1; i--) {
      Activity activity = mActivities.get(i);
      removeActivity(activity);
      activity.finish();
      i = mActivities.size() - 1;
    }
  }
}
