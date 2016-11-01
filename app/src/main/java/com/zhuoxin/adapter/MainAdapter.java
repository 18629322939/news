package com.zhuoxin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/27.
 */

public class MainAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> mArrayList;

    public MainAdapter(ArrayList mArrayList, Context mContext) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(mArrayList.get(position));
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);

        return textView;
    }
}
