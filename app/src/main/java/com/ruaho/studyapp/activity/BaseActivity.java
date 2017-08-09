package com.ruaho.studyapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.ruaho.studyapp.utils.ToastsUtils;

/**
 * Created by ruaho on 2017/8/9.
 * Activity基类
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //吐司传递上下文进去
        ToastsUtils.register(this);

    }
}
