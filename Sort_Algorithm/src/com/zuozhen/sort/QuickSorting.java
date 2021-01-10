package com.zuozhen.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 内部排序-》交换排序-》快速排序（冒泡的优化）
 * <p>
 * 基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行。
 */
public class QuickSorting {
    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70, -1, -900, 4561};
        //quickSorting(arr,0,arr.length-1);
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
        quickSorting(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void quickSorting(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        //while循环的目的是让比pivot小的值放在其左边，大的放在其右边
        while (l < r) {
            //在pivot左边一直找，直到找到大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot右边一直找，直到找到小于等于pivot的值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r，说明pivot左右两边的值已经按照均小于等于pivot和均大于等于pivot的规律存在了
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;


            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果l == r，必须l++, r--，否则栈溢出
        if(l == r) {
            l++;
            r--;
        }
        //向左递归
        if(left < r) {
            quickSorting(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSorting(arr, l, right);
        }
    }
}
