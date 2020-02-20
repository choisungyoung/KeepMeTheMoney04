package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Adapter.DetailListviewAdapter;
import com.example.keepmethemoney04.Model.Saving;


import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ListView listView = null;
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
    }
}
