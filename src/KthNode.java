import java.util.Stack;

/**
 * @author LiMin
 * @Title: KthNode
 * @Description:
 * @date 2020/7/17  11:19
 */
public class KthNode {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        KthNode kth = new KthNode();
        System.out.println(kth.kthNodeRecursion(node1, 2).val);
    }

    /**
     * 中序遍历非递归（借助栈）
     */
    public TreeNode kthNodeByStack(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        TreeNode root = pRoot;
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;//一路压栈直到最左节点
            }
            root = stack.pop();//弹出索引值为i的结点
            if (i == (k - 1)) {//第k小的结点的索引值为k-1
                return root;
            }
            i++;
            root = root.right;//转向右子树，因为最左结点的right为null，所以弹出最左结点以后会继续弹出根节点，然后转向右子树
        }
        return null;
    }

    /**
     * 中序遍历递归：设置全局变量result和num；提前剪枝
     */
    public TreeNode kthNodeRecursion(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        recursion(pRoot, k);
        return result;
    }

    private int num = 0;
    private TreeNode result = null;

    private void recursion(TreeNode pRoot, int k) {
        if (num >= k) {
            return;//剪枝
        }
        if (pRoot.left != null) {
            recursion(pRoot.left, k);
        }
        num++;
        if (num == k) {
            result = pRoot;
            return;
        }
        if (pRoot.right != null) {
            recursion(pRoot.right, k);
        }
    }
}

