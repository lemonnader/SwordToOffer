import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
class ListNode{
    int val;
    ListNode next=null;
    ListNode(int val){
        this.val=val;
    }
    public void setNext(ListNode node){
        this.next=node;
    }
    public static void print(ListNode head){
        while(head!=null){
            System.out.println(head.val);
            head=head.next;
        }

    }
}
public class PrintListFromTailToHead {
    /**
     *方法1.1：利用栈的先进后出
     * 复杂度：O(N)
     * 运行时间：17ms
     * 占用内存：9480k
     */
   public ArrayList<Integer>  printListFromTailToHeadOne(ListNode listNode){
       Stack<Integer> temp=new Stack<>();
       ArrayList<Integer> result=new ArrayList<>();

       while (listNode!=null){
           temp.push(listNode.val);
           listNode= listNode.next;
       }

       while(!temp.empty()){
           result.add(temp.pop());
       }
       return result;
   }

    /**
     * 方法1.2：利用递归和系统的"栈"
     * 递归方程：T(N)=T(N-1)+O(1)
     * 复杂度：O(N)
     * 运行时间：21ms
     * 占用内存：9368k
     */
    ArrayList<Integer> result2=new ArrayList<>();
    public ArrayList<Integer>  printListFromTailToHeadTwo(ListNode listNode){
        if (listNode!=null){//不小心写成了while就进入了死循环
           // System.out.println(listNode.val);//调试用
           printListFromTailToHeadTwo(listNode.next);
            result2.add(listNode.val);
        }
        return result2;
    }

    /**
     * 方法2：利用ArrayList的add方法，一直把新元素插到最前面,效率不高
     * 复杂度：O(N²)
     * 运行时间：28ms
     * 占用内存：9212k
     */
    public ArrayList<Integer>  printListFromTailToHeadThree(ListNode listNode){
        ArrayList<Integer> result3=new ArrayList<>();
        while(listNode!=null){
            result3.add(0,listNode.val);//查看源码可知此方法是依次复制
            // 插入位置及后面的数组元素，到后面一格，插入到最前面的复杂度为O(n)
            listNode=listNode.next;
        }
        return result3;
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
        PrintListFromTailToHead print=new PrintListFromTailToHead();
        ArrayList<Integer> test=print.printListFromTailToHeadOne(p);
        System.out.println(test);
    }
}
