package com.example.keepmethemoney04.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Activity.ListActivity;
import com.example.keepmethemoney04.Model.Pair;

import java.util.ArrayList;

public class ImageProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pair> imageList;

    public ImageProductAdapter(Context context, ArrayList<Pair> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.image_product, null);

        ImageView imageView = view.findViewById(R.id.imageView0);
        imageView.setImageResource(imageList.get(position).id);

        TextView textView = view.findViewById(R.id.textView0);
        textView.setText(imageList.get(position).name);

        CardView cardView = view.findViewById(R.id.cardview0);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("TAG", "This image was clicked: " + position);
                //이미지를 터치했을때 동작하는 곳

                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("productName",imageList.get(position).name);
                intent.putExtra("targetMoney",imageList.get(position).price);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
