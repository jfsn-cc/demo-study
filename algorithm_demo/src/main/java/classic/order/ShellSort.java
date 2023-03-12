package classic.order;

/**
 * @创建人 ly
 * @时间 03-07
 * @描述 希尔排序
 */
public class ShellSort {
    private static int[]t1 = {5,1,4,3,19,0,38,7};

    public static void main(String[] args) {
        shellSort2(t1);
        printArr(t1);
    }

    private static void printArr(int[] objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }
    /**
     * 希尔排序是直接插入排序的进阶版本，
     * 可以先营造一个整体有序的列表，然后再进行插入排序
     * 设置步长，然后逐步缩小，直至完全有序
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int count = arr.length/2;
        int temp;
        for (int i = 0; i < count; i++) {
            if (arr[i] > arr[i+count]) {
                temp = arr[i];
                arr[i] = arr[i+count];
                arr[i+count] = temp;
            }
        }
        //3,1,0,5,19,4,38
        for (int i = 1,j; i < arr.length; i++) {
            temp = arr[i];
            for (j = i-1; j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            if (arr[i] != temp) {
                arr[j+1] = temp;
            }

        }
    }

    public static void shellSort2(int[] arr) {
        int step = arr.length;
        int temp = 0;
        while (step > 1) {
            step /= 2;
            for (int i = step, j; i < arr.length; i++) {
                temp = arr[i];
                for (j = i-step; j >= 0; j-=step) {
                    if (arr[j] > temp) {
                        arr[j+step] = arr[j];
                    } else {
                        break;
                    }
                }
                if (temp != arr[i]) {
                    arr[j+step] = temp;
                }
            }
        }
    }
}
