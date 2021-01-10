package com.zuozhen.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 内部排序-》归并排序
 */
public class MergeSorting {
    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        //int temp[] = new int[arr.length];
        //mergeSort(arr, 0, arr.length - 1, temp);
        //System.out.println(Arrays.toString(arr));
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
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归
            mergeSort(arr, left, mid, temp);
            //向右递归
            mergeSort(arr, mid + 1, right, temp);
            //
            merge(arr, left, mid, right, temp);
        }
    }
    //合并的方法

    /**
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       //初始化i，左边有序序列的初始索引
        int j = mid + 1;    //初始化j，右边有序序列的初始索引
        int t = 0;          //初始化t，指向temp数组的当前索引

        //（一）
        //先把左右两边的数据按照规则填充到temp数组
        //直到左右两边有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //（二）
        //把有剩余数据的一边依次填充到temp
        while (i <= mid) {//左边序列有剩余
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //（三）
        //将temp数组的元素copy到array
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
