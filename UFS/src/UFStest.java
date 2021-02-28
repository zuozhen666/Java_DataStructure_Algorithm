import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

public class UFStest {
    /*
    并查集：Union/Find/Set
    1.合并两个集合
    2.判断两个元素是否在一个集合
    int father[N];
    father[i]=j;元素1的父节点是2
    father[i]=i;根节点（同一集合只存在一个根节点，且将其作为所属集合的标识）
     */
    @Test
    public void test() {
        /*
        并查集基本操作
         */
        //初始化
//        for (int i = 1; i <= N; i++) {
//            father[i] = i;//令father[i]=-1也可
//        }
        //查找：对给定的节点寻找其根节点
        //递推
//        int findFather(int x) {
//            while (x != father[x]) {
//                x = father[x];
//            }
//            return x;
//        }
        //递归
//        int findFather(int x) {
//            if (x == father[x]) return x;
//            else return findFather(father[x]);
//        }
        //合并：给出两个元素，把两个元素所属集合合并。
        //先判断是否属于同一集合，只有属于不同集合才合并，合并过程
        //一般为把其中一个集合的根节点的父亲指向另一个集合的根节点。
//        void Union(int a, int b) {
//            int faA = findFather(a);
//            int faB = findFather(b);
//            if (faA != faB) {
//                father[faA] = faB;
//            }
//        }
        //并查集产生的每一个集合都是一颗树（而不是环）
    }

    @Test
    public void test1() {
        /*
        路径压缩
        将当前查询节点的路径上的所有节点的父亲都指向根节点；
         */
        //递推
//        int findFather (int x) {
//            int a = x;
//            while (x != father[x]) {
//                x = father[x];
//            }
//
//            while (a != father[a]) {
//                int z = a;
//                a = father[a];
//                father[z] = x;
//            }
//            return x;
//        }
        //递归
//        int findFather (int x) {
//            if(x = father[x]) return x;
//            else {
//                int F = findFather[father[x]];
//                father[x] = F;
//                return F;
//            }
//        }
    }

    /*
    应用题目举例：
        题目描述：
        1.A和B是好朋友等价于B和A是好朋友
        2.A和C是好朋友，B和C是好朋友，那么A和B也是好朋友
        现在给出所有好朋友的信息，问：可以把这些人分成多少组，
        满足每组中任意两人都是好朋友，且任意两组之间都不是好朋友。
        输入格式：
        第一行：正整数n、m（n为人数，m为好朋友组数，编号为1~n）
        接下来m行a、b表示a和b是好朋友。
        输出格式：
        输出一个整数，表示这些人可以分成的组数。

        样例输入：
        4 2
        1 4
        2 3
        输出：2

        样例输出：
        7 5
        1 2
        2 3
        3 1
        1 4
        5 6
        输出：3
     */
    private int[] father;
    private boolean[] isRoot;

    public int findFather(int x) {
        while (x != father[x]) {
            x = father[x];
        }
        return x;
    }

    public void Union(int a, int b) {
        int faA = findFather(a);
        int faB = findFather(b);
        if (faA != faB) {
            father[faA] = faB;
        }
    }

    public void init(int n) {
        father = new int[n + 1];
        isRoot = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            father[i] = i;
            isRoot[i] = false;
        }
    }

    @Test
    public void solution() {
        System.out.println("input something");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        init(n);
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            Union(a, b);
        }

        for (int i = 1; i <= n; i++) {
            isRoot[findFather(i)] = true;
        }
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            ret += isRoot[i] ? 1 : 0;
        }
        System.out.println(ret);
    }

}
