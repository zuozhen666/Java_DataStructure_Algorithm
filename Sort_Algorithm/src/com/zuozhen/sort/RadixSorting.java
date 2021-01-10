package com.zuozhen.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * 1）属于“分配式排序”(distribution sort)，又称“桶子法”(bucket sort)或bin sort，
 * 顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用。
 * 2）基数排序法是属于稳定性的排序，是效率高的稳定性排序法。
 * 3）是桶排序的扩展
 * 4）将整数按位数切割成不同的数字，然后按每个位数分别比较
 * 基本思想：
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零，然后，从最低位开始，
 * 依次进行一次排序。这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
 * <p>
 * 实际过程：
 * 1.第一轮：
 * 1）将每一个元素的个位数取出，然后看这个数应该放在哪个对应的桶（一维数组）
 * 2）按照这个桶的顺序（一维数组下标）依次取出数据，放入原来数组
 * 2.第二轮：
 * 同上，这次取的是十位，第一轮桶内数据仍然存在，如果没有十位就放入标号为0的桶内（对应补零操作）
 * 3.第三轮：
 * 同上，这次取的是百位...
 * ...轮数取决于最大数的位数
 */
public class RadixSorting {
    public static void main(String[] args) {
        //int arr[] = {53, 3, 542, 748, 14, 214};
        //radixSorting(arr);
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
        radixSorting(arr);
        //System.out.println(Arrays.toString(arr));
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void radixSorting(int[] arr) {
        //得到数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        //定义一个二维数组代表10个桶
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数据
        //定义一个一维数组来记录各个桶每次放入的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
}
