package com.zuozhen.binaryTree;

public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        //测试中序线索二叉树
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "king");
        HeroNode2 node6 = new HeroNode2(14, "dim");

        //手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试：10号节点{8,3,10,1,14,6}
        HeroNode2 leftNode = node5.getLeft();
        HeroNode2 rightNode = node5.getRight();
        System.out.println("前驱节点：" + leftNode);
        System.out.println("后继节点：" + rightNode);
    }
}

//实现线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode2 root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的一个指针
    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    //重载线索化
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //对二叉树进行中序线索化
    public void threadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());
        //2.再处理当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //然当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //当前节点即为下一个结点的前驱节点
        pre = node;

        //3.最后线索化右子树
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("is empty");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("is empty");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("is empty");
        }
    }

    //前序遍历查找
    public HeroNode2 preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    //后序遍历查找
    public HeroNode2 infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    //中序遍历查找
    public HeroNode2 postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    //删除
    public void delNode(int no) {
        if (root == null) {
            System.out.println("root is null");
        } else {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }
    }
}

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    private int leftType;   //0，指向左子树；1，指向前驱节点
    private int rightType;  //0，指向右子树；1，指向后驱节点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name=" + name + "}";
    }

    /**
     * 递归删除节点
     * 规定：
     * 叶子直接删除
     * 非叶子删除子树
     *
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
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

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode2 preOrderSearch(int no) {
        System.out.println("enter preSearch");
        if (this.no == no) {
            return this;
        }
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("enter infixSearch");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("enter postSearch");
        if (this.no == no) {
            return this;
        }
        return null;
    }
}
