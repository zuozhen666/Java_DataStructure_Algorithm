package review;

import org.junit.Test;

import java.util.Arrays;

public class merge {
    @Test
    public void test() {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, mid + 1, right);
        }
    }

    public void merge(int[] arr, int l1, int r1, int l2, int r2) {
        int i = l1, j = l2;
        int[] temp = new int[arr.length];
        int t = 0;
        while (i <= r1 || j <= r2) {
            if (i > r1) {
                temp[t++] = arr[j++];
            } else if (j > r2) {
                temp[t++] = arr[i++];
            } else if (arr[i] > arr[j]) {
                temp[t++] = arr[j++];
            } else {
                temp[t++] = arr[i++];
            }
        }
        t = 0;
        for (int k = l1; k <= r2; k++) {
            arr[k] = temp[t++];
        }
    }
}
