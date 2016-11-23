package com.zhuoxin.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuoxin.able.OnloadFavoriteListener;
import com.zhuoxin.adapter.FavoriteAdapter;
import com.zhuoxin.entity.Result;
import com.zhuoxin.news.R;
import com.zhuoxin.utlis.SqlUtils;

import java.util.ArrayList;

/**
 * Created by admin on 2016/11/4.
 */

public class FavoriteActivity extends AppCompatActivity {

    ArrayList<Result> mList;

    RecyclerView mRecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mRecycle = (RecyclerView) findViewById(R.id.recyle_favorite);
        mRecycle.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, true));
//        设置条目间隔
        mRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5, 10, 6, 2);
            }
        });
        initdata();

    }


    private void initdata() {
        SqlUtils sqlUtils = new SqlUtils(this);
        mList = sqlUtils.query();

        FavoriteAdapter adapter = new FavoriteAdapter(mList, this);
        mRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setData(new OnloadFavoriteListener() {
            @Override
            public void onLoadFavorite(int position) {
                Intent intent = new Intent(FavoriteActivity.this, WebFavoriteActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }


}
