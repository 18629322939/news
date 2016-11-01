package com.zhuoxin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.zhuoxin.adapter.GuidAdapter;
import com.zhuoxin.news.R;

/**
 * Created by admin on 2016/10/27.
 */

public class GuidActivity extends Activity {
    ViewPager mVp;
    //    四个点
    ImageView[] mImg;
    //    图片
    ImageView[] mRes;
    //    图片ID
    int[] mId = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        initview();

        initAdapter();
    }

    private void initAdapter() {
        GuidAdapter adapter = new GuidAdapter(mRes);
        mVp.setAdapter(adapter);
//        刷新适配器
        adapter.notifyDataSetChanged();
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {

                if (position == 3) {

                    Intent intent = new Intent(GuidActivity.this, LogActivity.class);
                    startActivity(intent);
                    Log.e("---", "------");
                    GuidActivity.this.finish();
                }
                for (int i = 0; i < mImg.length; i++) {
                    mImg[i].setImageResource(R.mipmap.adware_style_default);
                }
                mImg[position].setImageResource(R.mipmap.adware_style_selected);
                Log.e("onPageSelected", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void initview() {
        mVp = (ViewPager) findViewById(R.id.vp_guid);
        mImg = new ImageView[4];
        mRes = new ImageView[4];
        mImg[0] = (ImageView) findViewById(R.id.img_guid_1);
        mImg[1] = (ImageView) findViewById(R.id.img_guid_2);
        mImg[2] = (ImageView) findViewById(R.id.img_guid_3);
        mImg[3] = (ImageView) findViewById(R.id.img_guid_4);
        // 因为viewpager 默认显示第0位的资源 所以 初始化第0位 点为绿色
        mImg[0].setImageResource(R.mipmap.adware_style_selected);
        for (int i = 0; i < 4; i++) {
            mRes[i] = new ImageView(this);
            mRes[i].setImageResource(mId[i]);
        }
    }
}
