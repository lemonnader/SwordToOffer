/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubTree {
    public static boolean hasSubTree(TreeNode root1,TreeNode root2){
        if(root1==null||root2==null) {
            return false;//空树不是任意一个树的子结构
        }
        return isBigTreeSub(root1,root2)||hasSubTree(root1.left,root2)||hasSubTree(root1.right,root2);
        //短路特点：如果tree2是当前大树的子结构就不用进行后面的程序了
        //否则就判断tree2是不是当前大树的左子树的子结构..否则..右...
    }
    /**
     * 子函数:判断root2是不是以当前root1为根结点的大树的子结构
     * 两者根结点相等的话，就判断tree2的左子树和右子树是否同时满足是tree1的左、右子树的子结构
     * 否则就不是其子结构
     */

    public static boolean isBigTreeSub(TreeNode root1,TreeNode root2){
        if(root2==null) {
            return true;//初始时root1和root2都不是null，只有递归时才会出现root2==null
        }
        //而此情况（tree2先到达终点）包含root1==null和root1！=null两种情况，都应该返回true;
        //反之如果先判断root1==null时，root2==null和root2！=null两种情况返回值不相同
        if(root1==null) {
            return false;//tree1先到达终点
        }
        if(root1.val==root2.val)
            //找到相同的结点了!!开始看tree2的左子树和右子树是不是同时满足是tree2的左右子树的子结构
        {
            return isBigTreeSub(root1.left,root2.left)&&isBigTreeSub(root1.right,root2.right);
        }
            //短路特点：只要tree2的左子树不是tree1的左子树的子结构，就不用判断右子树了
        else {
            return false;
        }
    }
}
