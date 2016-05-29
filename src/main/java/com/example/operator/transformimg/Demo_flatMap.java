package com.example.operator.transformimg;

import java.io.File;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by duwei on 2016/5/28.
 *
 * flatMap�������ǰ�Observable�����Ľ��ת���ɶ��Observable��
 * Ȼ�������Observable����ƽ������һ��Observable���������ύ�����Ľ���������ߡ�
 * flatMap������ͨ������һ��������Ϊ����ת��ԴObservable������������У���
 * �����Զ���ת�������������������з���һ���µ�Observable��
 * Ȼ��flatMap������ͨ���ϲ���ЩObservable�����һ��Observable���������ύ����������ߡ�
 */
public class Demo_flatMap {

    public static void main(String[] args) {
        new Demo_flatMap()
                .listFiles(new File("D:\\githubProject\\JavaProkect\\rxjava\\src\\main\\java\\com\\okhttp"))
                .subscribe(new Action1<File>() {
            @Override
            public void call(File file) {
                System.out.println(file.getName());
            }
        });
    }


    public Observable<File> listFiles(File f){
        if(f.isDirectory()){
            return Observable
                    .from(f.listFiles())
                    .flatMap(new Func1<File, Observable<File>>() {
                @Override
                public Observable<File> call(File file) {
                    return listFiles(file);
                }
            });
        } else {
            return Observable.just(f);
        }
    }
}
