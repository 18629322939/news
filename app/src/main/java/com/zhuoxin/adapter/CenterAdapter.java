package com.zhuoxin.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuoxin.entity.Result;
import com.zhuoxin.news.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/31.
 */

public class CenterAdapter extends BaseAdapter {
    ArrayList<Result> mList;
    Context mContext;
   CardView mCard;
//    int [] mColor={R.color.colorAccent,R.color.colorPrimary};

    public CenterAdapter(Context mContext, ArrayList<Result> mList) {
        this.mList = mList;
        this.mContext = mContext;
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
        Houdle houdle = null;
        if (convertView == null) {
            houdle = new Houdle();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_centeradapter, parent, false);
            houdle.img = (ImageView) convertView.findViewById(R.id.img_center_adapter_img);
            houdle.txt_one = (TextView) convertView.findViewById(R.id.txt_center_one);
            houdle.txt_two = (TextView) convertView.findViewById(R.id.txt_center_two);
            houdle.txt_three = (TextView) convertView.findViewById(R.id.txt_center_three);
            houdle.cardView = (CardView) convertView.findViewById(R.id.crd);
            convertView.setTag(houdle);
        } else {
            houdle = (Houdle) convertView.getTag();
        }
        Glide.with(mContext).load(mList.get(position).getIcon()).into(houdle.img);
        houdle.txt_one.setText(mList.get(position).getTitle());
        houdle.txt_two.setText(mList.get(position).getSummary());
        houdle.txt_three.setText(mList.get(position).getStamp());
//        houdle.cardView.setCardBackgroundColor(mColor[position%3]);
        Log.e("--", "size1==" + mList.size());

        return convertView;

    }

    static class Houdle {
        ImageView img;
        TextView txt_one;
        TextView txt_two;
        TextView txt_three;
        CardView cardView;
    }

}
