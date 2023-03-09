package classic.order;

/**
 * @创建人 ly
 * @时间 03-08
 * @描述 快速排序，冒泡的分治
 */
public class QuickSort {
    private static int[]arr = {5,1,4,3,19,0,38,7};

    public static void main(String[] args) {
        int length = arr.length;
        quickSort2(0, length-1,arr[0]);
      //  quick_sort(arr, 0, length-1);
        printArr(arr);
    }

    private static void printArr(int[] objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    public static void quickSort1(int left, int right, int target) {
        int l = left;
        int r = right;
        if (l < r) {
            while (left < right) {
                // 当基准值<右侧数据，游标左移
                while (arr[right] > target)
                    right--;
                if (arr[right] < target){
                    swapNums(left, right);
                    left++;
                }
                while (arr[left] < target)
                    left++;
                if (arr[left] > target) {
                    swapNums(left, right);
                    right--;
                }
            }
            // 每次确定的target位置不发生变化，需要去除
            quickSort1(l, right-1, arr[l]);
            quickSort1(left+1, r,  arr[r]);
        }
    }

    //优化比较
    public static void quickSort2(int left, int right, int target) {
        int l = left;
        int r = right;
        int x = target;
        if (l < r) {
            while (left < right) {
                // 当基准值<右侧数据，游标左移
                while (arr[right] > target)
                    right--;
                if (left < right) {
                    arr[left] = arr[right];
                    left++;
                }
                while (arr[left] < target)
                    left++;
                if (left < right) {
                    arr[right] = arr[left];
                    right--;
                }
            }
            arr[right] = target;
            // 每次确定的target位置不发生变化，需要去除
            quickSort1(l, right - 1, arr[l]);
            quickSort1(left + 1, r, arr[r]);
        }
    }


    public static void swapNums(int right, int left) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    static void quick_sort(int a[], int l, int r)
    {
        if (l < r)
        {
            int i,j,x;

            i = l;
            j = r;
            x = a[i];
            while (i < j)
            {
                while(i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if(i < j)
                    a[i++] = a[j];
                while(i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if(i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quick_sort(a, l, i-1); /* 递归调用 */
            quick_sort(a, i+1, r); /* 递归调用 */
        }
    }
}
