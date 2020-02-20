package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.APIDataParser.InitDataParser;
import com.example.keepmethemoney04.Adapter.ImagePagerAdapter;
import com.example.keepmethemoney04.Model.Data;
import com.example.keepmethemoney04.Model.Pair;
import com.example.keepmethemoney04.Model.Saving;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    private ArrayList<Pair> imageList;
    private static final int DP = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initializeData();

        viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);

        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin / 2);
        viewPager.setAdapter(new ImagePagerAdapter(this, imageList));

        if(savedInstanceState == null){
            Log.d("test","스레드 입장");
            InitDataParser idp = new InitDataParser();
            idp.initData(mHandler);
        }
    }

    public void initializeData() {
        imageList = new ArrayList();

        imageList.add(new Pair("20대 이하",R.drawable.twenty));
        imageList.add(new Pair("30대",R.drawable.thirty));
        imageList.add(new Pair("40대",R.drawable.forty));
        imageList.add(new Pair("50대",R.drawable.fifty));
        imageList.add(new Pair("60대 이상",R.drawable.sixty));
    }

    public void intentList() {
        //로딩이 끝났을 때, 실행할 로직
        Data.sortByRate();

    }

    private final MyHandler mHandler = new MyHandler(this);


    public static class MyHandler extends Handler {
        private final WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity mainactivity) {
            weakReference = new WeakReference<MainActivity>(mainactivity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println("됨됨");
            MainActivity mainactivity = weakReference.get();

            if (mainactivity != null) {
                switch (msg.what) {
                    case 101:
                        //데이터 파싱 성공
                        mainactivity.intentList();
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Data.savings.clear();
    }
}
