package com.ruaho.studyapp.mode.factory;


/**
 * 测试代码
 *          Factory.create("A").show();//生产ProductA
 *         Factory.create("B").show();//生产ProductB
 *         try {
 *             Factory.create("C").show();//生产ProductC
 *         } catch (NullPointerException e) {
 *             System.out.println("没有ProductC");//没有ProductC,会报错
 *         }
 *
 * 生成复杂对象时，确定只有一个工厂类，可以使用简单工厂模式。否则有多个工厂类的话，使用工厂方法模式。
 *
 * 优点:
 *  代码解耦，创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建。
 * 缺点:
 *  违背开放封闭原则，若需添加新产品则必须修改工厂类逻辑，会造成工厂逻辑过于复杂。
 *  简单工厂模式使用了静态工厂方法，因此静态方法不能被继承和重写。
 *  工厂类包含了所有实例（产品）的创建逻辑，若工厂类出错，则会造成整个系统都会会受到影响。
 *
 */

public class Factory {
    public static Product create(String productName) {
        Product product = null;
        //通过switch语句控制生产哪种商品
        switch (productName) {
            case "A":
                product = new ProductA();
                break;
            case "B":
                product = new ProductB();
                break;
        }
        return product;
    }
}