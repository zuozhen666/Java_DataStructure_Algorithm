import java.util.Arrays;
import java.util.Comparator;

public class leetcode {
    public int[][] reconstructQueue(int[][] people) {
        /*
        先按照身高从小到大（相同身高看K从大到小）排序；
        然后依次将i放入第ki+1个空位。
        */
        if (people.length == 0) return people;
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        int[][] ret = new int[people.length][];
        for (int[] person : people) {
            int site = person[1] + 1;
            for (int i = 0; i < ret.length; i++) {
                if (ret[i] == null) site--;
                if (site == 0) {
                    ret[i] = person;
                    break;
                }
            }
        }
        return ret;
    }
}
