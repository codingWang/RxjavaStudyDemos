package com.example.operator.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_filter {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple","Orange","Pear","Peach","Banana","Grape","Blueberry","Lemon","Pomegranate");

        Observable.from(list)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                       // System.out.println("call"+s);
                        return s.startsWith("P");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });



    }
}
