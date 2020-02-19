package com.example.keepmethemoney04.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Activity.SubActivity;
import com.example.keepmethemoney04.Model.Pair;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Pair> imageList;

    public ImagePagerAdapter(Context context, ArrayList<Pair> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_age, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageList.get(position).id);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(imageList.get(position).name);

        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //this will log the page number that was click
//                Log.i("TAG", "This page was clicked: " + position);
                Intent intent = new Intent(v.getContext(), SubActivity.class);
                intent.putExtra("ages", imageList.get(position).name);
                context.startActivity(intent);
            }
        });

        // 뷰페이저에 추가.
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}
