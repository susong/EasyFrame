package com.dream.easy;

import android.os.Bundle;

import com.dream.library.AbBaseActivity;
import com.dream.library.utils.AbToastUtil;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: EasyFrame
 */
public class MainActivity extends AbBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AbToastUtil.show("d");
    }
}
