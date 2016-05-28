package com.example.operator.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by 鏉滀紵 on 2016/5/26.
 */
public class Demo_merge_mergeDelayError {


    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(6,7,8,9,0);

        Observable<Integer> observable1 = Observable.from(list1);
        Observable<Integer> observable2 = Observable.from(list2);

        Observable<Integer> mergedObservable = Observable.merge(observable1,observable2);//鍚堝苟涓や釜鏁版嵁婧?


//        Observable.mergeDelayError(observable1,observable2);
// 从一个Observable中继续发射数据即便是其中有一个抛出了错误。当所有的Observables都完成时，mergeDelayError()将会发射onError()

        mergedObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });


    }

}
