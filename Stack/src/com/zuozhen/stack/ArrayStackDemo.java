package com.zuozhen.stack;

import java.util.Scanner;

/*
* 数组模拟栈
* */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop) {
            System.out.println("show:");
            System.out.println("exit:");
            System.out.println("push:");
            System.out.println("pop:");
            key = scanner.next();
            switch(key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("input a number");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈数据：%d\n ", res);
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

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if(isFull()) {
            System.out.println("is full");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("empty");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    //遍历时需要从栈顶开始显示
    public void list() {
        if(isEmpty()) {
            System.out.println("empty");
            return;
        }
        for(int i = top; i >= 0; i--) {
            System.out.printf("%d\n", stack[i]);
        }
    }
}
