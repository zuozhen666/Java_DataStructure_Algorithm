package review;

import org.junit.Test;

import java.util.Arrays;

/*
插入排序-》直接插入排序
 */
public class Insert {
    /*
    直接插入：
    对于序列A，A[0]~A[n];
    从i=1开始，将A[i]插入到序列A[0]~A[i-1]中，使得A[0]~A[i]为有序；
    易知：需进行n趟操作。
     */
    @Test
    public void test() {
        int[] arr = {5, 2, 4, 6, 3, 1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));//[1, 2, 3, 4, 5, 6]
    }

    public void insertSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        for (int i = 1; i < len; i++) {
            int temp = arr[i], insertIndex = i;
            while (insertIndex > 0 && temp < arr[insertIndex - 1]) {
                arr[insertIndex] = arr[insertIndex - 1];//向右移动
                insertIndex--;
            }
            arr[insertIndex] = temp;
        }
    }
}
