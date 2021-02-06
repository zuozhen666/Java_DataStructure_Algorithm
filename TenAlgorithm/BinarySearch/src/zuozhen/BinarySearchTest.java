package zuozhen;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySearch(arr, 67));
    }

    /**
     * 二分查找非递归实现
     *
     * @param arr    (升序)
     * @param target
     * @return 下标or-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
