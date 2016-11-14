package com.zhuoxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuoxin.activity.WebActivity;
import com.zhuoxin.adapter.CenterAdapter;
import com.zhuoxin.entity.PersonInfo;
import com.zhuoxin.entity.Result;
import com.zhuoxin.news.R;
import com.zhuoxin.able.OnLoadBitmapLister;
import com.zhuoxin.utlis.Task;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;

/**
 * Created by admin on 2016/10/28.
 */

public class CenterFragment extends Fragment implements OnLoadBitmapLister, XListView.IXListViewListener, AdapterView.OnItemClickListener {

    public static final String PATH = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    XListView mXlst;
    static ArrayList<Result> mList = new ArrayList<>();
    Handler mHandler;
    CenterAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_maincenter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mXlst = (XListView) view.findViewById(R.id.xlst_maincenter);
        initData();



    }


    private void initData() {
        Task task = new Task();
        task.setListener(this);
        task.execute(PATH);


    }


    @Override
    public void onLoadBitmap(String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<PersonInfo>() {
        }.getType();
        PersonInfo personInfo = gson.fromJson(str, type);
        Log.e("--", "dui==" + personInfo.getMessage());
        Log.e("--", "dui==" + personInfo.getStatus());
        mList = personInfo.getData();
        mHandler = new Handler();
        mAdapter = new CenterAdapter(getContext(), mList);
        Log.e("--", "size==" + mList.size());
        mXlst.setAdapter(mAdapter);

        mXlst.setOnItemClickListener(this);
        mAdapter.notifyDataSetChanged();
//      必须设置两个方法
//       上拉刷新
        mXlst.setPullLoadEnable(true);
//      下拉加载
        mXlst.setPullRefreshEnable(true);
//      下拉和上拉需要设置监听，否则停不下来或者无法加载更多
        mXlst.setXListViewListener(this);

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
        mXlst.stopLoadMore();
        mXlst.stopRefresh();
//        设置时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXlst.setRefreshTime(format.format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("position", position-1);
        startActivity(intent);
    }

    public static ArrayList<Result> getList() {
        return mList;
    }
}
