import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom {
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;//先检查二叉树是否为空
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);//先把根结点放进队列
        TreeNode temp;
        while(!queue.isEmpty()){
            //每当从队列中拿出最靠前的结点时就把它的左右儿子压入队列排队等待
            temp=queue.poll();
            result.add(temp.val);
            if(temp.left != null) {
                queue.offer(temp.left);
            }
            if(temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return result;
    }
}
