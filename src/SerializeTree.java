import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiMin
 * @Title: SerializeTree
 * @Description: 请实现两个函数，分别用来序列化和反序列化二叉树
 * @date 2020/7/16  15:00
 */
public class SerializeTree {
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
        node4.right = node5;
        node3.left = node6;
        node5.right = node7;
        SerializeTree serial = new SerializeTree();
        String str = serial.serialBFS(node1);
        TreeNode node0 = serial.deserialBFS(str);
        System.out.println(node0.val);
    }

    /**
     * 前序遍历序列化
     */
    public String serialPreOrder(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        preStrBuild(pRoot, builder);
        return builder.toString();
    }

    private void preStrBuild(TreeNode pRoot, StringBuilder builder) {
        if (pRoot == null) {
            builder.append("#!");
            return;
        }
        builder.append(pRoot.val + "!");
        preStrBuild(pRoot.left, builder);
        preStrBuild(pRoot.right, builder);
    }

    /**
     * 前序遍历反序列化
     */
    public TreeNode deserialPreOrder(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] strings = str.split("!");
        return preTreeBuild(strings);
    }

    private int index = 0;

    private TreeNode preTreeBuild(String[] strings) {
        TreeNode root = null;
        if (strings[index].equals("#")) {
            index++;
            return root;//"#"直接将结点返回，没有左右子结点
        }//equal比较字符串内容
        root = new TreeNode(Integer.parseInt(strings[index]));
        index++;//每用掉一个string，index就向前走一步
        root.left = preTreeBuild(strings);
        root.right = preTreeBuild(strings);
        return root;
    }

    /**
     * 层序遍历序列化
     */
    public String serialBFS(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            //null也放进队列
            TreeNode cur = queue.poll();
            if (cur == null) {
                builder.append("#!");
                continue;
            }
            builder.append(cur.val + "!");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return builder.toString();
    }

    /**
     * 层序遍历反序列化
     */
    public TreeNode deserialBFS(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] strings = str.split("!");
        int idx = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = new TreeNode(Integer.parseInt(strings[idx++]));
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (!strings[idx].equals("#")) {
                cur.left = new TreeNode(Integer.parseInt(strings[idx++]));
                queue.offer(cur.left);
            } else {
                idx++;//null结点不用再放进队列了，后面没有子树了;cur.left本来就是null
            }
            if (!strings[idx].equals("#")) {
                cur.right = new TreeNode(Integer.parseInt(strings[idx++]));
                queue.offer(cur.right);
            } else {
                idx++;//null结点不用再放进队列了，后面没有子树了;cur.right本来就是null
            }
        }
        return head;
    }
}
