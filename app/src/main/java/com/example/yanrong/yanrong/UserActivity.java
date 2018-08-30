package com.example.yanrong.yanrong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.button_logout)
    Button buttonLogout;
    @BindView(R.id.textView_phone)
    TextView textViewPhone;
    @BindView(R.id.textView_username)
    TextView textViewUsername;
    @BindView(R.id.imageView)
    ImageView imageView;
    String handle;

    private Boolean isFirst = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        handle = preferences.getString("handle", "");
        String username = preferences.getString("username", "");
        textViewPhone.setText("18999997777");
        textViewUsername.setText(username);
        buttonLogout.setOnClickListener(mListener);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabAtPosition(3);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(isFirst.equals(true)){
//                    return;
                }
                if (tabId == R.id.tab_home) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(UserActivity.this,HomeActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_maps) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(UserActivity.this,HomeActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_alerm) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(UserActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_logout:                          //登出按钮
                    logout();
                    break;
            }
        }
    };

    public void logout() {
        //开始请求
        Request request = ItheimaHttp.newGetRequest("examples/yanrong/logout.php");//apiUrl格式："xxx/xxxxx"
        Map<String,Object> map = new HashMap<>();
        map.put("handle",handle);
        request.putParamsMap(map);
        Call call = ItheimaHttp.send(request, new HttpResponseListener<LoginBean>() {

            @Override
            public void onResponse(LoginBean bean, Headers headers) {
                System.out.println("print data");
                System.out.println("print data -- " +bean);
                if(bean.getRet().equals( "succ")){
                    SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
                    //获取editor对象
                    SharedPreferences.Editor editor = preferences.edit();
                    //存储数据时选用对应类型的方法
                    editor.putString("handle","");
                    editor.putString("username","");
                    //提交保存数据
                    editor.commit();
                    Intent i = new Intent(UserActivity.this,LoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(UserActivity.this, "登出失败了", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * 可以不重写失败回调
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                Toast.makeText(UserActivity.this, "登出失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
