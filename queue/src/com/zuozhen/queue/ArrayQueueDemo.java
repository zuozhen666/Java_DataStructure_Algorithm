package com.zuozhen.queue;

import java.util.Scanner;

/*
* 数组模拟队列
* 缺陷：数组只能使用一次
* 优化：环形
*/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("s(show)");
            System.out.println("e(exit)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("input a number");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int tmp = arrayQueue.getQueue();
                        System.out.printf("data:%d\n", tmp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int tmp = arrayQueue.headQueue();
                        System.out.printf("data:%d\n", tmp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayQueue {
    private int maxSize;    //最大容量
    private int front;      //队列头的前一个位置
    private int rear;       //队列尾
    private int[] arr;

    //构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = rear = -1;
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if(isFull()){
            System.out.println("isFull");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //出列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("isEmpty");
        }
        front++;
        return arr[front];
    }

    //显示队列数据
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("isEmpty");
            return;
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    //显示队列头
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("isEmpty");
        }
        return arr[front + 1];
    }
}
