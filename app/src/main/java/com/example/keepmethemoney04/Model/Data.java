package com.example.keepmethemoney04.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Data {
    public static ArrayList<Saving> savings = new ArrayList<>();

    public static void sortByRate(){
        Collections.sort(savings, new Comparator<Saving>() {
            @Override
            public int compare(Saving o1, Saving o2) {
                if( o2.getIntr_rate() > o1.getIntr_rate()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
    }
}
