import java.util.ArrayList;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail {
    public static ListNode findKthToTail(ListNode head,int k){
        if(head==null||k==0) {
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;
        while(--k>0){//需要执行k-1次，不能是k--（会执行k次）
            if(fast.next==null) {
                return null;//说明k比总结点数还大
            }
            fast=fast.next;
        }
        while(fast.next!=null) {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
        //大神超简洁代码
        /*for(;fast.next!=null;fast=fast.next){
            if(--k<=0) slow=slow.next;
        }
        return k>1?null:slow;*/
    }

    /**
     *找出链表中间结点
     */

    public static ListNode findMidNode(ListNode head){
        if(head==null) {
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next.next!=null){
            //因为2N/2=N;(2N+1)/2=N,所以只有当fast.next.next!=null时才把slow指针往后移一个
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     *判断链表是不是环形链表
     */
    public static boolean isLoopList(ListNode head){
        if(head==null) {
            return false;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next.next!=null){
            //因为2N/2=N;(2N+1)/2=N,所以只有当fast.next.next!=null时才把slow指针往后移一个
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow) {
                break;
            }
        }
        return fast==slow?true:false;
    }

    public static ListNode reverse(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        ListNode newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 0; i < 5; i++) {
            ListNode newnode = new ListNode(i + 1);
            p.next = newnode;
            p = p.next;
        }
        p = head;
       //head.print(p);
      //System.out.println(findMidNode(p).val);
        // System.out.println(isLoopList(heapd));
        ListNode.print(reverse(p));
    }
}
