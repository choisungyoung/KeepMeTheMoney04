package com.example.keepmethemoney04.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Activity.ListActivity;
import com.example.keepmethemoney04.Model.Calculator;
import com.example.keepmethemoney04.Model.Saving;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {
   private LayoutInflater inflater;
   private ArrayList<Saving> data;
    private int targetMoney;
   private int layout;
   private Context context;
   public ListviewAdapter(Context context, int layout, ArrayList<Saving> data, int targetMoney){
       this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       this.context = context;
       this.data=data;
       this.layout=layout;
       this.targetMoney = targetMoney;
   }
   @Override
   public int getCount(){return data.size();}
   @Override
   public Saving getItem(int position){return data.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
   public View getView(int position, View convertView, ViewGroup parent){
       if(convertView==null){
           convertView=inflater.inflate(layout,parent,false);
       }

        Saving s=data.get(position);
        TextView savingNmae = convertView.findViewById(R.id.savingName);
        TextView bankName = convertView.findViewById(R.id.bankName);
        TextView rate = convertView.findViewById(R.id.rate);
        TextView spRate = convertView.findViewById(R.id.spRate);
        TextView month = convertView.findViewById(R.id.month);
        TextView monthPrice = convertView.findViewById(R.id.monthPrice);
        TextView hiddenIndex = convertView.findViewById(R.id.hiddenIndex);
        TextView stdText = convertView.findViewById(R.id.stdRate);
        savingNmae.setText(s.getFin_prdt_nm());
        bankName.setText(s.getKor_co_nm());
        rate.setText(s.getIntr_rate()+"%");
        spRate.setText(s.getIntr_rate2()+"%");
        month.setText(s.getSave_trm()+"개월 간");
        hiddenIndex.setText(position+"");
        int principal = 0;
        boolean f = ((ListActivity)context).stdFlag;
        if(f){
            principal = (int) Calculator.getPrincipal(targetMoney,Integer.parseInt(s.getSave_trm()),s.getIntr_rate2());
            stdText.setText("우대금리");
        }
        else {
            principal = (int) Calculator.getPrincipal(targetMoney,Integer.parseInt(s.getSave_trm()),s.getIntr_rate());
            stdText.setText("기본금리");
        }
        monthPrice.setText("월 "+Integer.toString(principal)+"원");
       //TextView accountName = convertView.findViewById(R.id.accountName);

       return convertView;
   }


}