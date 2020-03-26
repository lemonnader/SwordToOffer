/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */

import java.util.Stack;

public class TwoStacksReachQueue {
    /**
     * 利用两个栈的先进先出间接实现队列的先进先出
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        if (stack1.size() != stack1.capacity()) {
            stack1.push(node);//只要stack1没满，就把进来的数据放进stack1
        }
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());//stack2是空的就先把stack1里面的数据放进stack2里，
                //再pop就实现了先进先出
            }
        }
        return stack2.pop(); //stack2里面是之前进来的数据，所以有数据的话，优先出来
    }
}
