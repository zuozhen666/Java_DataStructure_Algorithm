package zuozhen;

/**
 * 动态规划
 * 与分治不同：分解的子问题往往不是相互独立的
 */
public class Package {
    /*
    背包问题
    给定n个物品，设val[i],w[i]分别为第i个物品的价值和重量，C为背包的重量，
    令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值。
    按照物品遍历
    易知，
    v[0][j]=v[i][0]=0
    w[i]>j:v[i][j]=v[i-1][j]
    w[i]<=j:v[i][j] = max(v[i-1][j],v[i-1][j-w[i]]+val[i])
     */
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;//背包容量
        int n = val.length;//物品个数
        int[][] v = new int[n + 1][m + 1];

        //初始化
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i-1] > j) v[i][j] = v[i - 1][j];
                else v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - w[i-1]] + val[i-1]);
            }
        }
        System.out.println(v[n][m]);
    }
}
