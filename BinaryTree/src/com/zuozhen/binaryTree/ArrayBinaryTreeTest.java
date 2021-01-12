package com.zuozhen.binaryTree;


/**
 * 顺序存储二叉树实现，用数组存储
 * 应用场景：堆排序
 */
public class ArrayBinaryTreeTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;  //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        this.preOrder(0);
    }

    //顺序存储二叉树的前序遍历

    /**
     * 数组下标
     *
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
