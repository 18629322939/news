package com.zhuoxin.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhuoxin.adapter.MainLiftAdapter;
import com.zhuoxin.news.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView mLst_Left;
    public static DrawerLayout mDrawerLayout;
    TextView mTxt_home;
    TextView mTxt_shar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();

    }

    private void initAdapter() {
        MainLiftAdapter mainLiftAdapter = new MainLiftAdapter(this);
        mLst_Left.setAdapter(mainLiftAdapter);


    }

    private void initView() {
        mLst_Left = (ListView) findViewById(R.id.lst_mainleft_news);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTxt_home = (TextView) findViewById(R.id.txt_maincenter_home);
        mTxt_shar = (TextView) findViewById(R.id.txt_maincenter_share);
        mTxt_home.setOnClickListener(this);
        mTxt_shar.setOnClickListener(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_maincenter_home:
                if ((!mDrawerLayout.isDrawerOpen(Gravity.RIGHT))) {
                    if ((mDrawerLayout.isDrawerOpen(Gravity.LEFT))) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                    }
                }
                break;
            case R.id.txt_maincenter_share:
                if ((!mDrawerLayout.isDrawerOpen(Gravity.LEFT))) {
                    if ((mDrawerLayout.isDrawerOpen(Gravity.RIGHT))) {
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
                break;
        }
    }


    public static void close() {

        mDrawerLayout.closeDrawer(Gravity.RIGHT);
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }
}
