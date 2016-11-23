package com.zhuoxin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhuoxin.news.R;
import com.zhuoxin.utlis.SqlUtils;

/**
 * Created by admin on 2016/11/11.
 */

public class WebFavoriteActivity extends AppCompatActivity {
    WebView mWeb;
    int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_web);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mWeb = (WebView) findViewById(R.id.web_fragment_web);
        SqlUtils sqlUtils = new SqlUtils(this);
        mPosition = this.getIntent().getIntExtra("position", -1);

//        加载网页
        mWeb.loadUrl(sqlUtils.query().get(mPosition).getLink());

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


}
