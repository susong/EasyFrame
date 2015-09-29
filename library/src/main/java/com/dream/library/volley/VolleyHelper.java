package com.dream.library.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;

import junit.framework.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/29 上午8:51
 * Description: EasyFrame
 */
public class VolleyHelper {
    private static VolleyHelper mInstance;
    private RequestQueue mRequestQueue;

    private VolleyHelper() {
    }

    public static VolleyHelper getInstance() {
        if (mInstance == null) {
            synchronized (VolleyHelper.class) {
                if (mInstance == null) {
                    mInstance = new VolleyHelper();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        // use  custom okhttpStack, make better work .
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext(),
            new OkHttpStack(new OkHttpClient()));
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link com.android.volley.RequestQueue}
     */
    public RequestQueue getRequestQueue() {
        if (null != mRequestQueue) {
            return mRequestQueue;
        } else {
            throw new IllegalArgumentException("RequestQueue is not initialized.");
        }
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queue
     */
    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queuest
     * @param tag     is the tag identifying the request
     */
    public <T> void addRequest(Request<T> request, String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public void cancelAllRequests(String tag) {
        if (getRequestQueue() != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

    /**
     * 使用和参数配置范例
     *
     * @param param1        param1
     * @param param2        param2
     * @param listener      listener
     * @param errorListener errorListener
     */
    public void getDemoData(String param1,
                            String param2,
                            Response.Listener listener,
                            Response.ErrorListener errorListener, String tag) {
        Map<String, String> params = new HashMap<>();
        params.put("param1", param1);
        params.put("param2", param2);

        CustomRequest request = new CustomRequest.RequestBuilder()
            .post()//不设置的话默认GET 但是设置了参数就不需要了。。。
            .url("")//url会统一配置到requestUrl类中
            .addMethodParams("") //请求的方法名
                // 添加参数方法1 适用参数比较多的情况下
            .params(params)
                // 添加参数方法2
            .addParams("param1", param1)//添加参数1
            .addParams("param2", param2)//添加参数2
            .clazz(Test.class) //如果设置了返回类型，会自动解析返回model 如果不设置会直接返回json数据;
            .type(new TypeToken<List<Test>>() {
            }.getType()) //如果设置了返回类型，会自动解析返回model集合 如果不设置会直接返回json数据;
            .successListener(listener)//获取数据成功的listener
            .errorListener(errorListener)//获取数据异常的listener
            .build();
        //将请求add到队列中。并设置tag  并需要相应activity onStop方法中调用cancel方法
        addRequest(request, tag);
    }
}
