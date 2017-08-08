package com.ruaho.studyapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.utils.ToastsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        ToastsUtils.register(this);

    }
}
