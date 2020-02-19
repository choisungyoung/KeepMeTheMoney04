package com.example.keepmethemoney04.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Adapter.ListviewAdapter;
import com.example.keepmethemoney04.Model.Calculator;
import com.example.keepmethemoney04.Model.Data;
import com.example.keepmethemoney04.Model.Saving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity {
    ArrayList<Saving> savings = null;
    ListView listView = null;
    ListView menuListView = null ;
    int targetMoney = 0;
    String productName = null;
    public boolean stdFlag = false;    //기본금리, 우대금리
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listview);
        savings = Data.savings;
        Intent intent = getIntent();
        targetMoney = intent.getExtras().getInt("targetMoney");
        productName = intent.getExtras().getString("productName");

        TextView msgTextView = findViewById(R.id.msgTextView);
        msgTextView.setText("당신의 "+productName+" 구매를 위한\n최고 금리는 "+savings.get(0).getIntr_rate2()+"% 입니다.");

        final ListviewAdapter listadapter = new ListviewAdapter(this, R.layout.item_saving, savings, targetMoney);
        listView.setAdapter(listadapter);



        final String[] items = {"기본 금리가 높은 순", "최대 우대 금리가 높은 순", "기간이 짧은 순", "기간이 긴 순", "최소 월 납입금액 순", "우대금리로 계산"} ;
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;

        menuListView = (ListView) findViewById(R.id.drawer_menulist) ;
        menuListView.setAdapter(adapter) ;

        menuListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                TextView contentTextview = (TextView) findViewById(R.id.msgTextView) ;

                switch (position) {
                    case 0 : // 기본 금리가 높은 순
                        sortByRate();
                        break ;
                    case 1 : // 최대 우대 금리가 높은 순
                        sortByRate2();
                        break ;
                    case 2 : // 기간이 짧은 순
                        sortByMonthAsc();
                        break ;
                    case 3 : // 기간이 긴 순
                        sortByMonthDesc();
                        break ;
                    case 4 : // 최소 월 납입금액 순
                        sortByMonthPrice();
                        break ;
                    case 5 : // 기본금리계산인지 우대금리계산인지
                        if(stdFlag){
                            //현재 우대 금리기 때문에 기준 금리로 변환
                            stdFlag = false;
                            items[position] = "우대금리로 계산";

                        }
                        else{   //현재 기준 금리기 때문에 우대 금리로 변환
                            stdFlag = true;
                            items[position] = "기본금리로 계산";
                        }
                        adapter.notifyDataSetChanged();
                        break ;
                }
                listadapter.notifyDataSetChanged();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer) ;
                drawer.closeDrawer(Gravity.LEFT) ;
            }
        });
    }

    public void onClickCard(View v){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("savings",savings);
        TextView textView = v.findViewById(R.id.hiddenIndex);       //index값 숨겨놈
        int index = Integer.parseInt(textView.getText().toString());
        intent.putExtra("index",index);
        startActivity(intent);
    }

    public void sortByRate(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( o2.getIntr_rate() < o1.getIntr_rate()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
    }

    public void sortByRate2(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( o2.getIntr_rate2() > o1.getIntr_rate2()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
    }

    public void sortByMonthAsc(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( Integer.parseInt(o2.getSave_trm()) < Integer.parseInt(o1.getSave_trm())){
                    return 1;
                }
                else if( Integer.parseInt(o2.getSave_trm()) == Integer.parseInt(o1.getSave_trm())){
                    if( o2.getIntr_rate() > o1.getIntr_rate()){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                else{
                    return -1;
                }
            }
        });
    }

    public void sortByMonthDesc(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( Integer.parseInt(o2.getSave_trm()) > Integer.parseInt(o1.getSave_trm())){
                    return 1;
                }
                else if( Integer.parseInt(o2.getSave_trm()) == Integer.parseInt(o1.getSave_trm())){
                    if( o2.getIntr_rate() > o1.getIntr_rate()){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                else{
                    return -1;
                }
            }
        });
    }
    public void sortByMonthPrice(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( (int) Calculator.getPrincipal(targetMoney,Integer.parseInt(o2.getSave_trm()),o2.getIntr_rate()) < (int) Calculator.getPrincipal(targetMoney,Integer.parseInt(o1.getSave_trm()),o1.getIntr_rate())){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
    }
}
