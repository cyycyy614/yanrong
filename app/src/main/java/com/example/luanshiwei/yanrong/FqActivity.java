package com.example.luanshiwei.yanrong;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
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
                    row.setBackgroundResource(R.drawable.table_border);

                    // count the counter up by one
                    int counter = 0;
                    counter++;
                    ViewGroup.LayoutParams lp;

                    TextView t1 = new TextView(FqActivity.this);
                    row.addView(t1);
                    String id = item.getId().equals("null") ? "" : item.getId();
                    t1.setText(id+"");
                    t1.setGravity(Gravity.CENTER);
                    lp = t1.getLayoutParams();
                    lp.height = 70;
                    t1.setPadding(1,1,1,1);
                    margin(t1,1,1,1,1);
                    t1.setLayoutParams(lp);
                    t1.setBackgroundColor(Color.parseColor("#ffffff"));

                    TextView t2 = new TextView(FqActivity.this);
                    row.addView(t2);
                    String mapname = item.getMapName().equals("null") ? "" : item.getMapName();
                    t2.setText(mapname);
                    t2.setGravity(Gravity.CENTER);
                    lp = t2.getLayoutParams();
                    lp.height = 70;
                    t2.setPadding(1,1,1,1);
                    margin(t2,1,1,1,1);
                    t2.setLayoutParams(lp);
                    t2.setBackgroundColor(Color.parseColor("#ffffff"));

                    TextView t3 = new TextView(FqActivity.this);
                    row.addView(t3);
                    String fqname = item.getFqname().equals("null") ? "" : item.getFqname();
                    t3.setText(fqname);
                    t3.setGravity(Gravity.CENTER);
                    lp = t3.getLayoutParams();
                    lp.height = 70;
                    t3.setPadding(1,1,1,1);
                    margin(t3,1,1,1,1);
                    t3.setLayoutParams(lp);
                    t3.setBackgroundColor(Color.parseColor("#ffffff"));

                    TextView t4 = new TextView(FqActivity.this);
                    row.addView(t4);
                    String stat = item.getStatus().equals("null") ? "" : item.getStatus();
                    t4.setText(stat);
                    t4.setGravity(Gravity.CENTER);
                    lp = t4.getLayoutParams();
                    lp.height = 70;
                    t4.setPadding(1,1,1,1);
                    margin(t4,1,1,1,1);
                    t4.setLayoutParams(lp);
                    t4.setBackgroundColor(Color.parseColor("#ffffff"));

                    table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }

            public void margin(View v, int l, int t, int r, int b) {
                if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                    p.setMargins(l, t, r, b);
                    v.requestLayout();
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
