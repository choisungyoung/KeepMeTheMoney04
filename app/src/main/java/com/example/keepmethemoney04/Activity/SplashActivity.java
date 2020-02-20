package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.keepmethemoney04.R;
import com.example.keepmethemoney04.SwipeBackActivity;
import com.example.keepmethemoney04.SwipeBackLayout;



public class SplashActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }
}
