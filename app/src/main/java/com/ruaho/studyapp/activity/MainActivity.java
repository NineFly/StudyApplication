package com.ruaho.studyapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.goodcode.SocketConnection.activity.CochatSocketActivity;

import java.util.LinkedHashMap;

public class MainActivity extends BaseActivity {


    private static LinkedHashMap<String, String> _cachedGroups = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, CochatSocketActivity.class));
                break;
            case R.id.btn2:
                _cachedGroups.put("key","value");
                _cachedGroups.put("key","value_123");

                Log.e("输出","数据");

                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
            case R.id.btn7:
                break;
            case R.id.btn8:
                break;
            case R.id.btn9:
                break;
            case R.id.btn10:
                break;

        }
    }

}
