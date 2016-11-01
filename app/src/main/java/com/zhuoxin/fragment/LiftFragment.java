package com.zhuoxin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.news.R;

/**
 * Created by admin on 2016/10/28.
 */

public class LiftFragment extends Fragment {
    //    开始创建Fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        把XML文件返回给View
        return inflater.inflate(R.layout.activity_mainleft, container, false);

    }

    //这个方法表示fragment中的view正式被创建成功
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View viewById = view.findViewById(R.id.lst_mainleft_news);
    }

    //这是当前fragment所在的Activity正式被创建时的方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
