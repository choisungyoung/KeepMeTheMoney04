package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Adapter.ImageProductAdapter;
import com.example.keepmethemoney04.Model.Pair;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Pair> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        this.initializeData(intent.getStringExtra("ages"));

        gridView = findViewById(R.id.gridView);
//        ImageProductAdapter imgprdtAdapter = new ImageProductAdapter(this, imageList);
        gridView.setAdapter(new ImageProductAdapter(this, imageList));
//        gridView.addView(imgprdtAdapter.getView());
    }

    public void initializeData(String ages) {
        imageList = new ArrayList();

        switch (ages) {
            case "20대 이하":
                imageList.add(new Pair("향수", R.drawable.perfume, 1000000));
                imageList.add(new Pair("스마트폰", R.drawable.phone, 1200000));
                imageList.add(new Pair("노트북", R.drawable.laptop, 1500000));
                imageList.add(new Pair("무선게임기", R.drawable.game, 300000));
                imageList.add(new Pair("게임기", R.drawable.xbox, 500000));
                imageList.add(new Pair("카메라", R.drawable.camera, 1500000));
                imageList.add(new Pair("국내여행", R.drawable.trip, 500000));
                imageList.add(new Pair("해외여행", R.drawable.airplane, 2000000));
                imageList.add(new Pair("신발", R.drawable.shoe, 1000000));
                imageList.add(new Pair("자전거", R.drawable.bicycle, 1300000));
                imageList.add(new Pair("전동킥보드", R.drawable.kickboard, 800000));
                imageList.add(new Pair("이륜차", R.drawable.motorcycle, 2000000));
                imageList.add(new Pair("시계", R.drawable.watch, 4000000));
                imageList.add(new Pair("지갑", R.drawable.wallet, 600000));
                imageList.add(new Pair("시술비", R.drawable.cosmetic, 1000000));
                imageList.add(new Pair("원룸", R.drawable.home, 100000000));
                break;
            case "30대":
                imageList.add(new Pair("골프세트", R.drawable.golf, 1000000));
                imageList.add(new Pair("가전제품", R.drawable.electric, 5000000));
                imageList.add(new Pair("가구", R.drawable.sofa, 5000000));
                imageList.add(new Pair("명품시계", R.drawable.watch, 100000000));
                imageList.add(new Pair("명품지갑", R.drawable.wallet, 100000000));
                imageList.add(new Pair("명품가방", R.drawable.handbag, 30000000));
                imageList.add(new Pair("명품외투", R.drawable.coat, 30000000));
                imageList.add(new Pair("해외여행", R.drawable.airplane, 2000000));
                imageList.add(new Pair("노트북", R.drawable.laptop, 1500000));
                imageList.add(new Pair("게임기", R.drawable.xbox, 1500000));
                imageList.add(new Pair("카메라", R.drawable.camera, 1500000));
                imageList.add(new Pair("귀금속", R.drawable.diamond, 100000000));
                imageList.add(new Pair("중형차", R.drawable.car, 30000000));
                imageList.add(new Pair("시술비", R.drawable.cosmetic, 100000000));
                imageList.add(new Pair("아파트", R.drawable.apart, 200000000));
                break;
            case "40대":
                imageList.add(new Pair("양주", R.drawable.liquor, 30000000));
                imageList.add(new Pair("귀금속", R.drawable.diamond, 100000000));
                imageList.add(new Pair("명품지갑", R.drawable.wallet, 100000000));
                imageList.add(new Pair("명품가방", R.drawable.handbag, 30000000));
                imageList.add(new Pair("명품시계", R.drawable.watch, 100000000));
                imageList.add(new Pair("명품외투", R.drawable.coat, 30000000));
                imageList.add(new Pair("해외여행", R.drawable.airplane, 2000000));
                imageList.add(new Pair("한약", R.drawable.ginseng, 200000000));
                imageList.add(new Pair("골프세트", R.drawable.golf, 200000000));
                imageList.add(new Pair("아파트", R.drawable.apart, 200000000));
                imageList.add(new Pair("건물", R.drawable.building, 200000000));
                imageList.add(new Pair("경차", R.drawable.lightcar, 10000000));
                break;
            case "50대":
                imageList.add(new Pair("명품가방", R.drawable.handbag, 30000000));
                imageList.add(new Pair("명품외투", R.drawable.coat, 30000000));
                imageList.add(new Pair("명품시계", R.drawable.watch, 100000000));
                imageList.add(new Pair("명품지갑", R.drawable.wallet, 100000000));
                imageList.add(new Pair("귀금속", R.drawable.diamond, 100000000));
                imageList.add(new Pair("골프세트", R.drawable.golf, 200000000));
                imageList.add(new Pair("외제차", R.drawable.yangcar, 100000000));
                imageList.add(new Pair("국내여행", R.drawable.trip, 2000000));
                imageList.add(new Pair("해외여행", R.drawable.airplane, 2000000));
                imageList.add(new Pair("양주", R.drawable.liquor, 30000000));
                imageList.add(new Pair("조경수목", R.drawable.plant, 200000000));
                imageList.add(new Pair("한약", R.drawable.ginseng, 200000000));
                imageList.add(new Pair("돌침대", R.drawable.bed, 5000000));
                imageList.add(new Pair("안마의자", R.drawable.massage, 5000000));
                imageList.add(new Pair("아파트", R.drawable.apart, 200000000));
                imageList.add(new Pair("건물", R.drawable.building, 200000000));
                imageList.add(new Pair("금괴", R.drawable.gold, 200000000));
                break;
            case "60대 이상":
                imageList.add(new Pair("귀금속", R.drawable.diamond, 100000000));
                imageList.add(new Pair("금괴", R.drawable.gold, 200000000));
                imageList.add(new Pair("조경수목", R.drawable.plant, 200000000));
                imageList.add(new Pair("한약", R.drawable.ginseng, 200000000));
                imageList.add(new Pair("돌침대", R.drawable.bed, 5000000));
                imageList.add(new Pair("안마의자", R.drawable.massage, 5000000));
                imageList.add(new Pair("국내여행", R.drawable.trip, 2000000));
                imageList.add(new Pair("해외여행", R.drawable.airplane, 2000000));
                imageList.add(new Pair("아파트", R.drawable.apart, 200000000));
                imageList.add(new Pair("주택", R.drawable.house, 200000000));
                imageList.add(new Pair("건물", R.drawable.building, 200000000));
                break;
        }
    }
}
