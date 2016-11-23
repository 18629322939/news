package com.zhuoxin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhuoxin.able.OnLoadResponseListener;
import com.zhuoxin.entity.Result;
import com.zhuoxin.fragment.CenterFragment;
import com.zhuoxin.httpinfo.HttpInfo;
import com.zhuoxin.news.R;
import com.zhuoxin.utlis.HttpUtils;
import com.zhuoxin.utlis.SqlUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by admin on 2016/11/1.
 */

public class WebActivity extends AppCompatActivity implements View.OnClickListener, OnLoadResponseListener {
    WebView mWeb;
    ArrayList<Result> mList = new ArrayList<>();
    int mPosition;
    ImageView mImg_popu;
    TextView mTxt_back;
    Button mBtn_gt;
    PopupWindow mPopupWindow;
    TextView mFavorite;
    TextView mShare;
    RequestQueue mQueue;
    HttpUtils mHttpUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mHttpUtils = new HttpUtils();
        mQueue = Volley.newRequestQueue(this);
        mHttpUtils.conectionGet(HttpInfo.BASE_URI + HttpInfo.NUM + "ver=1&nid=" + mList.get(mPosition).getNid(), this, mQueue
        );
        mImg_popu = (ImageView) findViewById(R.id.img_web);
        mTxt_back = (TextView) findViewById(R.id.txt_web_back);
        mBtn_gt = (Button) findViewById(R.id.btn_web);
        mImg_popu.setOnClickListener(this);
        mBtn_gt.setOnClickListener(this);
        mTxt_back.setOnClickListener(this);
        mPopupWindow = new PopupWindow();
//        设置VIEW
        View inflate = this.getLayoutInflater().inflate(R.layout.activity_web_popu, null, false);
        mPopupWindow.setContentView(inflate);
        mFavorite = (TextView) inflate.findViewById(R.id.txt_web_popu);
        mShare = (TextView) inflate.findViewById(R.id.txt_web_popu_share);
        mShare.setOnClickListener(this);
        mFavorite.setOnClickListener(this);
        //        设置宽高
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPopupWindow.setFocusable(true);

        //设置点击取消
        mPopupWindow.setOutsideTouchable(true);
//     设置背景，没有背景取消不掉   new BitmapDrawable()空白背景
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mWeb = (WebView) findViewById(R.id.web);
        mPosition = this.getIntent().getIntExtra("position", -1);
        mList = CenterFragment.getList();
        Log.e("===", "--------" + mList.size());
//        加载网页
        mWeb.loadUrl(mList.get(mPosition).getLink());
//        mWeb.loadUrl("https://www.baidu.com");
//        设置手机客户端显示样式
        WebSettings settings = mWeb.getSettings();
//        使用网页中js代码自动识别是手机端还是网页端
        settings.getJavaScriptEnabled();
//        设置自适应屏幕  通过布局参数设置
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        设置webview推荐使用的窗口
        settings.setUseWideViewPort(true);
//        设置自适应窗口
        settings.setLoadWithOverviewMode(true);
//        设置 是否能放大缩小
        settings.setSupportZoom(true);
//        设置放大缩小的按钮显示
        settings.setBuiltInZoomControls(true);
//        设置调整窗口
        settings.setSupportMultipleWindows(true);
//        设置显示控制器
        settings.setDisplayZoomControls(true);
//        设置自己的客户端方式
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_web_back:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("position", mPosition);
                intent.putExtra("bundle", bundle);
                WebActivity.this.setResult(-1, intent);
                finish();
                break;
            case R.id.img_web:
                mPopupWindow.showAsDropDown(v);
                break;
            case R.id.txt_web_popu_share:
                //        初始化SDK
                ShareSDK.initSDK(this);
//        实例化对象
                OnekeyShare qq = new OnekeyShare();
                //关闭sso授权
                qq.disableSSOWhenAuthorize();

                qq.setText(mList.get(mPosition).getTitle());
                qq.setTitle(mList.get(mPosition).getSummary());
                qq.setImageUrl(mList.get(mPosition).getIcon());
                qq.setTitleUrl(mList.get(mPosition).getLink());


                //        开启分享
                qq.show(this);
                break;
            case R.id.txt_web_popu:

                SqlUtils sqlUtils = new SqlUtils(this);

                String summary = mList.get(mPosition).getSummary();
                String icon = mList.get(mPosition).getIcon();
                String stamp = mList.get(mPosition).getStamp();
                String title = mList.get(mPosition).getTitle();
                String nid = mList.get(mPosition).getNid();
                String link = mList.get(mPosition).getLink();
                String type = mList.get(mPosition).getType();
                for (int i = 0; i < sqlUtils.query().size(); i++) {
                    if (nid.equals(sqlUtils.query().get(i).getNid())) {
                        Toast.makeText(this, "已有收藏", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                sqlUtils.insert(summary, icon, stamp, title, nid, link, type);
                Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();


                break;

            case R.id.btn_web:
                Intent comment = new Intent(WebActivity.this, CommentActivity.class);
                comment.putExtra("nid", mList.get(mPosition).getNid());
                startActivity(comment);
                break;

        }


    }

    @Override
    public void getResponse(String message) {
        Log.e("---", "message" + message);

        try {
            SharedPreferences number = this.getSharedPreferences("number", MODE_PRIVATE);
            SharedPreferences.Editor editor = number.edit();
            JSONObject jsonObject = new JSONObject(message);
            String s = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            int data = jsonObject.getInt("data");
            editor.putInt("data", data);
            editor.commit();
            mBtn_gt.setText(data + "跟帖");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

//    //重写系统返回键
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            mWeb.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }
}
