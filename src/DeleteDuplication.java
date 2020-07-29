import java.util.HashSet;

/**
 * @author LiMin
 * @Title: DeleteDuplication
 * @Description: 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * @date 2020/7/8  11:02
 */
public class DeleteDuplication {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 利用额外空间HashSet
     */
    public ListNode deleteDuplicationOne(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        HashSet<Integer> duplicate = new HashSet<>();
        ListNode newHead = new ListNode(-1);
        newHead.next = pHead;
        ListNode pre;
        ListNode cur = pHead;
        //先找出重复的结点
        while (cur.next != null) {
            if (cur.next.val == cur.val) {
                duplicate.add(cur.val);
            }
            cur = cur.next;
        }
        pre = newHead;
        cur = pHead;
        //再遍历一遍，删除重复结点
        while (cur != null) {
            if (duplicate.contains(cur.val)) {
                cur = cur.next;
                pre.next = cur;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return newHead.next;
    }

    /**
     * 双指针法
     */
    public ListNode deleteDuplicationTwo(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = pHead;
        ListNode pre = newHead;
        ListNode cur = pHead;
        while (cur != null && cur.next != null) {
            if (cur.next.val == cur.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return newHead.next;
    }
}
