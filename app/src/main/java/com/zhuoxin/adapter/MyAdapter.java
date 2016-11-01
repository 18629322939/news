package com.zhuoxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */

public abstract class MyAdapter<T> extends BaseAdapter {
    ArrayList<T> mList;
    LayoutInflater mInflater;

    public MyAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<T> list) {
//        if (this.mList != null) {
////            如果不为空 清理在添加
//            this.mList.clear();
        this.mList.addAll(list);
//        }

    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return setView(position, convertView, parent);
    }

    public abstract View setView(int position, View convertView, ViewGroup parent);


}
