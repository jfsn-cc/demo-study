package classic.order;

import classic.ConstantsArray;

import java.util.Arrays;

/**
 * @创建人 ly
 * @时间 03-03
 * @描述 冒泡算法
 */
public class BubbleSort {
    private static int[]t1 = {5,1,4,3,19,0,38};
    private static int[]t2 = {0,1,4,19,38};
    public static void main(String[] args) {
        bubbleSort3(t2);
        System.out.println(Arrays.toString(t2));
    }
    public static void bubbleSort1(int[] arr) {
        //时间复杂都o(n)~o(n^2)
        for (int i = 0; i < arr.length-1; i++) {
            // 每一整次比较选定极值
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    //O(1)空间复杂， 交换数据
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 1无论数组数据如何，时间复杂度都是o(n),因此这里需要优化一下
     * 判断后续比较都符合排序要求时，停止对后续数据的冒泡排序
     * 冒泡算法
     */
    private static void bubbleSort2(int[] arr) {
        int flag = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                } else {
                    flag++;
                }
            }
            if (flag == arr.length-1-i) {
                break;
            } else {
                flag = 0;
            }
        }
    }

    //03-15
    public static void bubbleSort3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int flag = 0;
            int temp = arr[i];
            for (int j = 1; j < arr.length-i; j++) {
                if (arr[j-1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    flag++;
                }
            }
            //判断后续是否都有序, 每次比较都不会交换位置，i从0开始，
            if (flag == arr.length - i - 1) {
                break;
            }
        }
    }

}
