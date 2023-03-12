package classic.order;

import java.util.Arrays;


/**
 * @创建人 ly
 * @时间 0310
 * @描述 堆排序
 */

public class HeapSort {
    private static int[]arr = {5,1,4,3,19,0,38,56,343,567,23,44,33};
    public static void main(String []args){
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 数组当成一个完全二叉树的中序遍历
     * 构建堆 : 从最下层最右边子树递归，每一棵子树进行排序
     * 便于获取topk数据，出栈
     * @param arr
     */
    public static void buildHeap(int[] arr) {
        int length = arr.length;
        // 完全二叉树的最小右子树，节点= arr.length/2
        for (int i = arr.length/2-1; i >= 0 ; i--) {
            //将原始堆调整成符合条件的顺序堆
            changeHeap(arr, i, length);
        }
        for (int j = arr.length-1; j >= 0; j--) {
            // 获取极值
            swap(arr, 0, j);
            changeHeap(arr, 0, j);
        }
    }

    /**
     * 递归调整树顺序 每个最小子树进行比较调整顺序
     * @param arr
     * @param node 树父节点位置
     * @para 堆长度
     */
    public static void changeHeap(int[] arr, int node, int length) {
        //node的叶子节点
        int child = node*2;
        // 递归中断
        if (node > length/2-1)
            return;
        int temp = arr[node];
        //左节点存在并且大于右节点时候才有价值
        if (child+2 < length && arr[child+2] > arr[child+1]) {
            child++;
        }
        // 节点和叶子节点比较
        if (arr[child+1] > arr[node]) {
            arr[node] = arr[child+1];
            arr[child+1] = temp;
            //节点发生变更，子树也需要进行调整
            changeHeap(arr, child+1, length);
        }
    }


    public static void sort(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length) {
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1; k<length; k=k*2+1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k+1<length && arr[k]<arr[k+1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] >temp) { //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
