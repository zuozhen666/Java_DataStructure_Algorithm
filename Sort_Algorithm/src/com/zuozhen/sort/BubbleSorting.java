package com.zuozhen.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 内部排序-》交换排序-》冒泡排序
 * 通过对待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，
 * 若发现逆序则交换，使值较大的元素逐渐从前移向后部。
 * <p>
 * 优化：
 * 如果一趟比较下来没有进行过交换，就说明序列有序。
 * <p>
 * 1）一共进行数组大小-1次循环
 * 2）每一趟排序的次数在逐渐的减少
 * 3）如果在某趟中没有发生一次交换，可以提前结束
 */
public class BubbleSorting {
    public static void main(String[] args) {
        //int arr[] = {3, 9, -1, 10, 20};
        //测试花费时间，时间复杂度O(n^2)
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
        bubblesorting(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    public static void bubblesorting(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识变量，是否进行了交换
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (flag == false) {
                break;
            }
        }
    }
}
