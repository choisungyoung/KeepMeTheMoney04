package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.os.Bundle;
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
                imageList.add(new Pair("스마트폰", R.drawable.phone));
                imageList.add(new Pair("노트북", R.drawable.laptop));
                imageList.add(new Pair("해외여행", R.drawable.trip));
                imageList.add(new Pair("신발", R.drawable.shoe));
                imageList.add(new Pair("경차", R.drawable.lightcar));
                imageList.add(new Pair("원룸", R.drawable.home));
                break;
            case "30대":
                imageList.add(new Pair("중형차", R.drawable.car));
                imageList.add(new Pair("아파트", R.drawable.apart));
                imageList.add(new Pair("스마트폰", R.drawable.phone));
                imageList.add(new Pair("스마트폰", R.drawable.phone));
                imageList.add(new Pair("스마트폰", R.drawable.phone));
                break;
            case "40대":
                imageList.add(new Pair("외제차", R.drawable.car));
                break;
            case "50대":
                imageList.add(new Pair("안마의자", R.drawable.massage));
                break;
            case "60대 이상":
                imageList.add(new Pair("안마의자", R.drawable.massage));
                break;
        }
    }
}
