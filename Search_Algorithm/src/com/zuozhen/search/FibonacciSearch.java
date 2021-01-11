package com.zuozhen.search;

import java.util.Arrays;

/**
 * 斐波那契查找(黄金分割法)
 * 黄金分割点：
 * 把一条线段分割成两部分，使其中一部分与全长之比等于另一部分与这部分之比。取其前三位的近似值为0.618
 * {
 * 设全长为1，x/1 = (1-x)/x -> x = 0.618
 * }
 *
 * 斐波那契数列：
 * 1，1，2，3，5，8，13，21，34，55...
 * f(k)/f(k+1)约等于0.618
 *
 * 基本思想：
 *  改变mid的位置，即mid位于黄金分割点附近
 * 即mid = low + F(k-1) -1
 * 解释：顺序表的长度F[k]-1
 * 那么可分为两段与一个mid，即  F[k]-1    =   F[k-1]-1   +  F[k-2]-1  +1
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr, 1));
    }

    /**
     * 非递归，生成斐波那契数列
     *
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     * 非递归
     *
     * @param a
     * @param key
     * @return 下标or-1
     */
    public static int fibonacciSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;  //表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();
        //获取到斐波那契分割数组的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足的部分使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上用a数组最后的数填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]) {
                high = mid - 1;
                //1.全部元素=前面的元素+后边元素
                //2.f[k]=f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid=f[k-1-1]-1
                k--;
            } else if(key > temp[mid]) {
                low = mid + 1;
                //因为后面有f[k-2]个元素，所以可以继续拆分f[k-2]=f[k-3]+f[k-4]
                //即在f[k-2]的前面继续查找k-=2
                //即下次循环mid=f[k-1-2]-1
                k -= 2;
            } else {
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
