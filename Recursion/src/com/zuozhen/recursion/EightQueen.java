package com.zuozhen.recursion;

/*
* 八皇后
* 任意两个皇后都不能处于同一行，同一列，同一斜线上
*
* 思路：
* （1）第一个皇后先放在第一行第一列
* （2）把第二个皇后放在第二行第一列，然后判断是否OK。如果不OK，继续放在第二列、
* 第三列，依次把所有列都放完，找到合适位置
* （3）继续第三个皇后，还是第一列、第二列、直到第八个
* （4）当得到一个正确解，在栈退回到上一个栈时，就会开始回溯。即将第一个皇后，
* 放到第一列的所有正确解全部得到
* （5）继续将第一个皇后放在第二列，循环执行1，2，3，4
*
* 用一维数组来表示棋盘，下标表示行，值表示列
* */
public class EightQueen {

    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        //测试
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.printf("count：%d\n", count);
    }

    //放置第n个皇后
    private void check(int n) {
        if(n == max) {  //已经放置完成
            print();
            count++;
            return;
        }
        //依次放入皇后并判断是否冲突
        for(int i = 0; i < max; i++) {
            array[n] = i;
            if(judge(n)) {
                //接着放第n+1个皇后
                check(n + 1);
            }
        }
    }

    //查看放置第n个皇后时，检测该皇后是否和前面已经摆放的皇后冲突

    /**
     *
     * @param n 表示放第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for(int i = 0; i < n; i++) {
            //判断第n个皇后是否满足位置要求
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
