package com.example.operator.transformimg;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 杜伟 on 2016/5/28.
 */
public class Demo_switchMap {
    /**
     * ��flatMap��������ͬ���ǣ�switchMap�������ᱣ�����µ�Observable�����Ľ���������ɵĽ����
     * �ٸ�������˵������ԴObservable����A��B��C���������ͨ��switchMap���Զ���ӳ�����
     * ӳ���Ӧ�û����A1��A2��B1��B2��C1��C2�������ڲ���B2��ͬʱ��C1�Ѿ������ˣ�
     * �������Ľ���ͱ��A1��A2��B1��C1��C2��B2���������ˣ�
     */
    public static void main(String[] args) {
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                //10���ӳ�ִ��ʱ��Ϊ200���롢20��30���ӳ�ִ��ʱ��Ϊ180����
                int delay = 200;
                if (integer > 10)
                    delay = 180;
                //ֻ�����һ����30,15����ӡ�����ˣ���Ϊ���һ���滻��ǰ��Ĺ��ڵ�Observable��10��20��
                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).toBlocking().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("switchMap Next:" + integer);
            }
        });


    }

}
