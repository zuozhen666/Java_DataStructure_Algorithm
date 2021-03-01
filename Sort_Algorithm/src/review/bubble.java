package review;

import org.junit.Test;

import java.util.Arrays;

public class bubble {
    /*
    冒泡排序：
    通过对待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，
    若发现逆序则交换，使值较大的元素逐渐从前移向后部。
    优化：
    如果一趟比较下来没有进行过交换，就说明序列有序。
     */
    @Test
    public void test() {
        int[] arr = {3, 9, -1, 10, 20};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void bubbleSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        boolean isChanged = false;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isChanged = true;
                }
            }
            if (isChanged == false) {
                break;
            }
        }
    }
}
