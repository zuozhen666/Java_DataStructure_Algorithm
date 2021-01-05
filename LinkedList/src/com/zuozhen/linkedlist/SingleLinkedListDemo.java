package com.zuozhen.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.list();
        //更新
        //HeroNode newHero4 = new HeroNode(4,"linchong","baozitou");
        //singleLinkedList.update(newHero4);
        //singleLinkedList.list();
        //删除
        //singleLinkedList.delete(1);
        //singleLinkedList.delete(4);
        //singleLinkedList.list();
        //长度
        //System.out.println(getLength(singleLinkedList.getHead()));
        //倒数第k项
        //HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
        //System.out.println(res);
        //翻转
        //reverseList(singleLinkedList.getHead());
        //singleLinkedList.list();
        //逆序打印
        reversePrint(singleLinkedList.getHead());
    }

    //面试题：获取单链表结点个数（头结点不统计）
    public static int getLength(HeroNode head) {
        if(head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode tmp = head.next;
        while(tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }
    //面试题：查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if(head.next == null) {
            return null;
        }
        int size = getLength(head);
        if(index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for(int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }
    //面试题：单链表反转
    public static void reverseList(HeroNode head) {
        if(head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverse = new HeroNode(0,"","");
        while(cur != null) {
            next = cur.next;
            cur.next = reverse.next;
            reverse.next = cur;
            cur = next;
        }
        head.next = reverse.next;
    }
    //面试题：从尾到头打印
    //way1：反转后打印，改变了结构，不宜采用
    //way2：利用栈
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        while(stack.size()>0) {
            System.out.println(stack.pop());
        }
    }
}


//链表
class SingleLinkedList {
    //初始化头结点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点
    public void add(HeroNode heroNode) {
        HeroNode tmp = head;
        while(true) {
            if(tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = heroNode;
    }
    //按照no插入节点
    public void addByOrder(HeroNode heroNode) {
        HeroNode tmp = head;
        boolean flag = false;//标记新插入节点标号是否存在
        while(true) {
            if(tmp.next == null) {
                break;
            }
            if(tmp.next.no > heroNode.no) {
                break;
            } else if (tmp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            System.out.printf("%d already existed\n", heroNode.no);
        }
        else {
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }
    //显示链表
    public void list() {
        if(head.next == null) {
            System.out.println("NULL");
            return;
        }
        HeroNode tmp = head.next;
        while(true) {
            if(tmp == null) {
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    //修改节点信息，根据no
    public void update(HeroNode heroNode) {
        if(head.next == null) {
            System.out.println("list is empty");
            return;
        }
        HeroNode tmp = head.next;
        boolean flag = false;//表示是否找到
        while(true) {
            if(tmp == null) {
                break;
            }
            if(tmp.no == heroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            tmp.name = heroNode.name;
            tmp.nickname = heroNode.nickname;
        }
        else {
            System.out.println("not found");
        }
    }

    //删除
    public void delete(int no) {
        HeroNode tmp = head;
        boolean flag = false;//是否找到
        while(true) {
            if(tmp.next == null) {
                break;
            }
            if(tmp.next.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.println("not found");
        }
    }

}
//节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //重写toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname+"]";
    }
}
