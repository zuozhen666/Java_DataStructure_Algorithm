package com.zuozhen.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 堆：
 * 1.大顶堆：每个结点的值都大于或等于其左右孩子节点的值的完全二叉树
 * 2.小顶堆：每个结点的值都小于或等于其左右孩子节点的值的完全二叉树
 */
public class HeapSorting {
    public static void main(String[] args) {
        //升序排列，即大顶堆
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        //三步走
        //1.将无序序列构建成一个堆，根据实际需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /*
         * 2.将堆顶元素与末尾元素互换，将最大元素“沉”到数组末端
         * 3.重新调整结构，使其满足堆定义，反复执行1，2直到整个序列有序
         * */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成大顶堆
     * 将以i对应的非叶子节点的树调整成大顶堆（局部）
     *
     * @param arr    待调整数组（二叉树）
     * @param i      非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        //开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点小于右子节点
                k++;//指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];
                i = k;//i指向k，继续循环比较
            } else {
                break;//从左至右，从下至上调整，所以可以直接break
            }
        }
        arr[i] = temp;
    }
}
