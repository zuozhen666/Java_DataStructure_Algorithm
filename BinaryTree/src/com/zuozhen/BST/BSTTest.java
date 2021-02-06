package com.zuozhen.BST;

import com.sun.javafx.binding.SelectBinding;

import javax.xml.transform.Source;

/**
 * 二叉排序树：对于任意一个非叶子节点，左子节点比当前节点的值小，右子节点比当前节点的值大（也叫：二叉查找，二叉搜索树）
 * Binary Sort Tree
 */
public class BSTTest {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BST bst = new BST();
        for (int i = 0; i < arr.length; i++) {
            bst.add(new Node(arr[i]));
        }
        bst.infixOrder();//从小到大排列
        bst.delNode(9);
        bst.infixOrder();
    }
}

class BST {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("root is null");
        } else {
            root.infixOrder();
        }
    }

    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    /**
     * 返回以node为根节点的二叉排序树的最小节点的值
     *
     * @param node
     * @return
     */
    public int findMin(Node node) {
        Node target = node;
        //循环查找左节点
        while (target.left != null) {
            target = target.left;
        }
        return target.val;
    }
    /*
    二叉搜索树删除节点（重要）
    1.叶子节点
    2.节点含一个子树
    3.节点含两个子树
     */

    public void delNode(int value) {
        if (root == null) {
            System.out.println("root is null");
            return;
        } else {
            Node target = search(value);
            if (target == null) {
                System.out.println("target node not found");
                return;
            }
            //二叉排序树只有一个节点并且要删除
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            if (target.left == null && target.right == null) {
                //1.叶子节点
                if (parent.left != null && parent.left.val == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.val == value) {
                    parent.right = null;
                }
            } else if (target.left == null || target == null) {
                //2.节点含有一个子树
                if (target.left == null) {
                    if (parent.left != null && parent.left.val == value) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                } else {
                    if (parent.left != null && parent.left.val == value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                }
            } else {
                //3.节点含有两个子树
                //思路：找右子树的最小节点，然后用最小节点替换要删除节点即可
                //左子树找最大的也行
                int temp = findMin(target.right);
                delNode(temp);
                target.val = temp;
            }
        }
    }

}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public void add(Node node) {
        if (node == null) return;
        if (this.val > node.val) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.val == value) {
            return this;
        }
        if (value < this.val) {
            if (this.left != null) {
                return this.left.search(value);
            } else return null;
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else return null;
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.val == value) || (this.right != null && this.right.val == value)) {
            return this;
        }
        if (value < this.val && this.left != null) {
            return this.left.searchParent(value);
        } else if (value >= this.val && this.right != null) {
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }
}
