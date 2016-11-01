package com.zhuoxin.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by admin on 2016/10/27.
 */

public class GuidAdapter extends PagerAdapter {
    ImageView[] mRes;

    public GuidAdapter(ImageView[] mRes) {
        this.mRes = mRes;
    }

    @Override
    public int getCount() {
        return mRes == null ? 0 : mRes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // 向container 中添加数据 用于在 viewPager 中显示
        ImageView img;
        img = mRes[position];
        container.addView(img);
        Log.e("instantiateItem", "position=" + position);

        return img;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 移除看不到View
        container.removeView(mRes[position]);
        Log.e("destroyItem", "position=" + position);


    }
}
