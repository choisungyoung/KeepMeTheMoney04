package com.example.keepmethemoney04.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Model.Saving;

import java.util.ArrayList;

public class DetailListviewAdapter extends BaseAdapter {
   private LayoutInflater inflater;
    private int layout;
   private ArrayList<Saving.SavingPair> data;
    private int targetMoney;
   public DetailListviewAdapter(Context context, int layout, ArrayList<Saving> list, int index){
       this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       //======================
       this.data=list.get(index).getArrayList();
       this.layout=layout;
       this.targetMoney = targetMoney;
   }
   @Override
   public int getCount(){return data.size();}
   @Override
   public Saving.SavingPair getItem(int position){return data.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
   public View getView(int position, View convertView, ViewGroup parent){
       if(convertView==null){
           convertView=inflater.inflate(layout,parent,false);
       }
        Saving.SavingPair sp = data.get(position);

        TextView title = convertView.findViewById(R.id.detailTitle);
        title.setText("•  "+sp.title);
        TextView content = convertView.findViewById(R.id.detailContent);
        String str = sp.content;

        if(position == 3 || position == 4){
            str += " %";
        }
        else if(position == 5){
            str += " 원";
        }else if(position == 6){
            str += " 개월";
        }
        content.setText(str);

       return convertView;
   }
}