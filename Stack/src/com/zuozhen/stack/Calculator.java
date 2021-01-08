package com.zuozhen.stack;

/*
 * 使用栈计算表达式
 * 1.扫描字符串，如果是数字，入数栈；
 * 2.如果是符号，分情况
 *   2.1符号栈为空，直接入栈
 *   2.2如果当前的操作符优先级小于或等于栈中的操作符，需要从数栈中pop出两个数，符号栈中pop出一个符号
 *   运算后结果入数栈，当前操作符入符号栈；否则直接入栈。
 * 3.扫描完毕后，顺序pop计算
 * 4.最后数栈里的数字即为结果
 * */
public class Calculator {
    public static void main(String[] args) {
        //测试
        String expression = "700+2*6+400";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;//扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepnum = "";
        while(true) {
            ch = expression.substring(index,index+1).charAt(0);
            //判断是符号还是操作符
            if(operStack.isOper(ch)) {
                if(!operStack.isEmpty()) {
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //numStack.push(ch - 48);
                //改进：处理多位数
                keepnum += ch;
                //判断下一个字符
                if(index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepnum));
                } else {
                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepnum));
                        keepnum = "";
                    }
                }
            }
            index++;
            if(index >= expression.length()) {
                break;
            }
        }
        while(true) {
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s=%d",expression,numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器

    public ArrayStack2(int maxSize) {
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
    //返回运算符的优先级，数字越大优先级越高
    public int priority(int oper) {
        if(oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }
    //判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '/' || val == '*';
    }
    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch(oper) {
            case'+':
                res = num1 + num2;
                break;
            case'-':
                res = num2 - num1;
                break;
            case'/':
                res = num2/num1;
                break;
            case'*':
                res = num1*num2;
                break;
        }
        return res;
    }
    //查看栈顶
    public int peek(){
        return stack[top];
    }
}