/**
 * @author LiMin
 * @Title: IsBalanced
 * @Description: 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * @date 2020/6/2415:59
 */
public class IsBalanced {
    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        TreeNode[] temp = new TreeNode[12];
        for (int i = 1; i <= 11; i++) {
            temp[i] = new TreeNode(i);
        }
        temp[1].left = temp[2];
        temp[1].right = temp[3];

        temp[2].left = temp[4];
        temp[2].right = temp[5];

        temp[4].left = temp[8];
        temp[8].left = temp[9];

        temp[3].left = temp[6];
        temp[3].right = temp[7];

        temp[7].right = temp[10];
        temp[10].right = temp[11];

        IsBalanced is = new IsBalanced();
        System.out.println(is.isBanlancedTwo(temp[1]));
    }

    /**
     * 可以利用上道题涉及到的求树的高度的方法，利用先序遍历的方法，先判断根节点所在大树是否满足平衡二叉树（递归时会求出所有节点的高度），然后判断左右子树是否满足
     */
    public boolean isBanlancedOne(TreeNode root) {
        if (root == null) return true;
        System.out.println(root.val);
        if (Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 && isBanlancedOne(root.left) && isBanlancedOne(root.right))
            return true;
        return false;
        //这道题牛客上的测试用例没涉及到左子树的高度与右子树的高度差的绝对值小于等于1但是左子树不是平衡二叉树或者右子树不是平衡二叉树的情况
        //所以直接判断左右子树高度差的绝对值是否小于等于1也能通过....
//        if(Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) return false;
//        return true;
    }

    public int getDepth(TreeNode root) {
        if (root == null) return 0;
        System.out.println(root.val);
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    /**
     * 利用后序遍历，先查看左右子树是否平衡，然后再判断根节点是否平衡；设置不平衡标志为-1，当某个子树不满足平衡时可直接返回至最初根节点，做到剪枝，提高效率。
     */
    public boolean isBanlancedTwo(TreeNode root) {
        return balanceDepth(root) == -1 ? false : true;
    }

    public int balanceDepth(TreeNode root) {
        if (root == null) return 0;
        System.out.println(root.val);
        int left = balanceDepth(root.left);
        if (left == -1) {
            System.out.println("剪枝" + root.val);
            return -1;
        }
        int right = balanceDepth(root.right);
        if (right == -1) {
            System.out.println("剪枝" + root.val);
            return -1;
        }
        if (Math.abs(left - right) <= 1) return Math.max(left, right) + 1;
        else return -1;
    }

    /**
     * 判断是否为二叉搜索树
     */
    public boolean isBinarySearchTree(TreeNode root) {
        if (root == null) return true;
        boolean flag = true;
        if (root.left != null && root.val < root.left.val) flag = false;
        if (root.right != null && root.val > root.right.val) flag = false;
        return flag && isBinarySearchTree(root.left) && isBinarySearchTree(root.right);
    }
}
