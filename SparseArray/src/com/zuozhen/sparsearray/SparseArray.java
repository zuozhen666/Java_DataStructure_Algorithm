package com.zuozhen.sparsearray;

/*
 * 知识点：稀疏数组sparsearray
 * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * 处理方法：
 *   1）记录数组一共有几行几列，有多少个不同的值
 *   2）把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 *
 * 二维数组转稀疏数组的思路：
 *   1.遍历原始二维数组，得到要保存的有效数据的个数sum
 *   2.根据sum创建稀疏数组sparseArr int[sum+1][3]
 *   3.将二维数组的有效数据存入到稀疏数组
 *
 * 稀疏数组转二维数组的思路：
 *   1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组，chessArr2 = int[][]
 *   2.读取后几行的数据并赋给原始的二维数组
 * */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始二维数组11 * 11
        //0：表示没有棋子，1：表示黑子，2：表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 1;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row: chessArr1) {
            for(int data: row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }
        //二维数组转稀疏数组
        //1.遍历原始二维数组，得到要保存的有效数据的个数sum
        int sum = 0;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.根据sum创建稀疏数组sparseArr int[sum+1][3]
        int sparseArr[][] = new int[sum + 1][3];
        //3.将二维数组的有效数据存入到稀疏数组
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0;
        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("转化后的稀疏矩阵");
        for(int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //稀疏数组转二维数组
        //1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组，chessArr2 = int[][]
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.读取后几行的数据并赋给原始的二维数组
        for(int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("输出转化后的二维矩阵");
        for(int[] row: chessArr2) {
            for(int data: row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }
    }
}
