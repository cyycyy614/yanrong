package com.example.luanshiwei.yanrong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import cn.jpush.android.api.JPushInterface;

public class HomeActivity extends AppCompatActivity {
    private ImageView openalerm;
    private ImageView opendevice;
    private ImageView openmap;
    private ImageView openlog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        openalerm = (ImageView) findViewById(R.id.openAlerm);
        opendevice = (ImageView) findViewById(R.id.openDevice);
        openmap = (ImageView) findViewById(R.id.openMap);
        openlog = (ImageView) findViewById(R.id.openLog);
        openalerm.setOnClickListener(mListener);
        opendevice.setOnClickListener(mListener);
        openmap.setOnClickListener(mListener);
        openlog.setOnClickListener(mListener);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabAtPosition(0);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_home) {
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
//                    startActivity(i);
//                } else
                if (tabId == R.id.tab_maps) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(HomeActivity.this,MapActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_alerm) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_user) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(HomeActivity.this,UserActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        //不同按钮按下的监听事件选择
        Intent i ;
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.openAlerm:
                    i = new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(i);
                    break;
                case R.id.openDevice:
                    i = new Intent(HomeActivity.this,FqActivity.class);
                    startActivity(i);
                    break;
                case R.id.openMap:
                    i = new Intent(HomeActivity.this,MapActivity.class);
                    startActivity(i);
                    break;
                case R.id.openLog:
                    i = new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
}
