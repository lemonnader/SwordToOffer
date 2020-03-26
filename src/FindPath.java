import java.time.Clock;
import java.util.*;

public class FindPath {
    /**
     * 递归方法
     */
    private static ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    private static ArrayList<Integer> temp = new ArrayList<>();//为了防止在多次递归中被覆写，需要写在外面

    public ArrayList<ArrayList<Integer>> solve(TreeNode root, int target) {

        if (root == null) {
            return result;
        }
        temp.add(root.val);
        target -= root.val;

        if (target < 0) {
            temp.remove(temp.size() - 1);
            return result;
        }//剪枝，如果当前target已经等于零了，就没必要继续再往下找左子树和右子树的点了

        if (target == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(temp));//不能直接add(temp)，因为传递的是引用的值，也就是temp所指对象的内存地址
        }
        //当temp所指对象的内容改动时，result里面的对应位置上的元素所指的内容当然也会改动，因为是同一个地址
        //而新建一个对象只是利用了temp所指对象内存里面存储的值，没有建立地址联系，当temp之后再改变时新对象里面的值不会改变

        if (root.left != null) {
            solve(root.left, target);
        }
        if (root.right != null) {
            solve(root.right, target);
        }

        //其余情况：target>0并且root.left==null&&root.right==null
        temp.remove(temp.size() - 1);//回归到上一层去右子树里面找
        //回到上一层时，不需要再把target+=root.val，因为上一层的target是上上一层调用函数时压进栈里面临时存储的，
        //直接弹出来用，在外部对target进行操作不会影响栈里面的值

        return result;
    }

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        solve(root, target);

        if(result.size()>1) {
            //Collections.sort(result,new SizeComparator());//方式1.1，利用外部类对象

            //Collections.sort(result,comp);//方式1.2.1，匿名内部类

/*     //方式1.2.2，另外一种方式的匿名内部类
       Collections.sort(result, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size()-o1.size();
            }
        });*/

            //方式2：lambda表达式简化版（可以省略参数类型）
            Collections.sort(result, (a1, a2) -> a2.size() - a1.size());
        }
        return result;
    }

    //1.1内部类实现Comparator接口并重写compare方法
    static class SizeComparator implements Comparator<ArrayList<Integer>> {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            return o2.size() - o1.size();//降序
        }
    }

    //1.2.1匿名内部类方式，需要实现Comparator接口的compare方法
    private Comparator<ArrayList<Integer>> comp = new Comparator<ArrayList<Integer>>() {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            return o2.size() - o1.size();//降序
        }

    };

    /**
     *非递归方法
     */
    private static ArrayList<ArrayList<Integer>> result2 = new ArrayList<>();
    private static ArrayList<Integer> temp2 = new ArrayList<>();//为了防止在多次递归中被覆写，需要写在外面

    public  ArrayList<ArrayList<Integer>> findPath2(TreeNode root,int target){
        if(root==null) {
            return result2;
        }
        Stack<TreeNode> route=new Stack<>();
        while(root!=null||!route.empty()){//只要没有退回到大树的根结点，栈就一直不为空
            while (root!=null){
                route.push(root);
                temp2.add(root.val);
                target-=root.val;

                //剪枝
                if(target<0){
                    route.pop();
                    temp2.remove(temp2.size()-1);
                    target+=root.val;
                    if(!route.empty()&&route.peek().left==root&&route.peek().right!=null){//判断上一个结点是否为左子树
                        root=route.peek().right;
                        route.push(root);
                        temp2.add(root.val);
                        target-=root.val;
                    }
                    else {
                        root=null;
                    }
                }
                else {
                    root= (root.left!=null)?root.left:root.right;//只要有左子树，就一直把左子树结点沿着路径压入栈
                }
            }

            //当root==null时会跳出循环，这说明到了叶节点，有两个情况：
            // 上一个结点有左右儿子（这时栈顶是它的左儿子）或者只有右子树（这时栈顶是它的右子树）
            root=route.peek();
            if(target==0&&root.left==null&&root.right==null) {
                result2.add(new ArrayList<>(temp2));
            }
            //这条路径到了叶节点并且刚好和等于target，就可以把数组加进result了
            //准备回到上一层
            route.pop();
            temp2.remove(temp2.size()-1);
            target+=root.val;
            if(!route.empty()&&route.peek().left==root){//判断上一个结点是否为左子树
                root=route.peek().right;
            }
            else {
                root=null;//如果上一个节点就是右子树，就强制回到上一层。
            }
            // root=null能保证返回到上一层时不会进入内部小循环
        }
        return result2;
    }
    //完全二叉树生成函数
    public static TreeNode[] completeBinaryTree(int n){
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new TreeNode(i);
        }
        for (int i = 0; (2 * i + 1) < n; i++) {
            tree[i].left = tree[2 * i + 1];
            if ((2 * i + 2) < n) {
                tree[i].right = tree[2 * i + 2];
            }
        }
        return tree;
    }
    public static void main(String[] args) {
        FindPath find = new FindPath();
        TreeNode[] tree1=completeBinaryTree(20000);
        TreeNode[] tree2=tree1.clone();
        long s = Clock.systemDefaultZone().millis();
        find.findPath(tree1[0], 30067);
        System.out.println("递归耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
        System.out.println(result.toString());
        s = Clock.systemDefaultZone().millis();
        find.findPath2(tree2[0], 30067);
        System.out.println("非递归耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
        System.out.println(result2.toString());
        //递归耗时: 8 ms
        //[[0, 2, 6, 13, 28, 57, 116, 234, 469, 939, 1879, 3760, 7521, 15043]]
        //非递归耗时: 31 ms
        //[[0, 2, 6, 13, 28, 57, 116, 234, 469, 939, 1879, 3760, 7521, 15043]]
        //非递归反而更耗时，二叉树的DFS还是适合递归方法
        //并且，剪枝的效果当数据量变大时会特别明显
    }
}
