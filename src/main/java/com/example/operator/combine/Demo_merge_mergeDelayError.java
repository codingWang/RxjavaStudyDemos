package com.example.operator.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_merge_mergeDelayError {


    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(6,7,8,9,0);

        Observable<Integer> observable1 = Observable.from(list1);
        Observable<Integer> observable2 = Observable.from(list2);

        Observable<Integer> mergedObservable = Observable.merge(observable1,observable2);//合并两个数据�?


//        Observable.mergeDelayError(observable1,observable2);
// ��һ��Observable�м����������ݼ�����������һ���׳��˴��󡣵����е�Observables�����ʱ��mergeDelayError()���ᷢ��onError()

        mergedObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });


    }

}
