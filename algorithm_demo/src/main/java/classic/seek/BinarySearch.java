package classic.seek;

import java.lang.annotation.ElementType;

/**
 * @创建人 ly
 * @时间 02/28
 * @描述 折半查找
 */
public class BinarySearch {

    private static int[] t1 = {1,2,3};
    private static int[] t2 = {0,1,2,3};
    public static void main(String[] args) {
        System.out.println(test3(t1, 1));
        System.out.println(test3(t1, 3));
        System.out.println(test3(t2, 1));
        System.out.println(test3(t2, 2));
        System.out.println(test3(t2, -1));
        System.out.println(test3(t2, 5));
    }

    private static int test1(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int middle;
        while (right >= left) {
             middle = (left + right)/2;
            if (arr[middle] == target) {
              return middle;
            } else if (arr[middle] > target) {
                right = middle - 1;
            } else if (arr[middle] < target) {
                left = middle + 1;
            }
        }
        return -1;
    }
    //复写
    public static int test2(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int middle;
        while (left <= right) {
            middle = (left + right)/2;
            if (arr[middle] == target) {
              return middle;
            } else if (arr[middle] > target) {
                left = middle + 1;
            } else if (arr[middle] < target) {
                right = middle - 1;
            }
        }
        return -1;
    }
    //递归
    public static int test3(int[] arr, int target) {
        return judge(arr, 0, arr.length-1, target);
    }
    public static int judge(int[] arr, int left, int right, int target) {
        int middle = (left + right)/2;
        if (left > right)
            return -1;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] > target) {
            return judge(arr, left, middle - 1, target);
        } else {
            return judge(arr, middle + 1, right, target);
        }
    }
}
