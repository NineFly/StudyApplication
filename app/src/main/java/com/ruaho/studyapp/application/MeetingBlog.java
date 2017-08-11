package com.ruaho.studyapp.application;

/**
 * Created by ruaho on 2017/8/10.
 */
public class MeetingBlog {
/*
    Android 常见的面试题

    一:Java基础

    二:Android基础
        1. 项目怎么说
            先大致介绍, 再分层次去说
            1.1. 网络层
                1.1.1. 普通网络请求, 线程+Handler/AsyncTask/线程池, xUtils2.x(接口回调), JSON - JavaBean, Gson
                1.1.2. 普通数据缓存, 读本地, 先显示本地数据, 拿时间戳访问服务器,
                        如果有更新, 返回新数据+时间戳, 缓存到本地(应用私有目录/SD卡/数据库), 重新显示.
                1.1.3. 图片请求, 图片缓存
                1.1.4. 断点下载/上传, range, DownloadTask, 存数据库, Handler, DownloadListener, ViewHolder
                        比较有含金量
            1.2. 业务逻辑层
                1.2.1. JavaBean, 页面显示, 下拉刷新, 加载更多, 收藏, 登录, 分享, 商城(购物车, 支付), 百度地图
                1.2.2. 数据存储
                1.2.3. Service(起线程, 保持连接, 推送), BroadcastReceiver(起服务, 组件间通信工具)
            1.3. 页面层
                1.3.1. Activity, 抽取BaseActivity, onCreate, initView, initDate, 访问网络/回调,
                    用集合记录打开/关闭的Activity, 写一个方法退出应用, startAnActivity
                1.3.2. Fragment, show/hide, replace
                1.3.3. 常用控件, ListView, ViewPager(JazzyViewPager), LinearLayout, RelativeLayout, FrameLayout
                1.3.4. 自定义控件, 举例: 可拖动的GridView
                1.3.5. 屏幕适配, 除了新闻客户端讲的那几种, 再加上 PercentRelativeLayout、PercentFrameLayout
                    http://blog.csdn.net/zhaokaiqiang1992/article/details/45419023
                    http://developer.android.com/guide/practices/screens_support.html
                1.3.6. 动画
            1.4. 优化(掺杂在前三部分里)
                1.4.1. 布局优化, include, merge, ViewStub, addView/removeView
                1.4.2. 代码优化, 注解框架, ButterKnife, Dagger2, xUtils3, lint
                1.4.3. 内存/性能优化, OOM, 内存泄露, MAT
                1.4.4. 电量优化
                http://www.csdn.net/article/2015-01-20/2823621-android-performance-patterns/1
            1.5. WebView, HTML5, 5.0新特性, ...
        2. 自定义控件
            2.1. 怎么创建一个自定控件, 继承View/ViewGroup/已有控件, 构造方法串起来, 第三个参数
            2.2. onMeasure(测量, 两个参数), RatioFrameLayout, onLayout(布局, ViewGroup), 流式布局 FlowLayout
                 onDraw(绘制, canvas, paint, 不要在onDraw里new对象, Path), 快速索引
            2.3. onTouchEvent(返回值), invalidate, dispatchTouchEvent, onInterceptTouchEvent(ViewGroup)
                requestDisallowInterceptTouchEvent
            2.4. 回调, 定义状态, 定义回调接口, 回调方法(参数), 成员变量(set), 在适当位置调用回调方法, 外部使用
            2.5. 动画(帧动画, View/补间动画, 属性动画, 区别), 插补器, 估值器(TypeEvaluator) ValueAnimator
            2.6. ViewDragHelper, nineoldandroid.jar(没有真正改变view的属性)
        3. 图片缓存
            http://hukai.me/android-training-course-in-chinese/graphics/displaying-bitmaps/index.html
            DisplayingBitmaps
            3.1. 不能崩(OOM), 压缩处理,
                xUtils2.x com.lidroid.xutils.bitmap.BitmapDisplayConfig, optimizeMaxSizeByView
            3.2. 不能卡, 异步, Thread, AsyncTask
            3.3. 不能错位, ListView/GridView, setTag, AsyncDrawable
            3.4. 快, 缓存, 内存缓存(LruCache), 硬盘缓存
                final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
                // Use 1/8th of the available memory for this memory cache.
                final int cacheSize = maxMemory / 8;
                mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                    @Override
                    protected int sizeOf(String key, Bitmap bitmap) {
                        // The cache size will be measured in kilobytes rather than
                        // number of items.
                        return bitmap.getByteCount() / 1024;
                    }
                };
        4. Handler
            4.1. 为什么要有Handler
                4.1.1. 主线程不能做耗时操作, 要放在子线程
                4.1.2. 子线程不能修改主线程的UI, 就需要Handler
            4.2. new Handler(), 重写handleMessage, sendMessage, post, Message,
            4.3. 消息队列 MessageQueue, Looper.prepare, Looper.loop
            4.4. 所有的界面操作, 都依赖于Handler机制
            4.5. 子线程只要调用了 Looper.prepare, Looper.loop, 就可以在这两行代码之间使用WindowManager修改UI,
                主线程也不能修改子线程创建的UI
            4.6. Handler是线程间通信的工具





    三:Android提高

    四:Android高级

    五:算法

    六:人事


 */
}
