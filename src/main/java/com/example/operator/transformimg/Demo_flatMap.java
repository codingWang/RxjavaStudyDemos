package com.example.operator.transformimg;

import java.io.File;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by duwei on 2016/5/28.
 *
 * flatMap操作符是把Observable产生的结果转换成多个Observable，
 * 然后把这多个Observable“扁平化”成一个Observable，并依次提交产生的结果给订阅者。
 * flatMap操作符通过传入一个函数作为参数转换源Observable，在这个函数中，你
 * 可以自定义转换规则，最后在这个函数中返回一个新的Observable，
 * 然后flatMap操作符通过合并这些Observable结果成一个Observable，并依次提交结果给订阅者。
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
