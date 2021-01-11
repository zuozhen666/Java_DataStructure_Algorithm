package com.zuozhen.search;

/**
 * 线性查找
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};
        int ans = seqSearch(arr, 11);
        System.out.print(ans);
    }

    /**
     * 找到一个满足条件的值，即返回下标，否则返回-1
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //线性查找，逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
