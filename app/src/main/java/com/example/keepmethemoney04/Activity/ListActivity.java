package com.example.keepmethemoney04.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ListView menuListView = null;
    int targetMoney = 0;
    String productName = null;
    public boolean stdFlag = false;    //기본금리, 우대금리


    //애니메이션
    Animation tranlateUpAnim;
    Animation tranlateDownAnim;
    LinearLayout page;
    //숨겨진 페이지가 열렸는지 확인하는 변수
    boolean isPageOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listview);

        savings = Data.savings;
        sortByRate2();
        Intent intent = getIntent();
        targetMoney = intent.getExtras().getInt("targetMoney");
        productName = intent.getExtras().getString("productName");

        TextView targetMoneyText = findViewById(R.id.targetMoney);
        TextView productNameText = findViewById(R.id.productName);
        TextView rateText = findViewById(R.id.rate);

        targetMoneyText.setText(convertHangul(Integer.toString(targetMoney)));
        targetMoneyText.setPaintFlags(targetMoneyText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        productNameText.setText(productName);
        productNameText.setPaintFlags(productNameText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        rateText.setText(Double.toString(savings.get(0).getIntr_rate2()));

        final ListviewAdapter listadapter = new ListviewAdapter(this, R.layout.item_saving, savings, targetMoney);
        listView.setAdapter(listadapter);

        //애니메이션
        tranlateUpAnim = AnimationUtils.loadAnimation(this, R.anim.translate_up);
        tranlateDownAnim = AnimationUtils.loadAnimation(this, R.anim.translate_down); //페이지 슬라이딩 이벤트가 발생했을때 애니메이션이 시작 됐는지 종료 됐는지 감지할 수 있다.
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        tranlateUpAnim.setAnimationListener(animListener);
        tranlateDownAnim.setAnimationListener(animListener);
        page = findViewById(R.id.page);


        final String[] items = {"기본 금리가 높은 순", "최대 우대 금리가 높은 순", "기간이 짧은 순", "기간이 긴 순", "최소 월 납입금액 순", "우대금리로 계산"};
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        menuListView = (ListView) findViewById(R.id.drawer_menulist);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                switch (position) {
                    case 0: // 기본 금리가 높은 순
                        sortByRate();
                        break;
                    case 1: // 최대 우대 금리가 높은 순
                        sortByRate2();
                        break;
                    case 2: // 기간이 짧은 순
                        sortByMonthAsc();
                        break;
                    case 3: // 기간이 긴 순
                        sortByMonthDesc();
                        break;
                    case 4: // 최소 월 납입금액 순
                        sortByMonthPrice();
                        break;
                    case 5: // 기본금리계산인지 우대금리계산인지
                        if (stdFlag) {
                            //현재 우대 금리기 때문에 기준 금리로 변환
                            stdFlag = false;
                            items[position] = "우대금리로 계산";

                        } else {   //현재 기준 금리기 때문에 우대 금리로 변환
                            stdFlag = true;
                            items[position] = "기본금리로 계산";
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }
                listadapter.notifyDataSetChanged();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    public void onClickCard(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("savings", savings);
        TextView textView = v.findViewById(R.id.hiddenIndex);       //index값 숨겨놈
        int index = Integer.parseInt(textView.getText().toString());
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void sortByRate() {
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if (o2.getIntr_rate() < o1.getIntr_rate()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public void sortByRate2() {
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if (o2.getIntr_rate2() > o1.getIntr_rate2()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public void sortByMonthAsc() {
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if (Integer.parseInt(o2.getSave_trm()) < Integer.parseInt(o1.getSave_trm())) {
                    return 1;
                } else if (Integer.parseInt(o2.getSave_trm()) == Integer.parseInt(o1.getSave_trm())) {
                    if (o2.getIntr_rate() > o1.getIntr_rate()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });
    }

    public void sortByMonthDesc() {
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if (Integer.parseInt(o2.getSave_trm()) > Integer.parseInt(o1.getSave_trm())) {
                    return 1;
                } else if (Integer.parseInt(o2.getSave_trm()) == Integer.parseInt(o1.getSave_trm())) {
                    if (o2.getIntr_rate() > o1.getIntr_rate()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });
    }

    public void sortByMonthPrice() {
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if ((int) Calculator.getPrincipal(targetMoney, Integer.parseInt(o2.getSave_trm()), o2.getIntr_rate()) < (int) Calculator.getPrincipal(targetMoney, Integer.parseInt(o1.getSave_trm()), o1.getIntr_rate())) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public String convertHangul(String value) {
        String[] number_labels = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] range_labels = {"", "십", "백", "천", "만", "십만", "백만", "천만", "억", "십억"};


        int rangeCount = value.length()-1;
        int rawValue = Integer.valueOf(value);
        StringBuilder result = new StringBuilder();
        while(rangeCount >= 0) {
            int mod = (int)Math.pow(10, rangeCount);
            result.append(number_labels[rawValue/mod]);
            if(rawValue/mod > 0) {
                result.append(range_labels[rangeCount]);
            }
            rawValue = rawValue % mod;
            rangeCount--;
        }
      return result.toString();
    }


    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
/*
            if (isPageOpen) {
                page.setVisibility(View.GONE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }*/
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}
/*

class DecListView extends android.widget.ListView {

    private OnScrollListener onScrollListener;
    private OnDetectScrollListener onDetectScrollListener;

    public DecListView(Context context) {
        super(context);
        onCreate(context, null, null);
    }

    public DecListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context, attrs, null);
    }

    public DecListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onCreate(context, attrs, defStyle);
    }

    @SuppressWarnings("UnusedParameters")
    private void onCreate(Context context, AttributeSet attrs, Integer defStyle) {
        setListeners();
    }

    private void setListeners() {
        super.setOnScrollListener(new OnScrollListener() {

            private int oldTop;
            private int oldFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (onScrollListener != null) {
                    onScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (onScrollListener != null) {
                    onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                            totalItemCount);
                }

                if (onDetectScrollListener != null) {
                    onDetectedListScroll(view, firstVisibleItem);
                }
            }

            private void onDetectedListScroll(AbsListView absListView, int firstVisibleItem) {
                View view = absListView.getChildAt(0);
                int top = (view == null) ? 0 : view.getTop();

                if (firstVisibleItem == oldFirstVisibleItem) {
                    if (top > oldTop) {
                        onDetectScrollListener.onUpScrolling();
                    } else if (top < oldTop) {
                        onDetectScrollListener.onDownScrolling();
                    }
                } else {
                    if (firstVisibleItem < oldFirstVisibleItem) {
                        onDetectScrollListener.onUpScrolling();
                    } else {
                        onDetectScrollListener.onDownScrolling();
                    }
                }

                oldTop = top;
                oldFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setOnDetectScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.onDetectScrollListener = onDetectScrollListener;
    }
}

interface OnDetectScrollListener {

    void onUpScrolling();

    void onDownScrolling();
}*/