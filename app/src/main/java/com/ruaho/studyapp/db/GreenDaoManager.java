package com.ruaho.studyapp.db;

import com.ruaho.studyapp.application.App;

/**
 * Created by ruaho on 2017/8/9.
 */
public class GreenDaoManager {

    /**
     博客链接地址:
        地址一:Android实战——GreenDao3.2的使用，爱不释手  Hensen_ 鸿洋
             http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650821932&idx=1&sn
             =d26c09af7cbbfb1b0a95517bd78cc784&chksm=80b781b2b7c008a4a8dab45756e4d433
             b1c56e1c61762f98ee3b8a2b89a00756f82d6bb4d6b6&scene=0#rd

        地址二:GreenDao3.2.0项目的接入和简单的使用
            http://www.jianshu.com/p/dac3bd9bad72

     一:在build.gradle文件中添加如下内容:
        1:在app中的build.gradle 文件中添加
             apply plugin: 'com.android.application'
             apply plugin: 'org.greenrobot.greendao' // apply plugin

             //GreenDao数据库
             compile 'org.greenrobot:greendao:3.2.2' // add library
        2:在项目中build.gradle 文件中添加
            classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin

     二:在项目中定义一个实体类，并且使用@Entity和@Id标注
        例如项目中的User实体类
        说明:
             1;@Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
             2:@Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
             3:@Property：可以自定义字段名，注意外键不能使用该属性
             4:@NotNull：属性不能为空
             5:@Transient：使用该注释的属性不会被存入数据库的字段中
             6:@Unique：该属性值必须在数据库中是唯一值
             7:@Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

     三:定义好之后按ctrl+F9，也就是工具栏build下面的Make Project对项目进行重新构建，
        然后就在daoPackage 'com.zgl.greentest.gen'去看自动生成的代码了
        搜索: DaoMaster, DaoSession, UserDao 就成功

     四:使用部分，就是在别的地方怎么使用api来存取对象。首先是实例化GreenDao所需要的几个关
        键对象，DaoMaster，DaoSession，代码如下：
        例如: GreenDaoManager;

     五:在Application中去初始化：
         //GreenDao的初始化
         GreenDaoManager.getInstance();

     六:正式使用:
         public UserDao getUserDao(){
            return GreenDaoManager.getInstance().getmDaoSession().getUserDao();
         }

         public User getUser(Long id){
            return getUserDao().load(id);
         }
     */

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getAppContext(),
                "shopping_guide");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
