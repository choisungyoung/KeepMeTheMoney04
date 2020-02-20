package com.example.keepmethemoney04.Model;

public class Pair {
    public String name;
    public int id;
    public int price;

    public Pair(String name, int id){
        this.name = name;
        this.id = id;
    }

    public Pair(String name, int id, int price){
        this(name, id);
        this.price = price;
    }
}
