import java.util.ArrayList;

/**
 * @author LiMin
 * @Title: GetNext
 * @Description:
 * @date 2020/7/10  21:17
 */
public class GetNext {
    private static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //        StringBuilder res = new StringBuilder();
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i) == '%') {
//                stack.push(i);
//            }
//            else if (input.charAt(i) == '#') {
//                int start = stack.pop();
//                //如有重复模板，则只传入最外层嵌套的重复模板
//                if (stack.isEmpty()) {
//                    res.append(helper(input.substring(start - 1, i + 1)));
//                }
//            }
//            else if (Character.isLetter(input.charAt(i))) {
//                if (stack.isEmpty())
//                    res.append(input.charAt(i));
//            }
//        }
//        out.println(res.toString());
//        out.close();
//    }
//
//    public static StringBuilder helper(String input) {
//        StringBuilder res = new StringBuilder();
//        int k = input.charAt(0) - '0';
//        int start = 1;
//        int end = input.length() - 1;
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 2; i < input.length() - 1; i++) {
//            if (input.charAt(i) == '%') {
//                stack.push(i);
//            } else if (input.charAt(i) == '#') {
//                start = stack.pop();
//                if (stack.isEmpty()) {
//                    res.append(helper(input.substring(start - 1, i + 1)));
//                }
//            } else if (Character.isLetter(input.charAt(i))) {
//                if (stack.isEmpty())
//                    res.append(input.charAt(i));
//            }
//        }
//        StringBuilder ret = new StringBuilder();
//        for (int i = 0; i < k; i++) {
//            ret.append(res);
//        }
//        return ret;
//
//    }
        // TODO Auto-generated method stub
        TreeLinkNode root1 = new TreeLinkNode(1);
        TreeLinkNode root2 = new TreeLinkNode(2);
        TreeLinkNode root3 = new TreeLinkNode(3);
        TreeLinkNode root4 = new TreeLinkNode(4);
        TreeLinkNode root5 = new TreeLinkNode(5);
        TreeLinkNode root6 = new TreeLinkNode(6);
        TreeLinkNode root7 = new TreeLinkNode(7);
        root1.left = root2;
        root1.right = root3;
        root1.next = null;
        root2.next = root1;
        root3.next = root1;

        root2.left = root4;
        root2.right = root5;
        root5.next = root2;
        root4.next = root2;

        root3.left = root6;
        root3.right = root7;
        root6.next = root3;
        root7.next = root3;
        System.out.println(getNextTwo(root3).val);
    }

    public static int flag;//标记pNode出现位置，设成全局变量

    /**
     * 暴力解法
     */
    public static TreeLinkNode getNextOne(TreeLinkNode pNode) {
        if (pNode == null) {
            return pNode;
        }
        TreeLinkNode p = pNode;
        //找到根结点
        while (p.next != null) {
            p = p.next;
        }
        ArrayList<TreeLinkNode> midNodes = new ArrayList<>();
        flag = -5;//随便给个负值，发现一个问题，如果只是在定义flag时赋值，因为静态变量只会被初始化一次，所以，在牛客的系统里面，用不同的测试用例多次调用本函数时，flag值
        // 会被上一次的调用所修改的值给覆盖掉，就导致了错误，所以应该在函数内部每次都赋值
        midOrder(p, midNodes, pNode);
        if (flag == midNodes.size()) {
            return null;
        } else {
            return midNodes.get(flag);
        }
    }

    /**
     * 中序遍历
     */
    public static void midOrder(TreeLinkNode root, ArrayList<TreeLinkNode> midNodes, TreeLinkNode pNode) {
        if (root.left != null) {
            midOrder(root.left, midNodes, pNode);
        }
        midNodes.add(root);
        //剪枝，遇到pNode之后再放进一个结点就可以返回了;如果pNode就是最后一个结点，那么flag=midNodes.size()之后就会返回了
        if (flag == midNodes.size() - 1) {
            return;
        }
        if (root == pNode) {
            flag = midNodes.size();
        }
        if (root.right != null) {
            midOrder(root.right, midNodes, pNode);
        }
    }

    /**
     * 找规律
     */
    public static TreeLinkNode getNextTwo(TreeLinkNode pNode) {
        if (pNode == null) {
            return pNode;
        }
        //有右子树
        if (pNode.right != null) {
            TreeLinkNode p = pNode.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //没有右子树
        else {
            TreeLinkNode root = pNode.next;
            while (root != null && pNode != root.left) {
                pNode = root;
                root = pNode.next;
            }
            return root;
        }
    }
}
