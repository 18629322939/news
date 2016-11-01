package com.zhuoxin.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.zhuoxin.news.R;

/**
 * Created by admin on 2016/10/27.
 */

public class LogActivity extends Activity {
    public static final String FILT_NAME = "config";
    public static final String IS_FIRST = "fist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 以键值对的形式存储数据
        SharedPreferences shar = this.getSharedPreferences("FILT_NAME", MODE_PRIVATE);
        // 默认第一次进来 因为第一次进入应用的时候 这个键不存在 所以得到默认值
        boolean flag = shar.getBoolean(IS_FIRST, true);
        if (flag) {
            // 第一次进来时引导界面
            Intent intent = new Intent(LogActivity.this, GuidActivity.class);
            startActivity(intent);
            // 编辑器对象
            SharedPreferences.Editor edit = shar.edit();
            // 修改数据 false代表不是第一次进来
            edit.putBoolean(IS_FIRST, false);
            // 提交数据
            edit.commit();
            // 结束当前界面
            this.finish();
        } else {

            setContentView(R.layout.activity_log);
            ImageView img = (ImageView) findViewById(R.id.img_log);
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF,
                    0.0F, Animation.RELATIVE_TO_SELF, 0.0F);
            translateAnimation.setDuration(2000);
            translateAnimation.setRepeatCount(2);
            translateAnimation.setRepeatMode(Animation.REVERSE);
            img.startAnimation(translateAnimation);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                    Intent intent = new Intent(LogActivity.this, MainActivity.class);
                    Log.e("---", "-----");
                    startActivity(intent);
                    LogActivity.this.finish();
                }
            });
        }


    }
}
