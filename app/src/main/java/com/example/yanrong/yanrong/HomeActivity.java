package com.example.yanrong.yanrong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

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
                    i = new Intent(HomeActivity.this,MainActivity.class);
                    startActivity(i);
                    break;
                case R.id.openMap:
                    i = new Intent(HomeActivity.this,MainActivity.class);
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
