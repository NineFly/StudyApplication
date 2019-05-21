package com.ruaho.studyapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruaho on 2017/8/9.
 * Fragment生命周期执行流程（注意红色的不是生命周期方法）：
 * Fragment创建：setUserVisibleHint()->onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()->onResume()；
 *
 * Fragment变为不可见状态（锁屏、回到桌面、被Activity完全覆盖）：onPause()->onSaveInstanceState()->onStop()；
 *
 * Fragment变为部分可见状态（打开Dialog样式的Activity）：onPause()->onSaveInstanceState()；
 *
 * Fragment由不可见变为活动状态：onStart()->OnResume()；
 *
 * Fragment由部分可见变为活动状态：onResume()；
 *
 * 退出应用：onPause()->onStop()->onDestroyView()->onDestroy()->onDetach()（注意退出不会调用onSaveInstanceState方法，因为是人为退出，没有必要再保存数据）；
 *
 * Fragment被回收又重新创建：被回收执行onPause()->onSaveInstanceState()->onStop()->onDestroyView()->onDestroy()->onDetach()，重新创建执行onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()->onResume()->setUserVisibleHint()；
 *
 * 横竖屏切换：与Fragment被回收又重新创建一样。
 */
public class LifeFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*  执行该方法时，Fragment与Activity已经完成绑定，该方法有一个Activity
        类型的参数，代表绑定的Activity，这时候你可以执行诸如
        mActivity = activity的操作。*/
        //test
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  初始化Fragment。可通过参数savedInstanceState获取之前保存的值。*/

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        /*  初始化Fragment的布局。加载布局和findViewById的操作通常在此函数
        内完成，但是不建议执行耗时的操作，比如读取数据库数据列表。*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*  执行该方法时，与Fragment绑定的Activity的onCreate方法已经执行完成并返回，
        在该方法内可以进行与Activity交互的UI操作，所以在该方法之前Activity的onCreate
        方法并未执行完成，如果提前进行交互操作，会引发空指针异常。*/

    }

    @Override
    public void onStart() {
        super.onStart();
        /*  执行该方法时，Fragment由不可见变为可见状态。*/

    }

    @Override
    public void onResume() {
        super.onResume();
        /*  执行该方法时，Fragment处于活动状态，用户可与之交互。*/

    }

    @Override
    public void onPause() {
        super.onPause();
        /*  执行该方法时，Fragment处于暂停状态，但依然可见，用户不能与之交互。*/

    }

    @Override
    public void onStop() {
        super.onStop();
        /*  执行该方法时，Fragment完全不可见。*/


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*  销毁与Fragment有关的视图，但未与Activity解除绑定，依然可以通
        过onCreateView方法重新创建视图。通常在ViewPager+Fragment的方式下会调用此方法。*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /* 销毁Fragment。通常按Back键退出或者Fragment被回收时调用此方法。*/

    }

    @Override
    public void onDetach() {
        super.onDetach();
        /*  销毁Fragment。通常按Back键退出或者Fragment被回收时调用此方法。*/

    }

    //-------------------------重要的回调方法-----------------------------------


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*  设置Fragment可见或者不可见时会调用此方法。在该方法里面可以通过调
        用getUserVisibleHint()获得Fragment的状态是可见还是不可见的，如果可见则进行懒加载操作。*/


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*  保存当前Fragment的状态。该方法会自动保存Fragment的状态，比如EditText键入的
        文本，即使Fragment被回收又重新创建，一样能恢复EditText之前键入的文本。*/

    }
}
