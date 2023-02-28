package question.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        /*ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next= new ListNode(9);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next= new ListNode(8);
        ListNode re = mergeTwoLists(l1,null).next;
        while (re != null) {
            System.out.println(re.val);
            re = re.next;
        }*/
    }

    public static void testf(int n, char[][]a, int r) {
        char first = a[0][0];
        int count = 0;
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            char[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                if (b[j] == first) {
                    first = b[0];
                    temp = i;
                    continue;
                }
            }
        }
        System.out.println(first);
        char l = 'a';
        if (r < a[temp].length) {
            l = a[temp][r];
        } else {

        }
        for (int i = 0; i < a.length; i++) {
            char[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                if (b[j] == first && (j+r) < a[i].length) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    public static void teste(int n, char[][]a, int r) {
        char first = a[0][0];
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            char[] b = a[i];
            for (int j = 0; j < b.length; j++) {
                if (map.get(b[j]) != null) {
                    map.put(b[j], map.get(map.get(b[j]))+1);
                } else {
                    map.put(b[j], 1);
                }
                if (b[j] == first) {
                    first = b[0];
                }
            }
        }
        System.out.println(first);
    }
    public static void testl(String[][]a, int r) {
        List list = new ArrayList(a.length);
        String first = a[0][0];
        for (int i = 0; i < a.length; i++)  {
            list.add(a[i][0]);
            for (String s : a[i]) {
                
            }
        }
        System.out.println(first);
    }

    public static int[][] reversal(int[][] nums) {
        int[][] result = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                result[i][j] = nums[nums.length - i-1][nums[i].length - j-1];
            }
        }
        return result;
    }


//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        merge(list1, list2, result);
        return result;
    }

    private static void merge(ListNode list1, ListNode list2, ListNode result) {
        if (list1 == null) {
            result.next = list2;
            return;
        }
        if (list2 == null) {
            result.next = list1;
            return;
        }
        if (list1.val <= list2.val) {
            result.next = list1;
            merge(list1.next, list2, result.next);
        } else {
            result.next = list2;
            merge(list1, list2.next, result.next);
        }

    }




    /*  给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int sum = 1;
        ListNode temp = new ListNode();
        temp = head;
        while(temp.next != null) {
            temp = temp.next;
            sum++;
        }
        if (sum - n == 0) {
            return head.next;
        }
        temp = head;
        for (int i = 0; i<=sum - n; i++) {
            if (i == sum-n-1) {
                ListNode delete = temp.next;
                temp.next = delete.next;
                delete.next = null;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}