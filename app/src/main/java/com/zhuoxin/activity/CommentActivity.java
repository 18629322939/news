package com.zhuoxin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuoxin.able.OnLoadResponseListener;
import com.zhuoxin.adapter.CommentAdapter;
import com.zhuoxin.entity.CommentPerson;
import com.zhuoxin.entity.CommentResult;
import com.zhuoxin.httpinfo.HttpInfo;
import com.zhuoxin.news.R;
import com.zhuoxin.utlis.HttpUtils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;

/**
 * Created by admin on 2016/11/17.
 */

public class CommentActivity extends AppCompatActivity implements View.OnClickListener, XListView.IXListViewListener, OnLoadResponseListener, AdapterView.OnItemClickListener {
    XListView mComment;
    TextView mBack;
    Handler mHandler;
    RequestQueue mRequestQueue;
    String mNid;
    ArrayList<CommentPerson> mList;
    HttpUtils mHttpUtils;
    int mCid;
    CommentAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Intent intent = this.getIntent();
        mRequestQueue = Volley.newRequestQueue(this);
        mNid = intent.getStringExtra("nid");
        Log.e("===", "nid===" + mNid);
        SharedPreferences number = this.getSharedPreferences("number", MODE_APPEND);
        mCid = number.getInt("data", 0);
        Log.e("===", "cid===" + mCid);

        mComment = (XListView) findViewById(R.id.xlst_comment);
        mBack = (TextView) findViewById(R.id.txt_comment_back);

        mBack.setOnClickListener(this);
        

        mHandler = new Handler();
        //      必须设置两个方法
//       上拉刷新
        mComment.setPullLoadEnable(true);
//      下拉加载
        mComment.setPullRefreshEnable(true);
//      下拉和上拉需要设置监听，否则停不下来或者无法加载更多
        mComment.setXListViewListener(this);

        mHttpUtils = new HttpUtils();
        mHttpUtils.conectionGet(HttpInfo.BASE_URI + HttpInfo.SHOW + "ver=1&nid=" + mNid + "&type=1&stamp=yyyyMMdd&cid=" + mCid + "&dir=1&cnt=20", this, mRequestQueue);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_comment_back:
                finish();
                break;
        }
    }

    @Override
//    下拉刷新
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    @Override
//    上拉加载更多
    public void onLoadMore() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    public void stop() {
//        停止加载 停止刷新
        mComment.stopLoadMore();
        mComment.stopRefresh();
//        设置时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mComment.setRefreshTime(format.format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void getResponse(String message) {
        Log.e("---", "message" + message);
        Gson gson = new Gson();
        Type type = new TypeToken<CommentResult>() {
        }.getType();
        CommentResult result = gson.fromJson(message, type);
        mList = (ArrayList<CommentPerson>) result.getData();
        Log.e("list123", "list========" + mList);
        //适配器0
        mAdapter = new CommentAdapter(mList, this);
        mComment.setAdapter(mAdapter);
        //加载组件
        mComment.setOnItemClickListener(this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
