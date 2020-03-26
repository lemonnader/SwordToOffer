import java.util.HashMap;
import java.util.Random;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针
 * 指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，
 * 否则判题程序会直接返回空）
 */
public class RandomListNodeSolution {

    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        public static void print(RandomListNode head){
            RandomListNode p=head;
            while(p != null){
                System.out.print(p.label);
                p = p.next;
            }
            System.out.println();
            p = head;
            while(p != null){
                if(p.random == null){
                    System.out.print('#');
                }else{
                    System.out.print(p.random.label);
                }
                p = p.next;
            }
            System.out.println();
        }
    }

    /**
     * 1.加长拆分法
     */
    public static RandomListNode clone(RandomListNode pHead){
        if(pHead == null) {
            return null;
        }
        RandomListNode oldNode = pHead;
        RandomListNode temp;
        //第一步：复制结点，加长链表
        while (oldNode != null){
            temp = new RandomListNode(oldNode.label);
            temp.next = oldNode.next;
            oldNode.next = temp;
            oldNode = temp.next;
        }
        oldNode = pHead;//重新回到原点
        //第二步：建立新复制的链表结点的random关系
        while (oldNode != null){
            temp = oldNode.next;
            if (oldNode.random == null) {
                temp.random = null;//random指向null的情况
            }
            //random指向孤立结点的情况
            else if(oldNode.random.next == null) {
                temp.random = new RandomListNode(oldNode.random.label);
            } else {
                temp.random = oldNode.random.next;
            }
            oldNode = temp.next;
        }
        oldNode = pHead;
        RandomListNode newHead=pHead.next;
        //第三步：分割成两个链表
        while(oldNode != null){
            temp = oldNode.next;
            oldNode.next = temp.next;
            if(oldNode.next == null) {
                temp.next = null;
            } else {
                temp.next = oldNode.next.next;
            }
            oldNode = oldNode.next;
        }
        /*//第三步的另一种写法：
        while(oldNode.next != null){
            temp = oldNode.next;
            oldNode.next = temp.next;
            oldNode = temp;
        }*/
        return newHead;
    }

    /**
     * 2.哈希表法
     */
    public static RandomListNode clone2(RandomListNode pHead){
        if(pHead == null) {
            return null;
        }
        //noinspection MapOrSetKeyShouldOverrideHashCodeEquals
        HashMap<RandomListNode,RandomListNode> match = new HashMap<>();
        RandomListNode temp=pHead;
        while(temp != null){
            //noinspection MapOrSetKeyShouldOverrideHashCodeEquals
            match.put(temp,new RandomListNode(temp.label));//把每个旧结点和新节点配对放入哈希表
            temp = temp.next;
        }
        temp = pHead;
        //根据旧结点的next和random指针，建立新结点的next和random指针
        while (temp != null){
            match.get(temp).next = match.get(temp.next);
            if(temp.random != null && match.get(temp.random) == null)
                //random指向孤立结点的情况
            {
                match.get(temp).random=new RandomListNode(temp.random.label);
            } else {
                match.get(temp).random = match.get(temp.random);//如果temp.random为null的话，也会get到null，所以不用单独讨论
            }
            temp=temp.next;
        }
        return match.get(pHead);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomListNode newhead0 = new RandomListNode(0);
        RandomListNode newhead1 = new RandomListNode(1);
        RandomListNode newhead2 = new RandomListNode(2);
        newhead0.next = newhead1;
        newhead0.random = newhead2;
        newhead1.next = newhead2;
        newhead1.random = null;
        newhead2.random = newhead1;
        RandomListNode.print(newhead0);
        RandomListNode newhead = clone2(newhead0);
        RandomListNode.print(newhead);
    }


}
