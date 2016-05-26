package com.example;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
public class RxjavaSample1 {


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        Observable.from(list)
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return   String.format("%d ",integer);
                    }
                })
                .observeOn(Schedulers.immediate())
                .toBlocking()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                       // System.out.print(Thread.currentThread());
                        System.out.print(s);
                    }
                });

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
