package com.example.yanrong.yanrong;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;

import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import org.itheima.recycler.adapter.BaseRecyclerAdapter;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;
import org.itheima.recycler.widget.PullToLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    PullToLoadMoreRecyclerView pullToLoadMoreRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout myswipeRefreshLayout;
    @BindView(R.id.recycler_view)
    ItheimaRecyclerView myrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pullToLoadMoreRecyclerView = new PullToLoadMoreRecyclerView<BannerBean>(myswipeRefreshLayout, myrecyclerView, MyRecyclerViewHolder.class) {
            @Override
            public int getItemResId() {
                //recylerview item资源id
                return R.layout.items_news;
            }

            @Override
            public String getApi() {
                //接口
                return "YanRong/?cmd=GetFqInfo&handle=1090027314";
            }
//            //是否加载更多的数据，根据业务逻辑自行判断，true表示有更多的数据，false表示没有更多的数据，如果不需要监听可以不重写该方法
//            @Override
//            public boolean isMoreData(BaseLoadMoreRecyclerAdapter.LoadMoreViewHolder holder) {
//                System.out.println("isMoreData" + holder);
//
//                return true;
//            }
        };

//        pullToLoadMoreRecyclerView.requestData();
        //开始请求
        Request request = ItheimaHttp.newGetRequest("YanRong/?cmd=GetFqInfo&handle=1090027314");//apiUrl格式："xxx/xxxxx"
        Call call = ItheimaHttp.send(request, new HttpResponseListener<AlermBean>() {

            @Override
            public void onResponse(AlermBean bean, Headers headers) {
                System.out.println("print data");
                System.out.println("print data -- " +bean);
                List<String> result = bean.getResult();
                int size = result.size();
                final BaseRecyclerAdapter[] adapter = new BaseRecyclerAdapter[size];
                for (int i = 0; i < size; i++) {
                    final int index = i;
                    String url =  result.get(index);
                    Request request = ItheimaHttp.newGetRequest(url);//apiUrl格式："xxx/xxxxx"
                    Call call = ItheimaHttp.send(request, new HttpResponseListener<String>() {

                        @Override
                        public void onResponse(String s, Headers headers) {
                            System.out.println("print data");
                            System.out.println("print data -- " +s);
                            List<AlermItemBean> list  = new ArrayList();

                            adapter[index] = new BaseRecyclerAdapter(myrecyclerView
                                    , MyRecyclerViewHolder.class
                                    , R.layout.items_news
                                    , list);
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
                    adapter[0] = new BaseRecyclerAdapter(myrecyclerView
                            , MyRecyclerViewHolder.class
                            , R.layout.items_news
                            , bean.getItemDatas());
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

    public static class MyRecyclerViewHolder extends BaseRecyclerViewHolder<BannerBean.ResultBean.ItemsBean> {
        //换成你布局文件中的id
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public MyRecyclerViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        /**
         * 绑定数据的方法，在mData获取数据（mData声明在基类中）
         */
        @Override
        public void onBindRealData() {
            tvTitle.setText(mData.getName());
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

