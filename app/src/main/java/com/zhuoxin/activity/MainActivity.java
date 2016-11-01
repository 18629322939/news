package com.zhuoxin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zhuoxin.adapter.MainLiftAdapter;
import com.zhuoxin.news.R;

public class MainActivity extends AppCompatActivity {

    ListView mLst_Left;


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


    }
}
