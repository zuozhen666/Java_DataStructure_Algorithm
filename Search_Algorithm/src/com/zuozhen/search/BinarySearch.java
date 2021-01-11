package com.zuozhen.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 前提：有序数组
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 89);
        System.out.print(resIndex);
    }

    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 查找的值
     * @return 下标or-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if(left > right) return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
