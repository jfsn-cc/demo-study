package classic.order;

import classic.ConstantsArray;

/**
 * @创建人 ly
 * @时间 03-03
 * @描述
 */
public class BubbleSort {
    private static int[]t1 = {5,1,4,3,19,0,38};
    public static void main(String[] args) {
        bubbleSort1(t1);
    }
    public static void bubbleSort1(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
