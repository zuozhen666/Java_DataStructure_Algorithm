package com.zuozhen.search;

        import java.util.Arrays;

/**
 * 插值查找
 * 二分查找的改进，优化了一下mid公式
 * 对于数据量较大，关键字分布比较均匀的数组优势很大
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int resIndex = insertValueSearch(arr,0,arr.length-1,100);
        //System.out.println(Arrays.toString(arr));
        System.out.print(resIndex);
    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            //添加的条件必须需要，如果缺失那么mid可能存在越界的情况
            return -1;
        }
        //插值查找算法核心
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
