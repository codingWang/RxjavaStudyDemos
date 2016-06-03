package com.example.operator.transformimg;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 杜伟 on 2016/5/28.
 */
public class Demo_concatMap {

    /**
     * ��flatMap��������ͬ���ǣ�concatMap�������ڴ��������Observableʱ��
     * ���õ��ǡ�����(concat)���ķ�ʽ�������ǡ��ϲ�(merge)���ķ�ʽ��
     * ����ܱ�֤���������˳���ԣ�Ҳ����˵�ύ�������ߵĽ���ǰ���˳���ύ�ģ�������ڽ���������
     * @param args
     */
    public static void main(String[] args) {

      Observable.just(1,2,3,4).concatMap(new Func1<Integer, Observable<?>>() {
          @Override
          public Observable<?> call(Integer integer) {
              return Observable.just(2*integer,4*integer);
          }
      }).subscribe();




    }
}
