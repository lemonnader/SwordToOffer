/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class MirrorTree {
    public static void mirrorTree(TreeNode root){
        if(root==null) {
            return;
        }
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;//一定要交换结点才是交换了左右子树，如果仅仅是交换的val的话，底下的结构不会跟着改变
        if(root.left!=null) {
            mirrorTree(root.left);
        }
        if(root.right!=null) {
            mirrorTree(root.right);
        }
        //如果没有这两个判断语句的话，就算某个子树的根结点已经是null了，也还是会再执行一次函数
        //虽然直接return了，也尽力了压栈和出栈，比较两者的空间和时间消耗，还是判断一下吧
    }
}
