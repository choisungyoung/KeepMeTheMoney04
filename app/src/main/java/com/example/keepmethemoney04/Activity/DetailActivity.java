package com.example.keepmethemoney04.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.keepmethemoney04.APIDataParser.LocalInfoParser;
import com.example.keepmethemoney04.Adapter.DetailListviewAdapter;
import com.example.keepmethemoney04.Model.Data;
import com.example.keepmethemoney04.Model.Saving;
import com.example.keepmethemoney04.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ListView listView = null;

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;
    double latitude;
    double longitude;
    MapView mapView;
    String bankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar);
        setContentView(R.layout.activity_detail);
        listView = findViewById(R.id.detailListView);
        Intent intent = getIntent();
        int index = intent.getExtras().getInt("index");
        ArrayList<Saving> list = (ArrayList<Saving>) intent.getSerializableExtra("savings");

        TextView textView = findViewById(R.id.savingName);
        textView.setText(list.get(index).getFin_prdt_nm());
        bankName = list.get(index).getKor_co_nm();

        DetailListviewAdapter listadapter = new DetailListviewAdapter(this, R.layout.item_detail, list, index);
        listView.setAdapter(listadapter);

        mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //사용자의 현재 위치
        Location userLocation = getMyLocation();
        if (userLocation != null) {
            latitude = userLocation.getLatitude();
            longitude = userLocation.getLongitude();
//            userVO.setLat(latitude);
//            userVO.setLon(longitude);
            System.out.println("////////////현재 내 위치값 : " + latitude + "," + longitude);
        }
        setCenter();


        LocalInfoParser lip = new LocalInfoParser(Double.toString(longitude), Double.toString(latitude), 20000, bankName, mHandler);
        Thread thread = new Thread(lip);
        thread.start();
    }

    private Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("////////////사용자에게 권한을 요청해야함");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        } else {
            System.out.println("////////////권한요청 안해도됨");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                latitude = currentLocation.getLatitude();
                longitude = currentLocation.getLongitude();
                System.out.println("////////////현재 내 위치값 : " + latitude + "," + longitude);
            }
        }
        return currentLocation;
    }

    private void setCenter() {
        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("내위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.BluePin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);
    }
    void addPoint(){
        for(int i = 0 ; i < LocalInfoParser.locals.size() ; i++){
            LocalInfoParser.LocalInfo localInfo = LocalInfoParser.locals.get(i);
            MapPOIItem marker = new MapPOIItem();
            marker.setItemName(localInfo.bankName);
            marker.setTag(0);
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord( Double.parseDouble(localInfo.y), Double.parseDouble(localInfo.x)));
            marker.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
            mapView.addPOIItem(marker);
        }
    }

    private final DetailActivity.MyHandler mHandler = new DetailActivity.MyHandler(this);


    public static class MyHandler extends Handler {
        private final WeakReference<DetailActivity> weakReference;

        public MyHandler(DetailActivity mainactivity) {
            weakReference = new WeakReference<DetailActivity>(mainactivity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println("됨됨");
            DetailActivity mainactivity = weakReference.get();

            if (mainactivity != null) {
                switch (msg.what) {
                    case 101:
                        //데이터 파싱 성공
                        mainactivity.addPoint();
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalInfoParser.locals.clear();
    }
}