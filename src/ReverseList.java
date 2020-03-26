/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList {
    /**
     *非递归方法
     */
    public static ListNode reverseList1(ListNode head){
        if(head==null) {
            return null;//先判断链表是否为空
        }
        ListNode pre=null;
        ListNode next=null;//分别记录当前结点的前一个和后一个
        while(head!=null){//当前结点为空时说明当前结点的pre已经是尾节点了，返回pre
            next=head.next;//先把下一个节点保存下来，不然断链之后会找不到下一个
            head.next=pre;//当前结点的下一个指向pre（前一个结点）
            pre=head;
            head=next;//当前结点和pre都后移一个
            //最后的head（null）是和前面已经反转好的链表没连接起来的，所以需要返回pre
        }
        return pre;
    }
    /**
     * 递归方法
     */
    public static ListNode reverseList2(ListNode head){
        if(head==null||head.next==null) {
            return head;
        }
        //head==null主要是为了判断初始链表是否为空，head.next==null是为了满足递归终止条件
        ListNode temp=head.next;
        ListNode newHead=reverseList2(temp);
        temp.next=head;
        head.next=null;
        return newHead;
        //假设一共0~n-1个结点
        //最后：head=n-2,temp=n-1,返回newHead=n-1，null<--(n-2)<--(n-1)
        //弹出上一个状态的值，head=n-3,temp=n-2,null<--(n-3)<--(n-2)<--(n-1)。。继续
    }
}
