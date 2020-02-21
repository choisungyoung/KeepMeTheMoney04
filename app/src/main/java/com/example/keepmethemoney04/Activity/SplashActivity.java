package com.example.keepmethemoney04.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.keepmethemoney04.R;

public class SplashActivity extends AppCompatActivity {

    TextView answer1, answer2;
    TextView textView0, textView1, textView2, textView3, textView4;
    TextView textView5, textView6, textView7, textView8, textView9;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView0 = findViewById(R.id.textView0);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);


    }



    public void mOnClick(View v) {
        if (isFirst) {
            textView5.setText("선물");
            textView6.setText("하고 싶으신 ");
            textView7.setText("분");
            textView8.setText("이");
            textView9.setText("있나요?");
            textView0.setVisibility(View.GONE);
            textView1.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
            answer1.setText("네!");
            answer2.setText("아니오.");
            isFirst = !isFirst;
        } else {
            if(v == answer1){
                finish();
            } else {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }
}
