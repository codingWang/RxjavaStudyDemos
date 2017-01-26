package com.example.operator.multiplesource;

/**
 * @desc :
 * @auther duwei
 * @date 2017/1/26.
 */

public class Data {

    private static final long STALE_MS = 5 * 1000;

     String value ;

     long timestamp;


    public Data(String value){
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }


    public boolean isUpToDate(){
        return System.currentTimeMillis() - timestamp<STALE_MS;
    }

}
