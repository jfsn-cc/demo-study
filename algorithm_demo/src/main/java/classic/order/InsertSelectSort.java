package classic.order;

/**
 * @创建人 ly
 * @时间 03-06
 * @描述 插入排序与选择排序
 */
public class InsertSelectSort {
    private static int[]t1 = {5,1,4,3,19,0,38};

    public static void main(String[] args) {
        insertSort(t1);
        printArr(t1);
    }

    private static void printArr(int[] objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    /**
     * 选择排序
     * 交换数据，移动次数少， 逐一比较所有数据，获取最大最小值进行数据交换，构建有序集合
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int flag = 0, temp;
        for (int i = 0; i < arr.length-1; i++) {
            temp = arr[i];
            for (int j = i+1; j < arr.length-1; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    //更新需要交换的数据位置
                    flag = j;
                }
            }

            if (temp != arr[i]) {
                arr[flag] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 插入排序
     * 与冒泡交换次数一致，每次执行一次 有序度加一
     * 将前序集合作为有序集合，后续数据以冒泡方式向前移动，交换次数
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1, j; i < arr.length; i++) {
            int temp = arr[i];
            for (j = i-1; j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = temp;
        }
    }
}
