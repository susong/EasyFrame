package com.dream.library.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/28 下午3:25
 * Description: 弱引用的handler
 */
public abstract class AbWeakReferenceHandler<T> extends Handler {
    private WeakReference<T> mReference;

    public AbWeakReferenceHandler(T reference) {
        mReference = new WeakReference<T>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mReference.get() == null)
            return;
        handleMessage(mReference.get(), msg);
    }

    protected abstract void handleMessage(T reference, Message msg);
}
