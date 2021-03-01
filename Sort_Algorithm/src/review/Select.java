package review;

import org.junit.Test;

import java.util.Arrays;

public class Select {
    /*
    简单选择排序：
    对于一个序列A，A[0]~A[n]
    令i从0到n枚举，每趟从未排序部分A[i]~A[n]中找出最小的元素
    然后与A[i]交换；
     */
    @Test
    public void test() {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void selectSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
