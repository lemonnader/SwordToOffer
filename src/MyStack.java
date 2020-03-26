/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */
import java.util.Stack;
public class MyStack {
    Stack<Integer> stack=new Stack<>();
    Stack<Integer> minStack=new Stack<>();
    int min=Integer.MAX_VALUE;
    public void push(int node) {
        if(node<=min) {
            minStack.push(node);
        }
        min=minStack.peek();
        stack.push(node);
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
        min=minStack.peek();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }

    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        System.out.println(stack.peek());
    }
}
