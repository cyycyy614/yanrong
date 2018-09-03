package com.example.luanshiwei.yanrong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MapActivity extends AppCompatActivity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private static final int BAIDU_READ_PHONE_STATE =100;
    @BindView(R.id.first)
    TextView first;
    BaiduMap mBaiduMap;
    MapView mMapView;
    boolean isFirstLoc = true;
    List<MapBean.ResultBean.ItemsBean> mDatas ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

//        if(MapActivity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
//            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
//            requestPermissions( new String[]{ Manifest.permission.READ_PHONE_STATE },BAIDU_READ_PHONE_STATE );
//        }

        startLocate();
        initData();

        // 点击Marker事件响应
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                String mapid = bundle.getString("mapid");
                Toast.makeText(MapActivity.this, "获取地图信息id:" + mapid, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabAtPosition(1);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(MapActivity.this,HomeActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_alerm) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(MapActivity.this,MainActivity.class);
                    startActivity(i);
                } else if (tabId == R.id.tab_user) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Intent i = new Intent(MapActivity.this,UserActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    protected void  initData(){
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String handle = preferences.getString("handle", "");
        //开始请求
//            Request request = ItheimaHttp.newGetRequest("action/apiv2/banner?catalog=1");//apiUrl格式："xxx/xxxxx"
        Request request = ItheimaHttp.newGetRequest("examples/yanrong/getMapinfo.php?handle="+handle);//apiUrl格式："xxx/xxxxx"
        Call call = ItheimaHttp.send(request, new HttpResponseListener<MapBean>() {

            @Override
            public void onResponse(MapBean bean, Headers headers) {
                System.out.println("print data");
                System.out.println("print data -- " +bean);
                if(bean.getSuccess() == 1){
                    mDatas = bean.getItemDatas();
                    generateMarker();
                } else {
                    Toast.makeText(MapActivity.this, "获取地图信息失败了", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * 可以不重写失败回调
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                Toast.makeText(MapActivity.this, "获取地图信息失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mBaiduMap.clear();
        mMapView.onDestroy();
        mMapView = null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        switch(requestCode) {
            //requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case 1:
                BAIDU_READ_PHONE_STATE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取到权限，做相应处理
                    //调用定位SDK应确保相关权限均被授权，否则会引起定位失败
                } else{
                    //没有获取到权限，做特殊处理
                    Toast.makeText(this, "请设置相应的定位权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 定位
     */
    private void startLocate() {
        //伪代码
// 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();//获取地图实例
        //开启定位图层，一定不要少了这句，否则对在地图的设置、绘制定位点将无效
        mBaiduMap.setMyLocationEnabled(true);

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }
    //把String转化为double
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }
    private Bitmap getViewBitmap(View addViewContent) {
        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0, addViewContent.getMeasuredWidth(), addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        return bitmap;
    }
    public void generateMarker() {

        for (MapBean.ResultBean.ItemsBean item : mDatas) {
            LatLng ll = new LatLng(convertToDouble(item.getWeiDu(),0), convertToDouble(item.getJingDu(),0));
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customer_marker, null);
            TextView tv_hotel_price = (TextView) view.findViewById(R.id.tv_hotel_price);
            tv_hotel_price.setText(new StringBuilder().append(item.getMapid()));
            BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromBitmap(getViewBitmap(view));

            Bundle bundle = new Bundle();
            MapInfo mapinfo = new MapInfo(item.getMapid(),item.getJingDu(),item.getWeiDu());
            bundle.putSerializable("HOTEL", mapinfo);

            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
            OverlayOptions oo = new MarkerOptions().position(ll).icon(markerIcon).zIndex(9).draggable(false).extraInfo(bundle);
            mBaiduMap.addOverlay(oo);
        }

    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            first.setText(location.getLocationDescribe());

//            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
//            mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmap));

            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    .latitude(location.getLatitude()).longitude(location.getLongitude()).build();

            mBaiduMap.setMyLocationData(locData);//给地图设置定位数据，这样地图就显示位置了

            /**
             * 绘制Marker，地图上常见的类似气球形状的图层
             */
//            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//            MarkerOptions markerOptions = new MarkerOptions();//参数设置类
//            //LatLng latLng = mBaiduMap.getMapStatus().target;
//            markerOptions.position(latLng);//marker坐标位置
//            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
//            markerOptions.icon(bitmap);//marker图标，可以自定义
//            markerOptions.draggable(false);//是否可拖拽，默认不可拖拽
//            markerOptions.anchor(0.5f, 1.0f);//设置 marker覆盖物与位置点的位置关系，默认（0.5f, 1.0f）水平居中，垂直下对齐
//            markerOptions.alpha(0.8f);//marker图标透明度，0~1.0，默认为1.0
//            markerOptions.animateType(MarkerOptions.MarkerAnimateType.drop);//marker出现的方式，从天上掉下
//            markerOptions.flat(false);//marker突变是否平贴地面
//            markerOptions.zIndex(1);//index
//            markerOptions.title("12");
//            Marker mMarker = (Marker) mBaiduMap.addOverlay(markerOptions);//在地图上增加mMarker图层

/**
 * 绘制折线
 */
//            PolylineOptions polylineOptions= new PolylineOptions();//参数设置类
//            polylineOptions.width(10);//宽度,单位：像素
//            polylineOptions.color(0xAAFF0000);//设置折线颜色
//            polylineOptions.points(points);//折线顶点坐标集

/**
 * colorValue：List<Integer>，折线颜色集合
 * 如果这里设置了折现颜色集合，那么options.color()中设置的颜色会被覆盖
 * 例：5个点能画出4条线段，每条线段绘制时按照索引依次取值，
 如果颜色个数小于线段个数，剩余线段取最后一个颜色绘制
 */
//            polylineOptions.colorsValues(colorValue);
//            polylineOptions.dottedLine(false);//是否是虚线，默认为false
//            polylineOptions.zIndex(9);//index
//            polylineOptions.visible(true);//是否可见，默认可见
//            polylineOptions.extraInfo(bundle);//附加bundle


/**
 * 绘制圆形
 */

//            OverlayOptions oCircle = new CircleOptions().fillColor(0x000000FF)
//                    .center(latLng).stroke(new Stroke(5, 0xAA000000))
//                    .radius(1400);
//            mBaiduMap.addOverlay(oCircle);

/**
 * 绘制文本文字
 */
//            OverlayOptions ooText = new TextOptions().bgColor(0xAAFFFF00)
//                    .fontSize(24).fontColor(0xFFFF00FF).text("12").rotate(-30)
//                    .position(latLng);
//            mBaiduMap.addOverlay(ooText);


            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();

//                List<LatLng> points = [point1, point2,point3];
//                var view = mBaiduMap.getViewport(eval(points));
//                var mapZoom = view.zoom;
//                var centerPoint = view.center;
//                map.centerAndZoom(centerPoint,mapZoom);

                //设置缩放中心点；缩放比例；
                builder.target(ll).zoom(8);
                //给地图设置状态
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }


            Log.e("描述：", sb.toString());
        }
    }
}
