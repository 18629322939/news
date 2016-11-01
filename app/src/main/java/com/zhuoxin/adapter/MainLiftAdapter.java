package com.zhuoxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuoxin.entity.LiftInfo;
import com.zhuoxin.news.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/28.
 */

public class MainLiftAdapter extends BaseAdapter {
    Context mContext;
    String[] mCname = {"新闻", "收藏", "本地", "跟帖", "图片"};
    String[] mEname = {"NEWS", "FAVORITE", "LOCAL", "COMMENT", "PHOTO"};
    int[] mImg = {R.mipmap.biz_navigation_tab_news, R.mipmap.biz_navigation_tab_read, R.mipmap.biz_navigation_tab_local_news, R.mipmap.biz_navigation_tab_ties, R.mipmap.biz_navigation_tab_pics};

    ArrayList<LiftInfo> mList = new ArrayList<>();
    ;

    public MainLiftAdapter(Context mContext) {
        this.mContext = mContext;
        mList.clear();
        for (int i = 0; i < 5; i++) {
            mList.add(new LiftInfo(mCname[i], mEname[i]));
        }
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Handle handle = null;
        if (null == convertView) {
            handle = new Handle();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lift_adapter, parent, false);
            handle.img = (ImageView) convertView.findViewById(R.id.img_lift_adapter);
            handle.txt_c = (TextView) convertView.findViewById(R.id.txt_list_adapter_cname);
            handle.txt_e = (TextView) convertView.findViewById(R.id.txt_list_adapter_ename);
            convertView.setTag(handle);
        } else {
            handle = (Handle) convertView.getTag();
        }
        handle.img.setImageResource(mImg[position]);
        handle.txt_e.setText(mList.get(position).getEname());
        handle.txt_c.setText(mList.get(position).getCname());

        return convertView;
    }

    static class Handle {
        TextView txt_c;
        TextView txt_e;
        ImageView img;
    }
}
