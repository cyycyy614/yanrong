package com.example.luanshiwei.yanrong;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.itheima.retrofitutils.ItheimaHttp;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ItheimaHttp.init(this, "https://www.oschina.net/");
        ItheimaHttp.init(this, "http://jpush.joysw.com/");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        SDKInitializer.initialize(this);
    }
}
