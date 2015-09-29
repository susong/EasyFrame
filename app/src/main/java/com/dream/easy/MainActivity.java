package com.dream.easy;

import android.os.Bundle;

import com.android.volley.Response;
import com.dream.library.AbBaseActivity;
import com.dream.library.utils.AbToastUtil;
import com.dream.library.volley.CustomRequest;
import com.dream.library.volley.VolleyHelper;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.AbLog;

import java.util.List;

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

        VolleyHelper.getInstance()
            .addRequest(new CustomRequest<String>(new CustomRequest.RequestBuilder()
                .url("https://api.github.com/users/susong0618/repos")
                .successListener(new com.android.volley.Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        AbLog.json((String) response);
                    }
                })));

        VolleyHelper.getInstance()
            .addRequest(new CustomRequest<List<Bean>>(new CustomRequest.RequestBuilder()
                .url("https://api.github.com/users/susong0618/repos")
                .type(new TypeToken<List<Bean>>() {
                }.getType())
                .successListener(new com.android.volley.Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        List<Bean> response1 = (List<Bean>) response;
                        for (Bean bean : response1) {
                            AbLog.i(bean.name);
                        }
                    }
                })));

        VolleyHelper.getInstance().getRequestQueue().add(new CustomRequest<List<Bean>>("https://api.github.com/users/susong0618/repos",
            new TypeToken<List<Bean>>() {
            }.getType(),
            null, response -> {
                for (Bean bean : response) {
                    AbLog.i(bean.name);
                }
            }, null));
    }

    public class Bean {
        private String name;
    }
}
