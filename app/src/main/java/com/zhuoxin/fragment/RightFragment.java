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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by admin on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    TextView mTxt_right;
    TextView mWx;
    TextView mWb;
    TextView mPyx;
    TextView mQq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mainright, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTxt_right = (TextView) view.findViewById(R.id.txt_mainright_logo);
        mWb = (TextView) view.findViewById(R.id.txt_right_wb);
        mWx = (TextView) view.findViewById(R.id.txt_right_wx);
        mPyx = (TextView) view.findViewById(R.id.txt_right_pyx);
        mQq = (TextView) view.findViewById(R.id.txt_right_qq);
        mWx.setOnClickListener(this);
        mWb.setOnClickListener(this);
        mPyx.setOnClickListener(this);
        mQq.setOnClickListener(this);
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
            case R.id.txt_right_qq:
                //        初始化SDK
                ShareSDK.initSDK(getActivity());
//        实例化对象
                OnekeyShare qq = new OnekeyShare();
                //关闭sso授权
                qq.disableSSOWhenAuthorize();

                qq.setText("http://www.wowukaibufu.com");
                qq.setTitle("我尼古拉斯吴凯不服");


                //        开启分享
                qq.show(getActivity());
                break;
            case R.id.txt_right_wb:
                //        初始化SDK
                ShareSDK.initSDK(getActivity());
//        实例化对象
                OnekeyShare wb = new OnekeyShare();
                //关闭sso授权
                wb.disableSSOWhenAuthorize();

                wb.setText("http://www.baidu.com");
                wb.setTitle("我吴凯不服");

                //        开启分享
                wb.show(getActivity());
                break;
            case R.id.txt_right_wx:
                //        初始化SDK
                ShareSDK.initSDK(getActivity());
//        实例化对象
                OnekeyShare wx = new OnekeyShare();
                //关闭sso授权
                wx.disableSSOWhenAuthorize();

                wx.setText("http://www.baidu.com");
                wx.setTitle("我吴凯不服");

                //        开启分享
                wx.show(getActivity());
                break;
            case R.id.txt_right_pyx:
                //        初始化SDK
                ShareSDK.initSDK(getActivity());
//        实例化对象
                OnekeyShare pyx = new OnekeyShare();
                //关闭sso授权
                pyx.disableSSOWhenAuthorize();

                pyx.setText("http://www.baidu.com");
                pyx.setTitle("我吴凯不服");

                //        开启分享
                pyx.show(getActivity());
                break;


        }

    }


}
