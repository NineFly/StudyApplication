package com.ruaho.studyapp.suanfa;

/*
* 冒泡排序法 口诀：
    外层循环 0到n-1    //控制比较轮数   n 表示元素的个数
    内层循环 0到n-i-1  //控制每一轮比较次数
            两两比较做交换
*/

public class MaoPao {
    public static void main(String[] args) {
        int arr[] = {1, 6, 0, -1, 9};
        int temp = 0;//中间值

        //-------冒泡排序法
        //外层循环,它决定一共走几趟
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环,开始逐个比较
            //如果我们发现前一个数比后一个数大,则交换
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    //换位
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }
        //输出结果
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
