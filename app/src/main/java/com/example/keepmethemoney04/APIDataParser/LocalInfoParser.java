package com.example.keepmethemoney04.APIDataParser;


import android.os.Message;

import com.example.keepmethemoney04.Activity.DetailActivity;
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


public class LocalInfoParser implements Runnable{
    public class LocalInfo{
        public String x;
        public String y;
        public String bankName;

        public LocalInfo(String x, String y, String bankName) {
            this.x = x;
            this.y = y;
            this.bankName = bankName;
        }

        @Override
        public String toString() {
            return "LocalInfo{" +
                    "x='" + x + '\'' +
                    ", y='" + y + '\'' +
                    ", bankName='" + bankName + '\'' +
                    '}';
        }
    }
    private String clientKey = "KakaoAK 2528dbebdf35fdac013962b1b571af13";
    private String query = "";
    private String x;
    private String y;
    private int radius;
    private String catCode = "BK9";
    private String urlStr = "https://dapi.kakao.com/v2/local/search/keyword.json?";

    public static ArrayList<LocalInfo> locals = new ArrayList<>();
    private DetailActivity.MyHandler mHandler;
    public LocalInfoParser(DetailActivity.MyHandler mHandler) {
        this.mHandler = mHandler;
    }

    public LocalInfoParser(String x, String y, int radius, String query, DetailActivity.MyHandler mHandler) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.query = query;
        this.mHandler = mHandler;
    }

    public String localInfoParse(){
        String str, receiveMsg = null;
        URL url = null;
        try {//"auth="+clientKey +
            url = new URL(urlStr+"x="+x+"&y="+y+"&radius="+radius+"&query="+query+"&category_group_code="+catCode);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Authorization", clientKey);
            conn.setRequestMethod("GET");

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

    void jsonParse(String json){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jarr = jsonObject.getJSONArray("documents");

            for(int i = 0 ; i < jarr.length() ; i++){

                LocalInfo li = new LocalInfo(jarr.getJSONObject(i).getString("x"),jarr.getJSONObject(i).getString("y"), jarr.getJSONObject(i).getString("place_name"));
                locals.add(li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String json = localInfoParse();
        jsonParse(json);
        Message message = mHandler.obtainMessage(101);
        mHandler.sendMessage(message);
    }
}