package com.dream.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected static final String TAG = CommonAdapter.class.getSimpleName();

    protected final Context mContext;
    private final int layoutId;
    protected List<T> mList;

    public CommonAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.layoutId = layoutId;
        this.mList = new ArrayList<>();
    }

    public CommonAdapter(Context context, List<T> list, int layoutId) {
        this.mContext = context;
        this.layoutId = layoutId;
        if (list == null) {
            this.mList = new ArrayList<>();
        } else {
            this.mList = list;
        }
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonAdapterHelper helper = CommonAdapterHelper.get(mContext, convertView, parent, layoutId, position);
        convert(helper, getItem(position));
        return helper.getConvertView();
    }

    public abstract void convert(CommonAdapterHelper helper, T t);

    // 对mList集合进行操作

    public List<T> getList() {
        return mList;
    }

    public void add(T elem) {
        mList.add(elem);
        notifyDataSetChanged();
    }

    public void add(int location, T elem) {
        mList.add(location, elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elemList) {
        mList.addAll(elemList);
        notifyDataSetChanged();
    }

    public void addAll(int location, List<T> elemList) {
        mList.addAll(location, elemList);
        notifyDataSetChanged();
    }

    public void set(int location, T elem) {
        mList.set(location, elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(mList.indexOf(oldElem), newElem);
    }

    public void remove(T elem) {
        mList.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int location) {
        mList.remove(location);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return mList.contains(elem);
    }

    public void replaceAll(List<T> elemList) {
        mList.clear();
        mList.addAll(elemList);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
