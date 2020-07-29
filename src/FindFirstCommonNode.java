import java.util.Stack;

/**
 * @author LiMin
 * @Title: FindFirstCommonNode
 * @Description: 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * @date 2020/6/1921:13
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class FindFirstCommonNode {
    public static void main(String[] args) {

        ListNode Node1 = new ListNode(1);
        ListNode Node2 = new ListNode(2);
        ListNode Node3 = new ListNode(3);
        ListNode Node4 = new ListNode(4);
        ListNode Node5 = new ListNode(5);
        ListNode Node6 = new ListNode(6);
        ListNode Node7 = new ListNode(7);
        ListNode Node8 = new ListNode(8);
        Node1.next = Node2;
        Node2.next = Node3;
        Node3.next = Node6;
        Node4.next = Node5;
        //Node5.next = Node6;
        //Node6.next = Node7;
        //Node7.next = Node8;
        FindFirstCommonNode findFirstCommonNode = new FindFirstCommonNode();
        System.out.println(findFirstCommonNode.findFirstCommonNodeThree(Node1, Node4).val);
    }

    /**
     * 先遍历两个链表找出两个链表的长度，计算出长度差k，让长链表先走k步，然后两个一起走，直到走到尾部或者两个链表指针对应的节点相同
     */
    public ListNode findFirstCommonNodeOne(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        if (pHead1 == pHead2) return pHead1;
        ListNode pLong = pHead1;
        ListNode pShort = pHead2;
        //先算出两个链表的长度
        int length1 = 0;
        int length2 = 0;
        while (pLong.next != null) {
            pLong = pLong.next;
            length1++;
        }
        while (pShort.next != null) {
            pShort = pShort.next;
            length2++;
        }
        //计算出差值
        int k = 0;
        if (length1 > length2) {
            k = length1 - length2;
            pLong = pHead1;
            pShort = pHead2;
        } else {
            k = length2 - length1;
            pLong = pHead2;
            pShort = pHead1;
        }
        //长的先走k步
        while (k-- > 0) {
            pLong = pLong.next;
        }
        //一起走找公共节点
        //这里不能写成pLong.next!=null&&pShort.next!=null,当只有一个公共节点时，公共节点的next==null，就会直接跳出while返回null
        while (pLong != null && pShort != null) {
            if (pLong == pShort) return pLong;
            pLong = pLong.next;
            pShort = pShort.next;
        }
        return null;
    }

    /**
     * 等长链表法，两个链表一起遍历，A走到尾部之后继续从B的头部走，B走到尾部之后继续从A的头部走,也就是构造成了两个等长链表，那么最后一定能同时遍历到第一个公共节点
     */
    public ListNode findFirstCommonNodeTwo(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        if (pHead1 == pHead2) return pHead1;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        //两种写法，里面有坑
        while (p1 != p2) {
            p1 = (p1 == null ? pHead2 : p1.next);
            p2 = (p2 == null ? pHead1 : p2.next);
            //不能写成  p1 = (p1.next==null ? pHead2 : p1.next);p2 = (p2.next==null ? pHead1 : p2.next);
            //当两个链表没有公共节点时，本来应该是p1和p2都等于null时退出while，但这种写法p1和p2取不到null就已经接上下一个头了，所以会有死循环
        }

//        while(p1!=p2){
//            p1 = p1.next;
//            p2 = p2.next;
//            if(p1 != p2){
//                if(p1 == null)p1 = pHead2;
//                if(p2 == null)p2 = pHead1;
//            }
//        }

        return p1;
    }

    /**
     * 利用辅助空间：栈，将两个链表数据分别压栈，依次出栈并比较栈顶元素，最后一对相等的栈顶元素就是第一个公共节点。
     */
    public ListNode findFirstCommonNodeThree(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        if (pHead1 == pHead2) return pHead1;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (pHead1 != null) {
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode result = null;
        //有相同的元素就更新result，然后一直同时pop，直到当前对应元素不相同，那么上一个被记录的相同元素就是我们要的结果
        //若第一个元素就不相同就会返回result的初始值：null
        while (!stack1.empty() && !stack2.empty()) {
            if (stack1.peek() == stack2.peek()) {
                result = stack1.pop();
                stack2.pop();
            } else return result;
        }
        return result;
    }
}
