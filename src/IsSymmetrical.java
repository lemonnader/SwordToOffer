import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author LiMin
 * @Title: IsSymmetrical
 * @Description: 请实现一个函数，用来判断一棵二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * @date 2020/7/15  15:31
 */
public class IsSymmetrical {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(6);
        TreeNode node10 = new TreeNode(7);
        TreeNode node11 = new TreeNode(8);
        TreeNode node12 = new TreeNode(8);
        TreeNode node13 = new TreeNode(7);
        TreeNode node14 = new TreeNode(6);
        TreeNode node15 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;
        node6.right = node13;
        node7.left = node14;
        node7.right = node15;
        System.out.println(isSymmetricalBFS(node1));

    }

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return judge(pRoot.left, pRoot.right);
    }

    public boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }//left和right不全为null
        if (left == null || right == null) {
            return false;
        }//left和right全不为null
        return left.val == right.val && judge(left.left, right.right) && judge(left.right, right.left);
        //left.left和right.right；left.right和right.left分别对应比较
    }

    public static boolean isSymmetricalDFS(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        Stack<TreeNode> tree = new Stack<>();
        tree.push(pRoot.left);
        tree.push(pRoot.right);
        System.out.println("input  " + pRoot.left.val + pRoot.right.val);
        while (!tree.empty()) {
            TreeNode left = tree.pop();
            TreeNode right = tree.pop();
            if (left == null && right == null) {
                //两个都为null就继续pop
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                //其中一个为null或者值不相等
                return false;
            }
            System.out.println("output  " + left.val + right.val);
            String leftleft = left.left == null ? "null" : String.valueOf(left.left.val);
            String leftright = left.right == null ? "null" : String.valueOf(left.right.val);
            String rightleft = right.left == null ? "null" : String.valueOf(right.left.val);
            String rightright = right.right == null ? "null" : String.valueOf(right.right.val);
            tree.push(left.left);
            tree.push(right.right);
            System.out.println("input  " + leftleft + rightright);
            tree.push(left.right);
            tree.push(right.left);
            System.out.println("input  " + leftright + rightleft);
        }
        return true;
    }

    public static boolean isSymmetricalBFS(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        Queue<TreeNode> tree = new LinkedList<>();
        tree.offer(pRoot.left);
        tree.offer(pRoot.right);
        System.out.println("input  " + pRoot.left.val + pRoot.right.val);
        while (!tree.isEmpty()) {
            TreeNode left = tree.poll();
            TreeNode right = tree.poll();
            if (left == null && right == null) {
                //两个都为null就继续pop
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                //其中一个为null或者值不相等
                return false;
            }
            System.out.println("output  " + left.val + right.val);
            String leftleft = left.left == null ? "null" : String.valueOf(left.left.val);
            String leftright = left.right == null ? "null" : String.valueOf(left.right.val);
            String rightleft = right.left == null ? "null" : String.valueOf(right.left.val);
            String rightright = right.right == null ? "null" : String.valueOf(right.right.val);
            tree.offer(left.left);
            tree.offer(right.right);
            System.out.println("input  " + leftleft + rightright);
            tree.offer(left.right);
            tree.offer(right.left);
            System.out.println("input  " + leftright + rightleft);
        }
        return true;
    }
}
