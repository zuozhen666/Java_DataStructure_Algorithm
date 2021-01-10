package com.zuozhen.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序：
 * 内部排序-》插入排序-》直接插入
 * 基本思想：
 * 把n个待排元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
 * 无序表中包含n-1个元素，排序过程中每次从无序表中取出一个元素插入有序表中。
 */
public class InsertSorting {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        //insertSort(arr);

        //创建一个80000个元素的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成[0,8000000)的随机数
        }
        Date date1 = new Date();
        //格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);
        insertSort(arr);
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i <= arr.length - 1; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //保证不越界 && 待插入数还未找到插入位置
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }
    }
}
