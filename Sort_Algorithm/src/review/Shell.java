package review;

import org.junit.Test;

import java.util.Arrays;

public class Shell {
    /*
    希尔排序：
    别名：缩小增量排序
    把序列按下标的一定增量分组，对每组使用直接插入排序算法排序；
    随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。

    相较于直接插入排序，Shell可以尽量避免移动次数过多的这种情况，提前将较大或者较小的数放在合适位置
     */
    @Test
    public void test() {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void shellSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        for (int gap = len / 2; gap > 0; gap /= 2) {//初始化增量为len/2
            for (int i = gap; i < len; i++) {//对每组使用直接插入排序算法排序
                int temp = arr[i];
                int insertIndex = i;
                while (insertIndex - gap >= 0 && temp < arr[insertIndex - gap]) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = temp;
            }
        }
    }
}
