package com.example.keepmethemoney04.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.keepmethemoney04.R;

public class SplashActivity extends AppCompatActivity {

    TextView answer1, answer2;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.answer1 :

                break;
            case R.id.answer2 :
                break;
        }
    }
}
