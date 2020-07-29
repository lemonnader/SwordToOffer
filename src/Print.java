import java.util.*;

/**
 * @author LiMin
 * @Title: Print
 * @Description: 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * @date 2020/7/15  21:12
 */
public class Print {
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
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        printStack(node1);
    }

    /**
     * BFS方式，利用队列
     */
    public static ArrayList<ArrayList<Integer>> printByBFS(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int level = 1;
        while (!queue.isEmpty()) {
            //关键点：每次只处理上次在queue中剩余的节点，这是上一层的所有节点。
            //处理完后刚好将下一层的所有节点（包含null）又全部放了进去。
            int sz = queue.size();//上一层的结点个数
            ArrayList<Integer> temp = new ArrayList<>();
            while (sz-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;//取到null要继续取
                }
                temp.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if ((level & 1) == 0) {//偶数层需要逆序
                Collections.reverse(temp);
            }
            if (temp.size() > 0) {//遍历最后一层时，入队的全是null，所以temp会是空的，不应该打印
                result.add(temp);
            }
            level++;
        }
        return result;
    }

    /**
     * 利用双栈
     */
    public static ArrayList<ArrayList<Integer>> printStack(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(pRoot);
        int level = 1;
        while (!stack1.empty() || !stack2.empty()) {
            TreeNode cur;
            ArrayList<Integer> temp = new ArrayList<>();
            if ((level & 1) == 1) {
                while (!stack1.empty()) {
                    cur = stack1.pop();
                    if (cur == null) {
                        continue;
                    }
                    temp.add(cur.val);
                    //null也可以入栈，就是没啥用，也可以判断一下，只放入非空结点
                    stack2.push(cur.left);
                    stack2.push(cur.right);
                }
            } else {
                while (!stack2.empty()) {
                    cur = stack2.pop();
                    if (cur == null) {
                        continue;
                    }
                    temp.add(cur.val);
                    stack1.push(cur.right);
                    stack1.push(cur.left);
                }
            }
            level++;
            if (temp.size() > 0) {
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> printBFS(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int length = queue.size();
            ArrayList<Integer> temp = new ArrayList<>();
            while (length-- > 0) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(temp);
        }
        return result;
    }
}
