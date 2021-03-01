package review;

import org.junit.Test;

import java.util.Arrays;

public class Heap {
    /*
    堆排序：
    堆：
    1.大顶堆：每个结点的值都大于或等于其左右孩子节点的值的完全二叉树
    2.小顶堆：每个结点的值都小于或等于其左右孩子节点的值的完全二叉树
    相关知识：
    （1）完全二叉树：所有叶子节点在最后一层或者倒数第二层，
    且最后一层的叶子节点在左边连续，倒数第二层的叶子节点在右边连续；
    （2）顺序存储二叉树：将一个数组模拟成二叉树，(层序分配序号)
    序号index对应的左子节点序号2*index+1，右子节点序号2*index+2，
    父节点(index-1)/2；

    基本思想：(增序排序)
        1.将待排序数组构造成一个大根堆，此时最大值即为堆顶；
        2.将堆顶元素与（堆）末尾元素交换，固定尾元素；
        3.将堆调整为大顶堆，执行2直至得到有序数组。
     */
    @Test
    public void test() {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void heapSort(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        //初始化大顶堆
        getHeap(arr);//{9,8,6,4,5}
        //调整大顶堆
        for (int length = len - 1; length > 0; length--) {
            int temp = arr[0];
            arr[0] = arr[length];
            arr[length] = temp;//堆顶元素移到末尾固定
            adjustHeap(arr, length);
        }
    }

    //方法1：将数组构造成大顶堆（元素上升）
    public void getHeap(int[] arr) {
        int len = arr.length;
        if (len <= 1) return;
        for (int i = 1; i < len; i++) {
            int j = i;
            while (j > 0 && arr[j] > arr[(j - 1) / 2]) {
                //当前插入节点的值大于父节点的值
                int temp = arr[j];
                arr[j] = arr[(j - 1) / 2];
                arr[(j - 1) / 2] = temp;
                j = (j - 1) / 2;
            }
        }
    }

    //方法2：调整堆为大顶堆（元素下降）
    public void adjustHeap(int[] arr, int length) {
        int index = 0;
        int left = 1;
        int right = 2;
        while (left < length) {
            int largestIndex;
            if (right < length && arr[left] < arr[right]) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }
            if (arr[index] > arr[largestIndex]) {
                break;
            }
            int temp = arr[index];
            arr[index] = arr[largestIndex];
            arr[largestIndex] = temp;
            index = largestIndex;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }
}
