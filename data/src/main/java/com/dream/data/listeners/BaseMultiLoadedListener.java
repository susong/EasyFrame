package com.dream.data.listeners;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/12 下午3:29
 * Description: EasyFrame
 */
public interface BaseMultiLoadedListener<T> {

    /**
     * when data call back success
     *
     * @param event_tag
     * @param data
     */
    void onSuccess(int event_tag, T data);

    /**
     * when data call back error
     *
     * @param msg
     */
    void onError(String msg);

    /**
     * when data call back occurred exception
     *
     * @param msg
     */
    void onException(String msg);
}
