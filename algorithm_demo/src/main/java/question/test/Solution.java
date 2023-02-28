package question.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

class Solution {

    public static void main(String[] args) {
        TreeNode node = new TreeNode();
        node.val = 3;
        node.left = new TreeNode(5);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(2);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(0);
     //   node.right.right.right = new TreeNode(3);
        System.out.println(maxDepth(node));
    }

    /**
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */



    /**
     * 输入: 先序遍历 preorder = [3,9,20,15,7], 中序遍历 inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * 通过根节点判断左字数是否存在，随后递归
     * @param
     * @return
     */
    //根据先序遍历和中序遍历确定二叉树
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]);
        int n = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                n = i;
                break;
            }
        }
        TreeNode node = root;
        //左子树构建
        for (int i = 1, j=n-1; i < n; i++) {
        }
        //右子树构建
        for (int i = n; i < preorder.length; i++) {
        }
        return root;
    }
   // public

    //深度遍历  递归判断
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int right = lengthFound(root.right, 0, 0);
            int left = lengthFound(root.left, 0, 0);
          return right > left ? right+1 : left+1;
        }

    }

    //递归操作
    public static int lengthFound(TreeNode node, int temp, int length) {
        if (node == null) {
            return length;
        }
        temp++;
        length = temp > length ? temp:length;
        if (node.left != null) {
            length = lengthFound(node.left, temp, length);
        }

        if (node.right != null) {
            length = lengthFound(node.right, temp, length);
        }
        return length;
    }

    //层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<TreeNode>> assistList = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        assistList.add(Collections.singletonList(root));
        result.add(Collections.singletonList(root.val));
        for (int i = 0; i < assistList.size(); i++) {
            List<TreeNode> list = assistList.get(i);
            if (list.isEmpty()) {
                break;
            }
            List<TreeNode> nodes = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            for (TreeNode node : list) {
                if (node.left != null) {
                    TreeNode treeNode = node.left;
                    nodes.add(treeNode);
                    integers.add(treeNode.val);
                }
                if (node.right != null) {
                    TreeNode treeNode = node.right;
                    nodes.add(treeNode);
                    integers.add(treeNode.val);
                }
            }
            if (!integers.isEmpty()) {
                result.add(integers);
            }
            assistList.add(nodes);
        }
        return result;
    }

    //二叉对称树
    public static boolean isSymmetric(TreeNode root) {
        if (root.right == null && root.left == null) {
           return true;
        } else if (root.right == null || root.left == null) {
            return false;
        }

        return compare(root.left, root.right, true);
    }
    //将左右子树分开递归然后比较
    public static boolean compare(TreeNode left, TreeNode right, boolean result) {
        if (result == true) {
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            if (left.left != null || right.right != null) {
                result = compare(left.left, right.right, result);
            }
            if (left.right != null || right.left != null) {
                result = compare(left.right, right.left, result);
            }
        } else {
            return false;
        }
        return result;
    }
    //判断是否二叉搜索树（中序遍历后是否升序）
    //递归所有结点判断是否左节点》根节点》右节点
    public static boolean isValidBST(TreeNode root) {
       return judge(root, root.left == null?root.val : root.left.val);
    }

    private static boolean judge(TreeNode node, Integer max) {
        if (node.left != null) {
            if (!judge(node.left, null)){
                return false;
            }
            if (node.val <= node.left.val || (max != null && node.left.val < max)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!judge(node.right, node.val)){
                return false;
            }
            if (node.val >= node.right.val) {
                return false;
            }
        }
        return true;
    }

    //本质上就是获取可构建的二叉树有多少
    public int numTrees(int n) {
        return n;
    }
    //各个节点探索
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j] == chars[0]) {
                    //fondEnd();
                }
            }
        }
        return true;
    }

    public boolean fondEnd(char[][] origin, char[] target, int nums, int[] position, Map<Integer, List<int[]>> map) {
        boolean state = true;
        while (state) {
            //if ()
        }
        return true;
    }
    /**
     * 全子集
     * 递归
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i<nums.length; i++) {
            int a = result.size();
            for (int j = 0; j<a; j++) {
                List<Integer> list = result.get(j);
                List<Integer> temp = new ArrayList<>(list);
                temp.add(nums[i]);
                result.add(temp);
            }
            result.add(Collections.singletonList(nums[i]));
        }
        result.add(new ArrayList<Integer>());
        return result;
    }

    public void sortColors(int[] nums) {
        int a = 0,b = 0,c = 0;
        for (int i : nums) {
            if (i == 0) {
                nums[c++] = 2;
                nums[b++] = 1;
                nums[a++] = 0;
            } else if (i == 1) {
                nums[c++] = 2;
                nums[b++] = 1;
            } else {
                nums[c++] = 2;
            }
        }
    }
    public static int climbStairs(int n) {
        int[] result = new int[n];
        if(n<=3) {
            return n;
        }
        for (int i = 0; i<3; i++) {
            result[i] = i+1;
        }
        if (n>3) {
            for (int j=3; j<n; j++) {
                result[j] = result[j-1] + result[j-2];
            }
        }
        return result[result.length-1];
    }

    /**
     * 动态规划？  爬楼梯一类问题，总结前面的情况
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        for (int i = 1; i<=grid[0].length; i++) {
            grid[0][i] = grid[0][i-1] + grid[0][i];
        }
        for (int i = 1; i<grid.length; i++) {
            grid[i][0] = grid[i-1][0] + grid[i][0];
        }
        for(int i=1; i<grid.length; i++) {
            for (int j=1; j<grid[i].length; j++){
                if (grid[i-1][j] > grid[i][j-1]) {
                   grid[i][j] += grid[i][j-1];
                } else {
                    grid[i][j] += grid[i-1][j];
                }
            }
        }
        return grid[grid.length-1][grid[grid.length-1].length-1];
    }

    //暴力递归
    public static int reduce(int m, int n, int result) {
        if (n == 0 || m == 0) {
            return ++result;
        }
        //右移
        while(n > 0) {
            n--;
            result = reduce(m, n, result);
            if (m > 0) {
                m--;
                result = reduce(m, n, result);
            }
        }

        return result;
    }
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> re = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        int j = 0;
        for (int i=1; i<intervals.length; i++) {
            if (right >= intervals[i][0]) {
                if (right < intervals[i][1]) {
                    right = intervals[i][1];
                }
            } else {
                re.add(new int[]{left, right});
                j++;
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        re.add(new int[]{left,right});
        return  re.toArray(new int[re.size()][]);
    }

    public static boolean canJump(int[] nums) {
        int right = nums.length - 1;
        for (int i=nums.length-2; i>=0; i--) {
            if (nums[i] >= right - i) {
                right = i;
            }
        }
        if (right == 0) {
            return true;
        } else {
            return false;
        }
    }
    //暴力破解 创建ListList<<String>> 每个字符做包含处理，存在不同的list。add().     错误方法
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length < 1)
            return new ArrayList();
        //把第一条数据保存
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(Collections.singletonList(strs[0])));
        // 判断每一个字符串
       l1: for (int j = 1; j< strs.length; j++) {
            //判断每一个源字符串
         l2:   for (int k = 0; k<result.size(); k++) {
                char[] temp = result.get(k).get(0).toCharArray();
                // 对目标字符串的每个字符做判断
                for (int i=0; i<strs[j].length(); i++) {
                    if (!search(temp, strs[j].charAt(i))){
                        //循环到最后一个源字符串
                         if (k == result.size()-1){
                             result.add(new ArrayList<>(Collections.singletonList(strs[j])));
                             continue l1;
                         } else {
                             continue l2 ;
                         }
                    } else if (strs[j].length() != temp.length && k == result.size()-1){
                        result.add(new ArrayList<>(Collections.singletonList(strs[j])));
                        continue l1;
                    } else if(strs[j].length() != temp.length)  {
                        continue l2;
                    }
                }
                result.get(k).add(strs[j]);
                break;
            }
        }
        return result;
    }

    public static boolean search(char[] temp, char key) {
        for (char a : temp){
            if (a == key) {
                return true;
            }
        }
        return false;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> op = new ArrayList<>();
        op.add(Collections.singletonList(nums[0]));
        List<List<Integer>> re = new ArrayList<>();
        insert(re, nums, 1, op);
        return re;
    }

    public static void insert(List<List<Integer>> result, int[] nums, int n, List<List<Integer>> operate) {
        //
        while(n < nums.length) {
            List<List<Integer>> middle = new ArrayList();
            for(List<Integer> list : operate) {
                for (int i=0; i<=list.size(); i++) {
                    List<Integer> temp = new ArrayList(list.size());
                    temp.addAll(list);
                    Collections.copy(temp, list);
                    temp.add(i, nums[n]);
                    middle.add(temp);
                }
            }
            n++;
            insert(result, nums, n, middle);
        }
        if (operate.get(0).size() == nums.length){
            result.addAll(operate);
        }
    }

    public int search(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            if(nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static boolean isValid(String s) {
        Map<Character,Character> map = new HashMap();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i<s.length(); i++) {
            if (map.get(s.charAt(i)) != null && !stack.empty()) {
                if (map.get(s.charAt(i)).equals(stack.lastElement())) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            } else {
                stack.push(s.charAt(i)) ;
            }
        }
        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    public static List<String> generateParenthesis(int n) {
        StringBuffer process = new StringBuffer();
        List<String> result = new ArrayList();
        for (int i=0; i<2*n; i++) {
            if (i < n) {
                process.append("(");
            } else {
                process.append(")");
            }
        }
        result.add(process.toString());
        //移左边

        for (int i = n; i>1; i--) {
            process.insert(i+1, '(');
            process.delete(i-1,i);
            result.add(process.toString());
            right(process.toString(), result, n);
        }
        return result;
    }

    static List<String> right(String pro, List<String> result, int n) {
        StringBuffer sb = new StringBuffer(pro);
        for (int i = n; i<sb.length()-2; i++) {
            sb.delete(i,i+1);
            sb.insert(i+1,'(');
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> letterCombinations(String digits) {
        String[] constant ={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> list = new ArrayList();
        if (digits == null || digits.length()<1) {
            return list;
        }
        String[] di = digits.split("");
        List<String> params = new ArrayList();
        for (int i=0; i<digits.length(); i++) {
            params.add(constant[Integer.valueOf(di[i])-2]);
        }
        for(int i=0; i<params.get(0).length(); i++) {
            list.add(""+params.get(0).charAt(i));
        }
        for (int i=1; i<digits.length(); i++) {
            int temp = list.size();
            for (int t=0; t<temp ; t++) {
                for (int j = params.get(i).length()-1; j>=0; j--) {
                    if (j == 0) {
                        list.set(t, list.get(t) + params.get(i).charAt(j));
                    } else {
                        list.add(list.get(t) + params.get(i).charAt(j));
                    }
                }
            }
        }
        return list;
    }
        //中序遍历
        public static List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            middle(root, result);
            return result;
        }

        public static void middle(TreeNode node, List<Integer> result) {
            if (node.left != null) {
                middle(node.left, result);
            }
            result.add(node.val);
            if (node.right != null) {
                middle(node.right, result);
            }
        }

    public void textList() {
        ArrayList<Integer> objects = new ArrayList<Integer>();
        objects.add(2);
        objects.remove(0);
        objects.addAll(new ArrayList<>());
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(1);
        Collection<Integer> integers = Collections.synchronizedCollection(objects);
        List<Integer> integers1 = Collections.synchronizedList(objects);
        integers1.add(1);
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(2);
    }

    public void testMap() {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("","");
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("","");
    }

    // 时间复杂度log（m+n） 获取中位数
    // 取中位数比较，存在四种情况
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = m/2;
        int right = n/2;

        if (nums1[left] < nums2[right]) {
            //分为三种情况
            forMiddle(nums1, nums2, left, right, m, n);
        } else {
            forMiddle(nums2, nums1, right, left, n, m);
        }
        return 0;
    }
    //递归算法
    public static double forMiddle(int[] nums1, int[] nums2,int left, int right, int m, int n) {
        if (nums1[left] >= nums2[right-1]) {
            if (nums1[left+1] >= nums2[right]) {
                if ((m+n)%2 == 0) {
                    return (double)(nums1[left] + nums2[right])/2;
                } else {
                    if (m > n) {
                        return nums1[left];
                    } else {
                        return nums2[right];
                    }
                }
            } else {


            }
        } else {

        }
        return 0;
    }

    //获取最大非零子矩阵
    public static int maxNums(int [][] example, int n, int m) {
        int sum = 0;
        //每个非零元素都进行子矩阵判断
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                if (example[i][j] != 0) {
                    //比较每个子矩阵的最大值
                    sum = Math.max(sum, dealNoZero(i, j, example, m, n));
                    // sum = Math.max(sum, dealNoZero(j, i, temp, n, m));
                }
            }
        }
        return sum;
    }

    public static int dealNoZero(int left, int top, int[][] example, int m, int n) {
        int sum = 0;
        int t = 0;
        List<Integer> count = new ArrayList<>();
        // lost数组保存遇到0时，对应的行和列的数值
        int lost[] = new int[2];
        for (int i = left; i < n; i++) {
            lost[1] = 0;
            for (int j = top; j < m; j++) {
                //不为零的时候相加
                if (example[i][j] != 0) {
                    sum += example[i][j];
                } else {
                    // 在最后一行以及下一行第一个元素为零时 进行一次最大子矩阵的判断
                    // 为0列的数值
                    for (int k = left; k < i; k++) {
                        for (int l = j; l < m; l++) {
                            lost[0] +=  example[k][j];
                        }
                    }
                    //遇到0将对应的子矩阵保存至数组
                    t = sum;
                    count.add(t - Math.min(lost[0], lost[1]));
                    //边界值变小
                    m=j;
                    continue;
                }
                lost[1] += example[i][j];
                if (i == n-1 && j == m-1) {
                    count.add(sum);
                }
            }
        }
        Integer result = count.stream().max((o1, o2) -> Integer.compare(o1, o2)).get();
        return result;
    }
    }

class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode() {}
   TreeNode(int val) { this.val = val; }
   TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
   }
}

