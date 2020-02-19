package com.example.keepmethemoney04.APIDataParser;


import android.os.Message;

import com.example.keepmethemoney04.*;
import com.example.keepmethemoney04.Activity.MainActivity;
import com.example.keepmethemoney04.Model.Data;
import com.example.keepmethemoney04.Model.Saving;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SavingParser implements Runnable{
    String clientKey = "124d4f36fd9bf2492357a7ad9fb16e01";

    private String urlStr = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?";

    private MainActivity.MyHandler mHandler;
    public SavingParser(MainActivity.MyHandler mHandler) {
        this.mHandler = mHandler;
    }

    public String savingParse(String bankCode, int page){
        String str, receiveMsg = null;
        URL url = null;
        try {//"auth="+clientKey +
            url = new URL(urlStr+ "auth="+clientKey +"&topFinGrpNo=020000&pageNo="+page+"&financeCd="+bankCode);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                System.out.println("receiveMsg : "+ receiveMsg);

                reader.close();
            } else {
                System.out.println("통신 결과: "+ conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;

    }

    public ArrayList<Saving> jsonToList(String json){

        ArrayList<Saving> savings = new ArrayList<>();
        ArrayList<Saving> temps = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jarr = jsonObject.getJSONObject("result").getJSONArray("baseList");

            for(int i = 0 ; i < jarr.length() ; i++){
                JSONObject jo = jarr.getJSONObject(i);

                String dcls_month = jo.isNull("dcls_month") ? "" :  jo.getString("dcls_month");
                String fin_co_no = jo.isNull("fin_co_no") ? "" :  jo.getString("fin_co_no");
                String kor_co_nm = jo.isNull("kor_co_nm") ? "" :  jo.getString("kor_co_nm");
                String fin_prdt_cd = jo.isNull("fin_prdt_cd") ? "" :  jo.getString("fin_prdt_cd");
                String fin_prdt_nm = jo.isNull("fin_prdt_nm") ? "" :  jo.getString("fin_prdt_nm").replace("\n"," ");
                String join_way = jo.isNull("join_way") ? "" :  jo.getString("join_way");
                String mtrt_int = jo.isNull("mtrt_int") ? "" :  jo.getString("mtrt_int");
                String spcl_cnd = jo.isNull("spcl_cnd") ? "" :  jo.getString("spcl_cnd");
                String join_deny = jo.isNull("join_deny") ? "" :  jo.getString("join_deny");
                String join_member = jo.isNull("join_member") ? "" :  jo.getString("join_member");
                String etc_note = jo.isNull("etc_note") ? "" :  jo.getString("etc_note");
                int max_limit = jo.isNull("max_limit") ? Integer.MAX_VALUE :  jo.getInt("max_limit");
                String dcls_strt_day = jo.isNull("dcls_strt_day") ? "" :  jo.getString("dcls_strt_day");
                String dcls_end_day = jo.isNull("dcls_end_day") ? "" :  jo.getString("dcls_end_day");
                String fin_co_subm_day = jo.isNull("fin_co_subm_day") ? "" :  jo.getString("fin_co_subm_day");

                Saving s = new Saving(dcls_month, fin_co_no, kor_co_nm, fin_prdt_cd, fin_prdt_nm, join_way, mtrt_int, spcl_cnd, join_deny, join_member, etc_note, max_limit, dcls_strt_day, dcls_end_day, fin_co_subm_day);
                temps.add(s);
            }

            JSONArray jbrr = jsonObject.getJSONObject("result").getJSONArray("optionList");

            for(int i = 0 ; i < jbrr.length() ; i++) {
                JSONObject jo = jbrr.getJSONObject(i);

                String dcls_month = jo.isNull("dcls_month") ? "" :  jo.getString("dcls_month");
                String fin_co_no = jo.isNull("fin_co_no") ? "" :  jo.getString("fin_co_no");
                String fin_prdt_cd = jo.isNull("fin_prdt_cd") ? "" :  jo.getString("fin_prdt_cd");
                String intr_rate_type = jo.isNull("intr_rate_type") ? "" :  jo.getString("intr_rate_type");
                String intr_rate_type_nm = jo.isNull("intr_rate_type_nm") ? "" :  jo.getString("intr_rate_type_nm");
                String rsrv_type = jo.isNull("rsrv_type") ? "" :  jo.getString("rsrv_type");
                String rsrv_type_nm = jo.isNull("rsrv_type_nm") ? "" :  jo.getString("rsrv_type_nm");
                String save_trm = jo.isNull("save_trm") ? "" :  jo.getString("save_trm");
                Double intr_rate = jo.isNull("intr_rate") ? 0 :  jo.getDouble("intr_rate");
                Double intr_rate2 = jo.isNull("intr_rate2") ? 0 :  jo.getDouble("intr_rate2");

                Saving s = new Saving(dcls_month, fin_co_no, fin_prdt_cd, intr_rate_type, intr_rate_type_nm, rsrv_type, rsrv_type_nm, save_trm, intr_rate, intr_rate2);

                for(Saving saving : temps){
                    if (saving.getDcls_month().equals(s.getDcls_month()) && saving.getFin_co_no().equals(s.getFin_co_no()) && saving.getFin_prdt_cd().equals(s.getFin_prdt_cd())){
                        Saving newSaving = new Saving(saving, s);
                        savings.add(newSaving);
                        break;
                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return savings;
    }
    public int getMaxPage(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            int maxPage = jsonObject.getJSONObject("result").getInt("max_page_no");
            return maxPage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void addSavingList(String bankCode){
        String json = savingParse(bankCode, 1);
        System.out.println("Saving test: "+ json );
        int maxPage = getMaxPage(json);
        ArrayList<Saving> list = jsonToList(json);
        Data.savings.addAll(list);
        System.out.println("Saving list size : " + list.size());
        for(int i = 2 ; i <= maxPage ; i++){
            String aPageJson = savingParse(bankCode, 1);
            ArrayList<Saving> aPageList = jsonToList(aPageJson);
            Data.savings.addAll(aPageList);
        }
        System.out.println("Saving Data size : " + Data.savings.size());
    }
    @Override
    public void run() {
        addSavingList("0010017");
        addSavingList("0010024");

        Message message = mHandler.obtainMessage(101);
        mHandler.sendMessage(message);
    }
}
