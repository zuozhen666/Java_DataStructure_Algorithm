package zuozhen;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(violenceMatch(str1, str2));
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
    }

    /**
     * 暴力字符串匹配
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int p1 = 0;
        int p2 = 0;
        while (p1 < s1Len && p2 < s2Len) {
            if (s1[p1] == s2[p2]) {
                p1++;
                p2++;
            } else {
                p1 = p1 - (p2 - 1);
                p2 = 0;
            }
        }
        //判断是否匹配成功
        if (p2 == s2Len) {
            return p1 - p2;
        } else return -1;
    }
    /*
    KMP:
    通过某种算法改进移动的效率（相较于暴力匹配）
    部分匹配值：前缀（集合）和后缀（集合）的最长的共有元素的长度
    1.先得到子串的部分匹配表
    2.然后。。。

     */

    /**
     * 得到部分匹配值表
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        int next[] = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * kmp算法
     *
     * @param str1
     * @param str2
     * @param next str2对应的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
