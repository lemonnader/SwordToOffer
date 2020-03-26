import java.util.Stack;

/**
 * @ClassName BinaryTreeToDoubleLinkedList
 * @Discription ToDo
 * @Author lemon
 * @Date 2020/3/6 21:31
 **/
public class BinaryTreeToDoubleLinkedList {
    /**
     * 1.中序遍历，递归解法
     */
    private TreeNode preNode = null;
    private TreeNode newHead = null;

    public TreeNode convert1(TreeNode pRootOfTree) {

        if (pRootOfTree == null) {
            return newHead;
        }

        convert1(pRootOfTree.left);//递归左子树，递归完preNode应该是左子树的最右结点

        if (preNode == null) {
            preNode = pRootOfTree;//这一句一开始忘了加[囧]
            newHead = pRootOfTree;//左子树最左结点的处理：此结点应该是双向链表的表头
        } else {
            pRootOfTree.left = preNode;
            preNode.right = pRootOfTree;
            preNode = pRootOfTree;//建立根结点和左子树的最右结点的双向联系，并把preNode转向根结点
            //接下来第一个执行这段代码的pRootOfTree刚好是右子树的最左结点
        }

        convert1(pRootOfTree.right);//递归右子树

        return newHead;
    }

    public static void initOfMyTree(TreeNode root) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        root.left = node2;
        root.right = node4;
        node2.left = node1;
        node4.right = node5;
    }

    /**
     *2.非递归方法，利用栈
     */
    public TreeNode convert2(TreeNode pRootOfTree){
        if(pRootOfTree == null) {
            return newHead;
        }
        Stack<TreeNode> stack = new Stack<>();
        boolean isHead = false;
        while (pRootOfTree != null || !stack.empty()){
            //一路向左遍历到左子树最左叶节点，并把路径上的结点压栈
            while (pRootOfTree != null){
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            pRootOfTree = stack.pop();
            //第一次便利到的最左结点是新链表的头结点
            if(isHead == false){
                newHead = pRootOfTree;
                preNode = pRootOfTree;
                isHead = true;
            }
            else {
                pRootOfTree.left = preNode;
                preNode.right = pRootOfTree;
                preNode = pRootOfTree;//建立当前结点和前一个结点的联系，并更新前一个结点
            }
            pRootOfTree = pRootOfTree.right;//转向结点的右子树，重复以上步骤
        }
        return newHead;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        initOfMyTree(root);
        BinaryTreeToDoubleLinkedList my = new BinaryTreeToDoubleLinkedList();
        TreeNode head = my.convert1(root);
        while (head != null) {
            System.out.println(head.val + ",");
            head = head.right;
        }
    }
}
