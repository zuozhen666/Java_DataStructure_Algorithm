package com.zuozhen.linkedlist;

/*
* josephu约瑟夫问题：
*   设编号1、2、、、n的人坐成一圈，约定编号为k的人开始报数，数到m的那个人出列，
*   他的下一位继续从1开始报数，数到m继续出列，以此类推，直到所有人出列为止，由此产生一个出队编号的序列。
*
*
* */
public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.Josephu(10,20,125);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;
    public void addBoy(int nums) {
        if(nums < 1) {
            System.out.println("nums not legal");
            return;
        }
        Boy curBoy = null;
        for(int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历
    public void showBoy() {
        if(first == null) {
            System.out.println("empty");
            return;
        }
        Boy curBoy = first;
        while(true) {
            System.out.printf("%d\n",curBoy.getNo());
            curBoy = curBoy.getNext();
            if(curBoy == first) {
                break;
            }
        }
    }

    //josephu具体实现

    /**
     *
     * @param startNo 表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums 表示最初的小孩个数
     */
    public void Josephu(int startNo, int countNum, int nums) {
        if(first == null || startNo < 1 || startNo > nums) {
            System.out.println("param wrong");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        while(true) {
            if(helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        for(int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while(true) {
            if(helper == first) {
                break;
            }
            for(int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("last one：%d\n", first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
