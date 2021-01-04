package com.zuozhen.queue;

import java.util.Scanner;

/*
* 环形队列
* */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(3);
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
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("input a number");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int tmp = circleArrayQueue.getQueue();
                        System.out.printf("data:%d\n", tmp);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int tmp = circleArrayQueue.headQueue();
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

class CircleArrayQueue {
    private int maxSize;    //最大容量
    private int front;      //队列头
    private int rear;       //队列尾的后一个位置
    private int[] arr;

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = rear = 0;   //初始化为0
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("isEmpty");
        }
        int tmp = front;
        front = (front + 1) % maxSize;
        return arr[tmp];
    }

    //显示队列数据
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("isEmpty");
            return;
        }
        for(int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数目
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("isEmpty");
        }
        return arr[front];
    }
}