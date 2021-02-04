package com.zuozhen.AVL;

/**
 * 平衡二叉树（二叉排序树的升级）（平衡二叉搜索树）
 * 背景：二叉排序树在构造的时候可能存在一些问题影响查询效率（例如原数组有序）
 * Self-balancing binary search tree
 * 特点：一棵空树or左右子树的高度差不超过1，并且左右两个子树都是平衡二叉树
 * 实现方法：红黑树，AVL，替罪羊树，Treap，伸展树等
 */
public class AVLTest {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] arr = {10, 7, 11, 6, 8, 9};
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().leftHight());
        System.out.println(avlTree.getRoot().rightHight());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

        if (rightHight() - leftHight() > 1) {
            //左旋
            //如果右子树的左子树高度大于右子树的右子树高度，那么先对右子树右旋
            if (right != null && right.rightHight() < right.leftHight())
                right.leftRotate();
            leftRotate();
            return;
        }
        if (leftHight() - rightHight() > 1) {
            //右旋
            //如果左子树的右子树高度大于左子树的左子树高度，那么先对左子树左旋
            if (left != null && left.rightHight() > left.leftHight())
                left.leftRotate();
            rightRotate();
            return;
        }
    }

    public int leftHight() {
        if (left == null) return 0;
        return left.hight();
    }

    public int rightHight() {
        if (right == null) return 0;
        return right.hight();
    }

    /**
     * 以当前节点为根节点的树的高度
     *
     * @return
     */
    public int hight() {
        return Math.max(left == null ? 0 : left.hight(), right == null ? 0 : right.hight()) + 1;
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
     * 左旋转
     */
    private void leftRotate() {
        //当前节点即根节点
        //创建新节点，以根结点的值创建
        Node newNode = new Node(val);
        //把新节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子节点的值
        val = right.val;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(val);
        newNode.right = right;
        newNode.left = left.right;
        val = left.val;
        left = left.left;
        right = newNode;
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
