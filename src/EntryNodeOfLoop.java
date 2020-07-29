import java.util.HashSet;

/**
 * @author LiMin
 * @Title: EntryNodeOfLoop
 * @Description: 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * @date 2020/7/7  20:27
 */
public class EntryNodeOfLoop {
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 哈希法，需要利用额外空间
     */
    public ListNode entryNodeOfLoopOne(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        HashSet<ListNode> listNodes = new HashSet<>();
        while (pHead != null) {
            if (listNodes.contains(pHead)) {
                return pHead;
            }
            listNodes.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 双指针法
     */
    public ListNode entryNodeOfLoopTwo(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null && fast.next != null) {//如果没有环的话肯定是fast先到尾巴
            fast = fast.next.next;
            slow = slow.next;//一开始fast走得快
            if (slow == fast) {//一旦相遇就说明有环
                fast = pHead;//fast重新指向头部
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;//跳出上面这个while循环时肯定slow==fast即再次相遇
            }
        }
        return null;
    }
}
