package com.ruaho.studyapp.mode.builder;

import android.app.AlertDialog;
import android.app.Application;

/**
 * 建造者模式
 * 1: 定义 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 *
 * 1 定义一个静态内部类Builder，内部的成员变量和外部类一样
 * 2 Builder类通过一系列的方法用于成员变量的赋值，并返回当前对象本身（this）
 * 3 Builder类提供一个build方法或者create方法用于创建对应的外部类，
 *      该方法内部调用了外部类的一个私有构造函数，该构造函数的参数就是内部类Builder
 * 4 外部类提供一个私有构造函数供内部类调用，在该构造函数中完成成员变量的赋值，取值为Builder对象中对应的值
 */
public class Builder {


//    AlertDialog.Builder builder=new AlertDialog.Builder(this);
//    AlertDialog dialog=builder
//            .setTitle("标题")
//            .setIcon(android.R.drawable.ic_dialog_alert)
//            .setView(R.layout.myview)
//            .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            })
//            .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            })
//            .create();
//    dialog.show();

//    还有EventBus中也有一个Builder，只不过这个Builder外部访问不到而已，
//    因为它的构造函数不是public的，但是你可以在EventBus这个类中看到他的应用。

    //    再看看著名的网络请求框架OkHttp
//    Request.Builder builder = new Request.Builder();
//    Request request = builder.addHeader("", "")
//            .url("")
//            .post(body)
//            .build();

}
