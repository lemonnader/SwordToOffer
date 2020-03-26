import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode() {

    }
}

public class ReConstructBinaryTree {
    /**
     * 思路：根据前序遍历和中序遍历的特点，递归解决左子树和右子树。
     * 方法1：手动递归,递归方程：T(N)=T(N-1)+O(1)
     * 时间复杂度：介于O(N)和O(N²)
     * 运行时间：312ms;占用内存：22316k
     */
    public TreeNode reConstructBinaryTreeVersionOne(int [] pre,int [] in) {
        if(pre==null||in==null) {
            return null;
        }

        TreeNode tree=new TreeNode(pre[0]);//重建的二叉树的根结点是前序遍历的首元

        int head=0;
        int tail=in.length-1;//要解决的重建部分的中序遍历的头和尾的索引
        int midInPre=0;//当前树的中间结点在前序遍历中的索引

        reConstruct(tree,head,tail,midInPre,pre,in);

        return tree;
    }
    private void reConstruct(TreeNode root,int head,int tail,int midInPre,int[]pre,int[] in){
        int flag=0; //当前树的中间结点在中序遍历中的索引（作为划分左右子树的标志）
        for(;flag<pre.length;flag++){
            if(in[flag]==pre[midInPre]) {
                break;
            }
        }

        int leftLength=flag-head;
        int rightLength=tail-flag;//左右子树的长度
        //讨论左右子树存在性，分别用递归解决左右子树的重建
        //1.左子树存在
        if(leftLength>0){
            root.left=new TreeNode(pre[midInPre+1]);
            reConstruct(root.left,head,flag-1,midInPre+1,pre,in);
            //傻了..竟然把这两句写反了，下面的也是
        }
        //2.右子树存在
        if(rightLength>0){
            root.right=new TreeNode(pre[midInPre+leftLength+1]);
            reConstruct(root.right,flag+1,tail,midInPre+leftLength+1,pre,in);
        }
        else {
            return;//递归终止条件
        }


    }

    /**
     * 方法2：利用Arrays的静态函数copyOfRange进行递归，递归方程同上，注意函数左闭右开
     * 时间复杂度：介于O(N)和O(N²)【copyOfRange函数利用了system.arraycopy进行数组复制，效率很高】
     *运行时间：275ms；占用内存：24068k
     */
    public TreeNode reConstructBinaryTreeVersionTwo(int [] pre,int [] in){
        //if(pre==null||in==null) return null;不能这么表示..
        if(pre.length==0||in.length==0) {
            return null;
        }
        TreeNode tree=new TreeNode(pre[0]);
        int flag=0;
        for(;flag<in.length;flag++){
            if(in[flag]==pre[0]) {
                break;
            }
        }
        if(pre!=null&&in!=null){
            tree.left=reConstructBinaryTreeVersionTwo(Arrays.copyOfRange(pre,1,flag+1),Arrays.copyOfRange(in,0,flag));
            tree.right=reConstructBinaryTreeVersionTwo(Arrays.copyOfRange(pre,flag+1,pre.length),Arrays.copyOfRange(in,flag+1,in.length));
        }
        return tree;
    }

    public static void main(String[] args) {
        ReConstructBinaryTree re=new ReConstructBinaryTree();
        int[] pre={1,2,4,7,3,5,6,8};
        int[] in={4,7,2,1,5,3,8,6};
        TreeNode tr=re.reConstructBinaryTreeVersionTwo(pre,in);
        System.out.println("yes");
    }
}

