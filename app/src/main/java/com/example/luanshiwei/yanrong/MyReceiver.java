package com.example.luanshiwei.yanrong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

import cn.jpush.android.api.JPushInterface;



public class MyReceiver  extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";
    private static SharedPreferences preferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        preferences = context.getSharedPreferences("login", context.MODE_PRIVATE);
        try {
            Bundle bundle = intent.getExtras();
            System.out.println( "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                System.out.println( "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                System.out.println( "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                System.out.println( "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                System.out.println( "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    System.out.println("This message has no Extra data");
//                    Logger.i(TAG, "This message has no Extra data");
                    return;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        if(myKey.equals("data")){
                            System.out.println("开始写入本地存储------------------------------");
                            JSONObject alertobj = json.getJSONObject("data");
                            String fqype = "";
                            String eventtype =  "";
                            try{
                                fqype = alertobj.getString("fqtype");
                            } catch (Exception e){

                            }
                            try{
                                eventtype = alertobj.getString("eventtype");
                            } catch (Exception e){

                            }

                            String zhangli = preferences.getString("zhangli", "0");
                            String maichong = preferences.getString("maichong", "0");
                            String yuejie = preferences.getString("yuejie", "0");
                            String ruqin = preferences.getString("ruqin", "0");
                            String yichang = preferences.getString("yichang", "0");

                            if(fqype.indexOf("张力") > -1){
                                zhangli = (Integer.parseInt(zhangli) + 1) + "";
                            } else if(fqype.indexOf("脉冲") > -1){
                                maichong = (Integer.parseInt(maichong) + 1) + "";
                            }

                            ArrayList yichanglist = new ArrayList();
                            yichanglist.add("电源故障");
                            yichanglist.add("通讯故障");
                            yichanglist.add("防拆报警");

                            ArrayList ruqinlist = new ArrayList();
                            ruqinlist.add("电源故障");
                            ruqinlist.add("通讯故障");
                            ruqinlist.add("防拆报警");

                            if(Arrays.asList(yichanglist.contains(eventtype)).equals(true)){
                                yichang = (Integer.parseInt(yichang) + 1) + "";
                            } else if(Arrays.asList(ruqinlist.contains(eventtype)).equals(true)){
                                ruqin = (Integer.parseInt(ruqin) + 1) + "";
                            } else {
                                yuejie = (Integer.parseInt(yuejie) + 1) + "";
                            }
                            SharedPreferences.Editor editor = preferences.edit();
                            //存储数据时选用对应类型的方法
                            editor.putString("yichang",yichang);
                            editor.putString("ruqin",ruqin);
                            editor.putString("yuejie",yuejie);
                            editor.putString("zhangli",zhangli);
                            editor.putString("maichong",maichong);
                            //提交保存数据
                            editor.commit();
                        }
                    }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                System.out.println( "[MyReceiver] 用户点击打开了通知");

                //打开自定义的Activity
                Intent i = new Intent(context, TestActivity.class);
                i.putExtras(bundle);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                System.out.println( "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                System.out.println( "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
            } else {
                System.out.println("[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e){

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    System.out.println("This message has no Extra data");
//                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }



                } catch (JSONException e) {
//                    Logger.e(TAG, "Get message extra JSON error!");
                    System.out.println("Get message extra JSON error!" );
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
//        if (MainActivity.isForeground) {
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }
    }
}
