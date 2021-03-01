package review;

import org.junit.Test;

import java.util.Arrays;

public class quick {
    /*
    快速排序：
    核心：Partition函数：
        对于一个序列A[0]~A[n]，调整序列元素的位置，使得A[0]的左侧元素都不超过A[0],
    A[0]的右侧元素都大于A[0]；
        思路：双指针
        1.将A[0]存到临时变量temp中，另两个下标left,right指向序列首尾；
        2.A[right]大于temp，即将right不断左移，
        当A[right]<=temp时，将A[right]挪到A[left]；
        3.A[left]<=temp，即将left不断右移，
        当A[left]>temp时，将A[left]挪到A[right];
        4.重复2，3直到left,right相遇，然后temp赋给相遇位置。

     快排思路：
        1.调整序列中的元素，使当前序列最左端的元素在调整后满足左侧所有元素均不
        超过该元素，右侧所有元素均大于该元素；
        2.对该元素左侧和右侧分别进行1的调整，直到当前调整区间的长度不超过1。
     */
    @Test
    public void test() {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, -900, 4561};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left > right) return;
        int pos = Partition(arr, left, right);
        quickSort(arr, 0, pos - 1);
        quickSort(arr, pos + 1, right);
    }

    public int Partition(int[] arr, int left, int right) {
        //其实也可随机选取主元
        //即区间内随机生成一个index，swap(arr[index],arr[left])即可，后续代码同下
        int temp = arr[left];
        while (left < right) {
            while (right > left && arr[right] > temp) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }
}
