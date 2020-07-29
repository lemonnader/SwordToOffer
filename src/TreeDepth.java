import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiMin
 * @Title: TreeDepth
 * @Description: 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * @date 2020/6/2018:14
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
class Test{
    private static int count=0;
    private int id=count++;

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}

public class TreeDepth {
    /**
     * 递归
     */
    public int TreeDepthOne(TreeNode root) {
        //递归截止条件
        if (root == null) return 0;
        return Math.max(TreeDepthOne(root.left), TreeDepthOne(root.right)) + 1;
    }

    /**
     * 非递归：层序遍历
     */
    public int TreeDepthTwo(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> temp = new LinkedList<>();
        temp.offer(root);
        int count = 0;
        while (!temp.isEmpty()) {
            int size = temp.size();
            while (size-- > 0) {
                TreeNode cur = temp.poll();
                if (cur.left != null) temp.offer(cur.left);
                if (cur.right != null) temp.offer(cur.right);
            }
            count++;
        }
        return count;
    }


    public static void main(String[] args) {
        Test test1=new Test();
        Test test2=new Test();
        Test test3=new Test();
        System.out.println(test1+" "+test2+" "+test3);
    }
}
