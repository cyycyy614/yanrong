package com.example.luanshiwei.yanrong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isfirst =  SharedPreferencesUtil.getBoolean(LoginActivity.this, SharedPreferencesUtil.FIRST_OPEN, true);
        if(isfirst.equals(true)){
            Intent i = new Intent(LoginActivity.this,WelcomeGuideActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.et_userName);
        password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(mListener);

    }

    OnClickListener mListener = new OnClickListener() {
       //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:                          //登录界面的登录按钮
                    login();
                    break;
            }
        }
    };

    public void login() {                                              //登录按钮监听事件
        {
            String userName = username.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = password.getText().toString().trim();
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }


            //开始请求
//            Request request = ItheimaHttp.newGetRequest("action/apiv2/banner?catalog=1");//apiUrl格式："xxx/xxxxx"
            Request request = ItheimaHttp.newGetRequest("examples/yanrong/login.php");//apiUrl格式："xxx/xxxxx"
            Map<String,Object> map = new HashMap<>();
            map.put("pwd",userName);
            map.put("user",userPwd);
            request.putParamsMap(map);
            Call call = ItheimaHttp.send(request, new HttpResponseListener<LoginBean>() {

                @Override
                public void onResponse(LoginBean bean, Headers headers) {
                    System.out.println("print data");
                    System.out.println("print data -- " +bean);
                    if(bean.getRet().equals( "succ")){
                        int handle = bean.getHandle();
                        String username = bean.getUsername();

                        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
                        //获取editor对象
                        SharedPreferences.Editor editor = preferences.edit();
                        //存储数据时选用对应类型的方法
                        editor.putString("handle",handle+"");
                        editor.putString("username",username);
                        //提交保存数据
                        editor.commit();
                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败了", Toast.LENGTH_SHORT).show();
                    }
                }

                /**
                 * 可以不重写失败回调
                 * @param call
                 * @param e
                 */
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable e) {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
