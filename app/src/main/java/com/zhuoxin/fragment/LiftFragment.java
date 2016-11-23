package com.zhuoxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhuoxin.activity.CameraActivity;
import com.zhuoxin.activity.FavoriteActivity;
import com.zhuoxin.activity.MainActivity;
import com.zhuoxin.news.R;

/**
 * Created by admin on 2016/10/28.
 */

public class LiftFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView mLst;

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
        mLst = (ListView) view.findViewById(R.id.lst_mainleft_news);
        mLst.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                //        获取业务处理器
                FragmentTransaction center = getActivity().getSupportFragmentManager().beginTransaction();
                center.replace(R.id.fragmlayout, new CenterFragment());
//        提交后才能实现
                center.commit();
                MainActivity.close();

                break;
            case 1:
                Intent favorite = new Intent(getActivity(), FavoriteActivity.class);
                startActivity(favorite);
                break;

            case 2:
                Intent camera = new Intent(getActivity(), CameraActivity.class);
                startActivity(camera);
                break;
        }
    }
}
