package zuozhen;

/**
 * 分治：
 * 1）分解
 * 2）解决
 * 3）合并
 * 举例：汉诺塔问题
 */
public class DivisionTest {
    /*
    汉诺塔问题思路（三个柱子A，B，C）
    1）1个盘 A->C
    n >= 2 总是可以抽象成两个盘1：最下面的盘，2：上面的盘
    1）最上面的盘 A->B
    2）最下面的盘 A->C
    3）B所有盘 B->C
     */
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("pan 1:" + a + "->" + c);
        } else {
            //1)最上面的盘 A->B
            hanoiTower(num - 1, a, c, b);
            //2)最下面的盘 A->C
            System.out.println("pan " + num + ":" + a + "->" + c);
            //3)B所有盘 B->C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
