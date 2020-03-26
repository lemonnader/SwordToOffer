/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则
 */
public class ListMerge {
    /**
     *非递归方法
     */
    public static ListNode listMerge1(ListNode list1,ListNode list2){
        if(list1==null) {
            return list2;
        } else if(list2==null) {
            return list1;//某个链表为空，就直接返回另外一个链表的头节点就行了
        }
        ListNode newHead=new ListNode(0);//利用人造空结点就不用下面的一段代码了（防止形成环）
//        if(list1.val<=list2.val){
//            newHead=list1;
//            list1=list1.next;
//        }
//        else {
//            newHead=list2;
//            list2=list2.next;
//        }
        ListNode p=newHead;//需要先把头节点保存好，不然等运行完就找不到了，移到后面去了
        while (list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                p.next=list1;
                p=p.next;
                list1=list1.next;
            }
            else {
                p.next=list2;
                p=p.next;
                list2=list2.next;
            }
        }
        //哪个链表剩下了结点，就把指针指向哪个链表
        if (list1!=null){
            p.next=list1;
        }
        if (list2!=null){
            p.next=list2;
        }
        return newHead.next;//newHead是空结点，下一个才是真的头节点
    }

    /**
     *递归方法
     */
    public static ListNode listMerge2(ListNode list1,ListNode list2){
        if(list1==null) {
            return list2;
        } else if(list2==null) {
            return list1;//某个链表为空，就直接返回另外一个链表的头节点就行了
        }
        ListNode head;
        if(list1.val<=list2.val){
            head=list1;
            head.next=listMerge2(list1.next,list2);//递归解决两个链表剩下的部分
        }
        else {
            head=list2;
            head.next=listMerge2(list1,list2.next);
        }
        return head;//返回链表当前结点值比较小的那个
    }
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode p1 = list1;
        ListNode p2 = list2;
        for (int i = 1; i <=3; i+=2) {
            ListNode newnode1 = new ListNode(i + 2);
            ListNode newnode2 = new ListNode(i + 3);
            p1.next = newnode1;
            p1 = p1.next;
            p2.next = newnode2;
            p2 = p2.next;
        }
        ListNode.print(listMerge1(list1,list2));
    }
}
