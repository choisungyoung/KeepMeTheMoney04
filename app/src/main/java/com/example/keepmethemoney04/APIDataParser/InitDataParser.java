package com.example.keepmethemoney04.APIDataParser;

import com.example.keepmethemoney04.Activity.MainActivity;

public class InitDataParser {

    public void initData(MainActivity.MyHandler myHandler){

        SavingParser sp = new SavingParser(myHandler);

        Thread SavingThread = new Thread(sp);

        SavingThread.start();
/*
        try {
            SavingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
