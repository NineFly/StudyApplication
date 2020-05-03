package com.ruaho.studyapp.activity;

import android.os.Bundle;
import android.view.View;

import com.ruaho.studyapp.R;
import com.ruaho.studyapp.mode.builder.Person;
import com.ruaho.studyapp.mode.observer.Observable;
import com.ruaho.studyapp.mode.observer.Observer;
import com.ruaho.studyapp.mode.observer.Weather;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable<Weather> observable=new Observable<Weather>();
        Observer<Weather> observer1=new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("观察者1："+data.toString());
            }
        };
        Observer<Weather> observer2=new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("观察者2："+data.toString());
            }
        };

        observable.register(observer1);
        observable.register(observer2);

        Weather weather=new Weather("晴转多云");
        observable.notifyObservers(weather);

        Weather weather1=new Weather("多云转阴");
        observable.notifyObservers(weather1);

        observable.unregister(observer1);

        Weather weather2=new Weather("台风");
        observable.notifyObservers(weather2);


//        Person.Builder builder = new Person.Builder();
//        builder.name("lisi").height(178).weight(65.7).build();
//
//
//
////        int[] arr = new int[]{11, 25, 45, 26, 12, 78};
////        sort(arr);
//
//        int[] data = new int[] { 26, 53, 67, 48, 57, 13, 48, 32, 60, 50 };
//        shellSortSmallToBig(data);
//        System.out.println(Arrays.toString(data));
//        //
//        CrashReport.testJavaCrash();
        //一

    }

    /** 冒泡排序 */
    private void setBubble(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len - 1; i++){
            for (int j= 0;j < len - 1 - i; j++){
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /** 选择排序    */
    private void setSelect(int[] arr){
        int len = arr.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++){
            minIndex = i;
            for (int j = i + 1; j<len - 1; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex]= temp;
        }
        return ;
    }

    /** 插入排序    */
    private void setInsert(int[] arr){
        int len = arr.length;
        int preIndex, current;
        for (int i = 1;i< len - 1; i++){
            preIndex = i -1;
            current = arr[i];

            while (preIndex >= 0 && arr[preIndex] > current){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;

        }
        return;
    }



    /**  希尔排序   */



    /**  归并排序   */
    private void setTogether(int[] arr){

    }


    /** 快速排序 */
    private void setQuickSort(int[] arr, int left, int right){
        int len = arr.length;
        int partitionIndex;
//                left = 0,
//                right = len - 1;
        if (left < right){
            partitionIndex = partition(arr, left, right);
            setQuickSort(arr, left, partitionIndex -1);
            setQuickSort(arr, partitionIndex + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right){
        int pivot = left,
                index = pivot + 1;
        for (int i = index; i <= right; i++){
            if (arr[i] < arr[pivot]){
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index -1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * 思路：最简单的思路，整数n每次进行无符号右移一位，同1做&运算，可以判断最后以为是否为1。
     *
     * 通常的剑指offer的思路，n&(n-1) 操作相当于把二进制表示中最右边的1变成0。所以只需要看看进行了多少次这样的操作即可。看看什么时候n为0.
     *
     * */
    private int setNum(int num){
        /*int count = 0;  //自己的思路，主要就是n与2 4 8 16分别与，来判断
    	long temp = 1;
    	for(int i = 1; i <= 32;i++){
    		if((n&temp) > 0)
    			count++;
    		temp = temp * 2;
    	}
        return count;*/
/*    	//简单的思路
    	int res = 0;
    	while (n!=0) {
	    res = res + n&1;
	    n>>>=1;
		}
    	return res;*/
//        int count = 0;
//        while(n!=0) {
//            n = n&(n-1);
//            count++;
//        }
//        return count;

        return 0;
    }





//    function quickSort(arr, left, right) {
//        var len = arr.length,
//                partitionIndex,
//                left = typeof left != 'number' ? 0 : left,
//                right = typeof right != 'number' ? len - 1 : right;
//
//        if (left < right) {
//            partitionIndex = partition(arr, left, right);
//            quickSort(arr, left, partitionIndex-1);
//            quickSort(arr, partitionIndex+1, right);
//        }
//        return arr;
//    }
//
//    function partition(arr, left ,right) {     // 分区操作
//        var pivot = left,                      // 设定基准值（pivot）
//                index = pivot + 1;
//        for (var i = index; i <= right; i++) {
//            if (arr[i] < arr[pivot]) {
//                swap(arr, i, index);
//                index++;
//            }
//        }
//        swap(arr, pivot, index - 1);
//        return index-1;
//    }
//
//    function swap(arr, i, j) {
//        var temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }


//    function insertionSort(arr) {
//        var len = arr.length;
//        var preIndex, current;
//        for (var i = 1; i < len; i++) {
//            preIndex = i - 1;
//            current = arr[i];
//            while (preIndex >= 0 && arr[preIndex] > current) {
//                arr[preIndex + 1] = arr[preIndex];
//                preIndex--;
//            }
//            arr[preIndex + 1] = current;
//        }
//        return arr;
//    }


//    function selectionSort(arr) {
//        var len = arr.length;
//        var minIndex, temp;
//        for (var i = 0; i < len - 1; i++) {
//            minIndex = i;
//            for (var j = i + 1; j < len; j++) {
//                if (arr[j] < arr[minIndex]) {     // 寻找最小的数
//                    minIndex = j;                 // 将最小数的索引保存
//                }
//            }
//            temp = arr[i];
//            arr[i] = arr[minIndex];
//            arr[minIndex] = temp;
//        }
//        return arr;
//    }

//    首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，
//    再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
//    以此类推，直到所有元素均排序完毕。
//
//            2.1 算法描述
//    n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
//
//    初始状态：无序区为R[1..n]，有序区为空；
//    第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
//            该趟排序从当前无序区中-选出关键字最小的记录 R[k]，
//            将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)
//    分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
//    n-1趟结束，数组有序化了。





    public static void shellSortSmallToBig(int[] data) {
        int j = 0;
        int temp = 0;
        for (int increment = data.length / 2; increment > 0; increment /= 2){
            System.out.println("increment:" + increment);
            for (int i = increment; i < data.length; i++){
                temp = data[i];
                for (j = i - increment; j >= 0; j -= increment){
                     System.out.println("data[" + j + "]:" + data[j]);
                    if (temp < data[j]){
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

    public void sort(int[] arr){
        int tmp;
        for(int i = 1; i < arr.length; i++){
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
