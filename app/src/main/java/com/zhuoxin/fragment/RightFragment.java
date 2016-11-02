package com.zhuoxin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuoxin.activity.MainActivity;
import com.zhuoxin.news.R;

/**
 * Created by admin on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    TextView mTxt_right;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mainright, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTxt_right = (TextView) view.findViewById(R.id.txt_mainright_logo);
        CenterFragment centerFragment = new CenterFragment();
        //        获取业务处理器
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmlayout, centerFragment);
        fragmentTransaction.commit();
        mTxt_right.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        LogoFragment logoFragment = new LogoFragment();

        switch (v.getId()) {
            case R.id.txt_mainright_logo:
                //             获取业务处理器
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.fragmlayout, logoFragment);
//              提交后才能实现
                transaction1.commit();
                MainActivity.close();
                break;


        }

    }


}
