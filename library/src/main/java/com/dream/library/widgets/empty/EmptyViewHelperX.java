package com.dream.library.widgets.empty;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/3 上午12:13
 * Description: EasyFrame
 */
public class EmptyViewHelperX implements IEmptyViewHelper {

  private IEmptyViewHelper helper;
  private View view;

  public EmptyViewHelperX(View view) {
    super();
    this.view = view;
    ViewGroup group = (ViewGroup) view.getParent();
    LayoutParams layoutParams = view.getLayoutParams();
    FrameLayout frameLayout = new FrameLayout(view.getContext());
    group.removeView(view);
    group.addView(frameLayout, layoutParams);

    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    View floatView = new View(view.getContext());
    frameLayout.addView(view, params);
    frameLayout.addView(floatView, params);
    helper = new EmptyViewHelper(floatView);
  }

  @Override
  public View getCurrentLayout() {
    return helper.getCurrentLayout();
  }

  @Override
  public void restoreView() {
    helper.restoreView();
  }

  @Override
  public void showLayout(View view) {
    helper.showLayout(view);
  }

  @Override
  public View inflate(int layoutId) {
    return helper.inflate(layoutId);
  }

  @Override
  public Context getContext() {
    return helper.getContext();
  }

  @Override
  public View getView() {
    return view;
  }
}
