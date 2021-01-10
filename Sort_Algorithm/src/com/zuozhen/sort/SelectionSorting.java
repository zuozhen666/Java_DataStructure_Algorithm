package com.zuozhen.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 内部排序-》选择排序
 * 基本思想：
 * 从arr[0]-arr[n-1]中找出最小值，然后与arr[0]交换，然后在arr[1]-arr[n-1]中找出最小值，与arr[1]交换...
 * 共n-1次。
 */
public class SelectionSorting {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1, -1, 90, 123};
        //selectSort(arr);
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
        selectSort(arr);
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    //时间复杂度：O(n^2)
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
