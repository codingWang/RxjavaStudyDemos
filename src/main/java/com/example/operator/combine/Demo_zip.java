package com.example.operator.combine;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by 杜伟 on 2016/5/28.
 */
public class Demo_zip {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("A","B","C","D","E");
        List<Integer> list2 = Arrays.asList(1,2,3,4,5);

        Observable<String> observable1 = Observable.from(list1);
        Observable<Integer> observable2 = Observable.from(list2);

        Observable.zip(observable1, observable2, new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {//�任
                return s+integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

}
