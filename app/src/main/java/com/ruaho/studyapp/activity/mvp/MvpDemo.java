package com.ruaho.studyapp.activity.mvp;

/**
 * Created by ruaho on 2018/1/31.
 * MVP信息
 */
public class MvpDemo {
/**
 http://blog.csdn.net/lmj623565791/article/details/46596109
 MVP中很明显，Model与View之间的交互由Presenter完成。

 项目结构
 model	实体模型 业务逻辑
 bean
 User	首先实体类User不用考虑这个肯定有，其次从效果图可以看到至少有一个业务方法login()，这两点没什么难度，我们首先完成：

 listener
 IUserBiz						业务方法login()
 OnLoginListener					实体类不用说，至于业务类，我们抽取了一个接口，一个实现类这也很常见~~login方法，一般肯定
 是连接服务器的，是个耗时操作，所以我们开辟了子线程，Thread.sleep(2000)模拟了耗时，由于是
 耗时操作，所以我们通过一个回调接口来通知登录的状态。

 UserBiz

 Presenter 负责完成View于Model间的交互				其实也是主要看该功能有什么操作，比如本例，两个操作:login和clear。
 pressenter
 UserLoginsPressenter

 View	对应于Activity，负责View的绘制以及与用户交互
 view
 IUserLoginView
 所以我们这里需要定义一个ILoginView，难点就在于应该有哪些方法，我们看一眼效果图：
 可以看到我们有两个按钮，一个是login，一个是clear；

 login说明了要有用户名、密码，那么对应两个方法：
 再者login是个耗时操作，我们需要给用户一个友好的提示，一般就是操作ProgressBar，所以再两个：
 login当然存在登录成功与失败的处理，我们主要看成功我们是跳转Activity，而失败可能是去给个提醒：
 login这个方法我们分析完了~~还剩个clear那就简单了：
 总结下，对于View的接口，去观察功能上的操作，然后考虑：
 该操作需要什么？（getUserName, getPassword）
 该操作的结果，对应的反馈？(toMainActivity, showFailedError)
 该操作过程中对应的友好的交互？(showLoading, hideLoading)



 1:登录
 2:显示loading






 1:布局文件
 activity_main

 2:依据布局文件考察有什么view层信息
 获取用户名
 获取密码

 进度条显示
 进度条隐藏

 登录成功
 登录失败

 清除数据信息


 3:实体模型

 4:业务逻辑
 1:抽象接口
 2:实体接口
 3:实体实现


*/
}
