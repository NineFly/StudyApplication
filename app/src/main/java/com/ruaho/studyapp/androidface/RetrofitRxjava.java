package com.ruaho.studyapp.androidface;

import android.util.Log;

import okhttp3.ResponseBody;

public class RetrofitRxjava {
    /*
    Retrofit    是对OKHttp的第二次封装;
    链接: https://www.jianshu.com/p/b25669052335

    Rxjava      异步和链式编程;
    链接: https://www.jianshu.com/p/cd3557b1a474

    结合起来的网络框架   网络请求封装框架
    链接: https://github.com/rengwuxian/RxJavaSamples
    */
    public static void main(){
        //Retrofit例子
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.baidu.com/").build();
//        BookService bookService = retrofit.create(BookService.class);
//        Call<ResponseBody> call = bookService.getBook("");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try{
//                    String s = response.body().string();
//                    Log.e("yulin", s);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("yulin", t.toString());
//            }
//        });

        //Rxjava例子
        //被观察者
//        Observable novel=Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("连载1");
//                emitter.onNext("连载2");
//                emitter.onNext("连载3");
//                emitter.onComplete();
//            }
//        });


        //观察者
//        Observer<String> reader=new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                mDisposable = d;
//                Log.e("yulin","onSubscribe");
//            }
//
//            @Override
//            public void onNext(String value) {
//                if ("2".equals(value)){
//                    mDisposable.dispose();
//                    return;
//                }
//                Log.e("yulin","onNext:"+value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("yulin","onError="+e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("yulin","onComplete()");
//            }
//        };
//        novel.subscribe(reader);//一行代码搞定

        //小说订阅了读者
        //这就是RxJava2.0最最简单的用法，创建小说，创建读者，建立订阅关系，记住这三步，你就能实现一个最简单的RxJava2.0的用法。

    }

}
