package com.ruaho.studyapp.mode.factory;


/**
 * https://blog.csdn.net/u011810352/article/details/78599946
 *
 * Product（抽象产品类）：要创建的复杂对象，定义对象的公共接口。
 * ConcreteProduct（具体产品类）：实现Product接口。
 * Factory（工厂类）：返回ConcreteProduct实例。
 *
 */
//抽象产品类;要创建的复杂对象，定义对象的公共接口。
public abstract class Product {
    public abstract void show();
}