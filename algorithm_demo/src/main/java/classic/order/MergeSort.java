package classic.order;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @创建人 ly
 * @时间 03-09
 * @描述 归并排序 分而治之的思想，是插入排序的进阶版本
 */
public class MergeSort {
    private static int[]arr = {5,1,4,3,19,0,38,7};

    public static void main(String[] args) {
       // printArr(compare(new int[]{1,4,8}, new int[]{2,3,5}));
        int[] result = new int[arr.length];
        mergeSort1(arr, 0, (arr.length-1)/2, arr.length-1, result);
        printArr(arr);
    }

    private static void printArr(int[] objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }
    //初版，归并算法是分而治之，化为多个有序集合的合并然后逐一合并

    /**
     *
     * @param arr 原数组
     * @param left 左起点
     * @param middle 左终点 middle+1：右起点
     * @param right 右终点
     * @param result 辅助数组
     */
    public static void mergeSort1(int[] arr, int left, int middle, int right, int[] result) {
        if (right <= left) {
            return;
        }
        //分成多个小区间
        mergeSort1(arr, left, (left+middle)/2, middle, result);
        mergeSort1(arr, middle+1, (middle+1+right)/2, right, result);

        //进行数据比较
        int i = left;
        int k = left;
        int j = middle+1;
        while (left <= middle && j <= right) {
            result[i++] = arr[left] > arr[j] ? arr[j++] : arr[left++];
        }
        // arr 后有序序列大于前有序队列，就无需交换
        if (left != middle+1) {
            for (; left <= middle; left++) {
                result[i++] = arr[left];
            }
        }
/*        if (left != middle+1) {
            for (; left <= middle; left++) {
                result[i++] = arr[left];
            }
        }
        if (j != right+1) {
            for (; j <= right; j++) {
                result[i++] = arr[j++];
            }
        }*/
        for (; k <= right; k++) {
            arr[k] = result[k];
        }
    }

    //两个有序序列的比较合并
    public static int[] compare(int[] a, int[] b) {
        int[] arr = new int[a.length+b.length];
        int i = 0,j = 0;
        int temp = 0;
        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                arr[temp++] = b[j++];
            } else if (a[i] < b[j]) {
                arr[temp++] = a[i++];
            } else {
                arr[temp++] = a[i++];
                arr[temp++] = b[j++];
            }
        }
        if (i != a.length) {
            for (; i < a.length; i++) {
                arr[temp++] = a[i];
            }
        }
        if (j != b.length) {
            for (; j < b.length; j++) {
                arr[temp++] = b[j];
            }
        }
        return arr;
    }


}
