package com.zuozhen.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树：
 * 给定n个权值作为n个叶子节点，构造二叉树
 * 如果树的带权路径长度达到最小，这样的二叉树为最优二叉树，也称为哈夫曼树；
 * <p>
 * 树的带权路径长度WPL：所有的《叶子结点》的带权路径长度之和（weighted path length）
 * <p>
 * 构建哈夫曼树（给定数组）
 * 1.从小到大排序，将每个数据看成一个节点，每个节点可以看成一个简单的二叉树
 * 2.取出根节点权值最小的两个二叉树
 * 3.组成一个新的二叉树，新的二叉树根节点的权值是前面两个二叉树的根节点权值之和
 * 4.以根节点的大小再次排序，重复上述操作，直到。。。
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node node = create(arr);
        node.preOrder();
    }

    public static Node create(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

//支持排序，实现Comparable接口
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}