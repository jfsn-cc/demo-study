package JavaSource;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @创建人 ly
 * @时间 03-22
 * @描述
 */
public class ListSource {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
    }
}

class SimulateList {
    public Object[] defaultArr = {};

    public int size;

    public Object[] arr;

    private int DEFAULT_COUNT = 10;

    public SimulateList() {
        arr = defaultArr;
    }

    public void add(Object o) {
        int len;
        //确认是不是初始化的数据
        if (this.arr == defaultArr) {
            len = DEFAULT_COUNT;
        } else {
            len = this.arr.length;
        }
        //首次插入数据以及发生越界数据进行扩容
        if (len - arr.length > 0)
            grow(len);
    }

    //扩容
    private void grow(int len) {
        int oldLen = arr.length;
        //新数组为原来数组的1.5倍，如果是初始化，newLen为零
        int newLen = oldLen + oldLen>>1;
        //如果len大于newLen，就使用len的容量大小
        if (len - newLen > 0) {
            newLen = len;
        }
        // 这里还会有一次溢出判断，长度超过
        if (newLen - Integer.MAX_VALUE - 8 > 0) {
            //报错oom
        }
        arr = Arrays.copyOf(arr, newLen);
    }
}