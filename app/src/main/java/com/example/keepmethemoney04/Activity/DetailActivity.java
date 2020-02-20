package com.example.keepmethemoney04.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.keepmethemoney04.Adapter.DetailListviewAdapter;
import com.example.keepmethemoney04.Model.Saving;
import com.example.keepmethemoney04.R;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ListView listView = null;

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        listView = findViewById(R.id.detailListView);
        Intent intent = getIntent();
        int index = intent.getExtras().getInt("index");
        ArrayList<Saving> list = (ArrayList<Saving>)intent.getSerializableExtra("savings");

        TextView textView = findViewById(R.id.savingName);
        textView.setText(list.get(index).getFin_prdt_nm());


        DetailListviewAdapter listadapter = new DetailListviewAdapter(this, R.layout.item_detail, list,  index);
        listView.setAdapter(listadapter);

        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //사용자의 현재 위치
        Location userLocation = getMyLocation();
        if( userLocation != null ) {
            double latitude = userLocation.getLatitude();
            double longitude = userLocation.getLongitude();
//            userVO.setLat(latitude);
//            userVO.setLon(longitude);
            System.out.println("////////////현재 내 위치값 : "+latitude+","+longitude);
        }
    }
    private Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("////////////사용자에게 권한을 요청해야함");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        }
        else {
            System.out.println("////////////권한요청 안해도됨");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
                System.out.println("////////////현재 내 위치값 : "+lat+","+lng);
            }
        }
        return currentLocation;
    }
}
