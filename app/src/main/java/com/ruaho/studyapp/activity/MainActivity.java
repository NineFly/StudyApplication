package com.ruaho.studyapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.goodcode.SocketConnection.activity.CochatSocketActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is git ceshi!


    }


    

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, CochatSocketActivity.class));
                break;
            case R.id.btn2:
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
