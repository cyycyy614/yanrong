package com.example.yanrong.yanrong;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import org.itheima.recycler.adapter.BaseRecyclerAdapter;

import java.util.List;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class FqActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fq);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String handle = preferences.getString("handle", "");

        //开始请求
        Request request = ItheimaHttp.newGetRequest("examples/yanrong/getFqlog.php?handle="+handle);//apiUrl格式："xxx/xxxxx"
        Call call = ItheimaHttp.send(request, new HttpResponseListener<FqItemBean>() {

            @Override
            public void onResponse(FqItemBean bean, Headers headers) {
                System.out.println("print data");
                System.out.println("print data -- " +bean);

               // get a reference for the TableLayout
                TableLayout table = (TableLayout) findViewById(R.id.fq_table);

                List<FqItemBean.ResultBean.ItemsBean> itemDatas = bean.getItemDatas();
                for(FqItemBean.ResultBean.ItemsBean item : itemDatas){
                    // create a new TableRow
                    TableRow row = new TableRow(FqActivity.this);
                    row.setDividerDrawable(getResources().getDrawable(R.drawable.line_h));
                    row.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    row.setShowDividers();
                    // count the counter up by one
                    int counter = 0;
                    counter++;
                    ViewGroup.LayoutParams lp;

                    TextView t1 = new TextView(FqActivity.this);
                    row.addView(t1);
                    t1.setText(item.getId()+"");
                    t1.setGravity(Gravity.CENTER);
                    lp = t1.getLayoutParams();
                    lp.height = 70;
                    t1.setLayoutParams(lp);

                    TextView t2 = new TextView(FqActivity.this);
                    row.addView(t2);
                    t2.setText(item.getMapid() + "");
                    t2.setGravity(Gravity.CENTER);
                    lp = t2.getLayoutParams();
                    lp.height = 70;
                    t2.setLayoutParams(lp);

                    TextView t3 = new TextView(FqActivity.this);
                    row.addView(t3);
                    t3.setText(item.getFqname());
                    t3.setGravity(Gravity.CENTER);
                    lp = t3.getLayoutParams();
                    lp.height = 70;
                    t3.setLayoutParams(lp);

                    TextView t4 = new TextView(FqActivity.this);
                    row.addView(t4);
                    t4.setText(item.getStatus());
                    t4.setGravity(Gravity.CENTER);
                    lp = t4.getLayoutParams();
                    lp.height = 70;
                    t4.setLayoutParams(lp);

                    table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }

            /**
             * 可以不重写失败回调
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                System.out.println("print data fail");
            }
        });
    }
}
