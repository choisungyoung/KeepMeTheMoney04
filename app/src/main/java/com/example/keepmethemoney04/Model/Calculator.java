package com.example.keepmethemoney04.Model;

public class Calculator {


    public static double getPrincipal(int expirySum, int month, double rate){
        rate /= 100;
        return  expirySum/(month +rate*((month * ( month + 1))/2)/12);
    }

    public static double getExpirySum(double principal, int month, double rate){
        rate /= 100;
        return (principal*month ) + (principal *rate *((month * ( month + 1))/2)/12);

    }

}
