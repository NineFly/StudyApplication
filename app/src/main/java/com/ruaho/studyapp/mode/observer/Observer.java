package com.ruaho.studyapp.mode.observer;

/**
 * 定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖他的对象都能得到通知并被自动更新
 *  情景一:有一种短信服务，比如天气预报服务，一旦你订阅该服务，你只需按月付费，
 *  付完费后，每天一旦有天气信息更新，它就会及时向你发送最新的天气信息。
 *
 *  情景二: 杂志的订阅，你只需向邮局订阅杂志，缴纳一定的费用，当有新的杂志时，
 *  邮局会自动将杂志送至你预留的地址。
 *
 *  观察者，我们称它为Observer，有时候我们也称它为订阅者，即Subscriber
 *  被观察者，我们称它为Observable，即可以被观察的东西，有时候还会称之为主题，即Subject
 *
 */
public interface Observer<T> {
    void onUpdate(Observable<T> observable,T data);

//    EventBus也是基于观察者模式的。观察者模式的三个典型方法它都具有，即注册，取消注册，发送事件
//    EventBus.getDefault().register(Object subscriber);
//EventBus.getDefault().unregister(Object subscriber);
//EventBus.getDefault().post(Object event);

//    还有一个地方就是Android的广播机制，其本质也是观察者模式，
//    这里为了简单方便，直接拿本地广播的代码说明，即LocalBroadcastManager。

//    看成是一对一的观察者模式
//    Button btn=new Button(this);
//btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Log.e("TAG","click");
//        }
//    });
}
