package com.example.yanrong.yanrong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.retrofitutils.L;

import org.itheima.recycler.adapter.BaseLoadMoreRecyclerAdapter;
import org.itheima.recycler.header.RecyclerViewHeader;
import org.itheima.recycler.listener.ItemClickSupport;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;
import org.itheima.recycler.widget.PullToLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    PullToLoadMoreRecyclerView pullToLoadMoreRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout myswipeRefreshLayout;
    private ItheimaRecyclerView myrecyclerView;
    String handle;
    Integer pageIndex = 0;
    private int state = 0;
    private static final int STATE_FRESH = 1;
    private static final int STATE_MORE = 2;
    private  int pageindex = 0;
    ArrayList<AlermBean.ResultBean.ItemsBean> itemsBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.recycler_header);
        myrecyclerView = (ItheimaRecyclerView) findViewById(R.id.recycler_view);
        header.attachTo(myrecyclerView);

        ItemClickSupport itemClickSupport = new ItemClickSupport(myrecyclerView);
        //点击事件
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String mapname =  itemsBeanList.get(position).getMapName();
                String fqname =  itemsBeanList.get(position).getFQName();
                String eventtype =  itemsBeanList.get(position).getEventType();
                Intent intent = new Intent(MainActivity.this,AlermDetailActivity.class);
                intent.putExtra("mapname",mapname);
                intent.putExtra("fqname",fqname);
                intent.putExtra("eventtype",eventtype);
                startActivity(intent);
//                Toast.makeText(recyclerView.getContext(), "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        handle = preferences.getString("handle", "");
        pullToLoadMoreRecyclerView = new PullToLoadMoreRecyclerView<AlermBean>(myswipeRefreshLayout, myrecyclerView, MyRecyclerViewHolder.class) {
            @Override
            public int getItemResId() {
                //recylerview item资源id
                return R.layout.items_news;
            }

            @Override
            public String getApi() {
                switch (state){
                    case STATE_FRESH:
                        pageIndex = 0;
                        break;
                    case STATE_MORE:
                        pageIndex++;
                        break;
                }
                //接口
                return "examples/yanrong/GetAlarmlog.php?startrow=" + pageIndex + "&handle=" + handle;
            }
//            //是否加载更多的数据，根据业务逻辑自行判断，true表示有更多的数据，false表示没有更多的数据，如果不需要监听可以不重写该方法
            @Override
            public boolean isMoreData(BaseLoadMoreRecyclerAdapter.LoadMoreViewHolder holder) {
                System.out.println("isMoreData" + holder);
                state = STATE_MORE;
                return true;
            }
        };

        pullToLoadMoreRecyclerView.setLoadingDataListener(new PullToLoadMoreRecyclerView.LoadingDataListener<AlermBean>() {

            @Override
            public void onRefresh() {
                //监听下啦刷新，如果不需要监听可以不重新该方法
                L.i("setLoadingDataListener onRefresh");
                state = STATE_FRESH;
            }

            @Override
            public void onStart() {
                //监听http请求开始，如果不需要监听可以不重新该方法
                L.i("setLoadingDataListener onStart");
            }

            @Override
            public void onSuccess(AlermBean o , Headers headers) {
                //监听http请求成功，如果不需要监听可以不重新该方法
                L.i("setLoadingDataListener onSuccess: " + o);
                List<AlermBean.ResultBean.ItemsBean> itemDatas = o.getItemDatas();
                for(AlermBean.ResultBean.ItemsBean item : itemDatas){
                    itemsBeanList.add(item);
                }
            }

            @Override
            public void onFailure() {
                //监听http请求失败，如果不需要监听可以不重新该方法
                L.i("setLoadingDataListener onFailure");
            }
        });

        pullToLoadMoreRecyclerView.requestData();
        //开始请求
//        Request request = ItheimaHttp.newGetRequest("YanRong/?cmd=GetAlarmlog&startrow=0&&handle="+handle);//apiUrl格式："xxx/xxxxx"
//        Call call = ItheimaHttp.send(request, new HttpResponseListener<AlermBean>() {
//
//            @Override
//            public void onResponse(AlermBean bean, Headers headers) {
//                System.out.println("print data");
//                System.out.println("print data -- " +bean);
//                String result = bean.getResult();
//
//                final BaseRecyclerAdapter[] adapter = new BaseRecyclerAdapter[1];
//                {
//                    Request request = ItheimaHttp.newGetRequest(result);//apiUrl格式："xxx/xxxxx"
//                    Call call = ItheimaHttp.send(request, new HttpResponseListener<String>() {
//
//                        @Override
//                        public void onResponse(String s, Headers headers) {
//                            System.out.println("print data");
//                            System.out.println("print log data -- " +s);
//                            List<FqItemBean> list  = new ArrayList();
//
//                            adapter[0] = new BaseRecyclerAdapter(myrecyclerView
//                                    , MyRecyclerViewHolder.class
//                                    , R.layout.items_news
//                                    , list);
//                        }
//
//
//                        /**
//                         * 可以不重写失败回调
//                         * @param call
//                         * @param e
//                         */
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable e) {
//                            System.out.println("print data fail");
//                        }
//                    });
//                    adapter[0] = new BaseRecyclerAdapter(myrecyclerView
//                            , MyRecyclerViewHolder.class
//                            , R.layout.items_news
//                            , bean.getItemDatas());
//                }
//            }
//
//            /**
//             * 可以不重写失败回调
//             * @param call
//             * @param e
//             */
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable e) {
//                System.out.println("print data fail");
//            }
//        });
    }

    public static class MyRecyclerViewHolder extends BaseRecyclerViewHolder<AlermBean.ResultBean.ItemsBean> {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_handle)
        TextView tvHandle;

        //换成你布局文件中的id
        public MyRecyclerViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        /**
         * 绑定数据的方法，在mData获取数据（mData声明在基类中）
         */
        @Override
        public void onBindRealData() {
            String eventtype = (mData.getEventType().equals("") || mData.getEventType().equals("null")) ? "" :("【" + mData.getEventType() +"】");
            String fqname = (mData.getFQName().equals("") || mData.getFQName().equals("null")) ? "" : mData.getFQName();
            String mapname = (mData.getMapName().equals("") || mData.getMapName().equals("null")) ? "" : mData.getMapName();
            String eventtime = (mData.getEventTime().equals("") || mData.getEventTime().equals("null")) ? "" : mData.getEventTime();

            tvTitle.setText( eventtype + mapname  + fqname);
            tvDate.setText(eventtime);
            tvHandle.setText((mData.getConfirmType().equals("") || mData.getConfirmType().equals("null")
                    || mData.getConfirmType().equals("0"))?"已处理" : mData.getConfirmType());
        }


        /**
         * 给按钮添加点击事件（button改成你要添加点击事件的id）
         * @param v
         */
//        @OnClick(R.id.button)
//        public void click(View v) {
//        }
    }
}

