package com.ruaho.studyapp.activity;

import android.os.Bundle;
import android.view.View;

import com.ruaho.studyapp.R;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int[] arr = new int[]{11, 25, 45, 26, 12, 78};
//        sort(arr);

        int[] data = new int[] { 26, 53, 67, 48, 57, 13, 48, 32, 60, 50 };
        shellSortSmallToBig(data);
        System.out.println(Arrays.toString(data));
    }

    public static void shellSortSmallToBig(int[] data) {
        int j = 0;
        int temp = 0;
        for (int increment = data.length / 2; increment > 0; increment /= 2) {
            System.out.println("increment:" + increment);
            for (int i = increment; i < data.length; i++) {
                temp = data[i];
                for (j = i - increment; j >= 0; j -= increment) {
                     System.out.println("data[" + j + "]:" + data[j]);
                    if (temp < data[j]) {
                        data[j + increment] = data[j];
                    } else {
                        break;
                    }
                }
                data[j + increment] = temp;
            }
            for (int i = 0; i < data.length; i++){
                System.out.print(data[i] + " ");
            }
        }
    }

    public void sort(int[] arr) {
        int tmp;
        for(int i = 1; i < arr.length; i++) {
            // 待插入数据
            tmp = arr[i];
            int j;
            for(j = i - 1; j >= 0; j--) {
                // 判断是否大于tmp，大于则后移一位
                if(arr[j] > tmp) {
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = tmp;
            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }


    

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:

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
