import java.util.Stack;
/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class IsPopOrder {
    public static boolean isPopOrder(int[] pushA,int[] popA){
        if(pushA.length==0||popA.length==0) {
            return false;
        }
        Stack<Integer> stack=new Stack<>();
        int in=0;
        for(;in<pushA.length;in++){
            if(pushA[in]!=popA[in]) {
                break;
            }
        }//如果两个从第0~第x个元素都相等的话，根本不用进行入栈操作
        int out=in;
        for(;in<pushA.length;in++){
            stack.push(pushA[in]);
            while(!stack.empty()&&stack.peek()==popA[out]){
                stack.pop();
                out++;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        int[] push={1,2,3,4,5};
        //int[] pop={2,1,3,5,4};
        int[] pop={2,1,3,5,4};
        System.out.println( isPopOrder(push,pop));
    }
}
