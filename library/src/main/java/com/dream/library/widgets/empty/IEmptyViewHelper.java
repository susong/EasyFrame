package com.dream.library.widgets.empty;

import android.content.Context;
import android.view.View;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/10/3 上午12:13
 * Description: EasyFrame
 */
public interface IEmptyViewHelper {

  View getCurrentLayout();

  void restoreView();

  void showLayout(View view);

  View inflate(int layoutId);

  Context getContext();

  View getView();

}