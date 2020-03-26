/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySequenceOfBST {
    public static boolean verifySequenceOfBstVersionOne(int[] sequence){
        if(sequence.length == 0) {
            return false;
        }
        return judge(sequence,0,sequence.length-1);
    }
    public static boolean judge(int[] arr,int start,int root){
        int length = root-start+1;
        if(length <= 2) {
            return true;
        }
        int i = 0;
        for(;i < root;i++){
            if(arr[i] > arr[root]) {
                break;
            }
        }
        int j=i-1;
        for(;i < root;i++){
            if(arr[i] < arr[root]) {
                return false;
            }
        }
        if(length==3) {
            return true;
        }
        return judge(arr,start,j)&&judge(arr,j+1,root-1);
    }

    /**
     *非递归解法
     */
    public static boolean verifySequenceOfBstVersionTwo(int[] sequence){
        int size=sequence.length;
        if(size==0) {
            return false;
        }
        if(size<=2) {
            return true;
        }
        while(--size>=0){
            int i=0;
            while (sequence[i]<sequence[size]) {
                i++;
            }
            while (sequence[i]>sequence[size]) {
                i++;
            }
            if(i!=size) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] test0={1};
        int[] test1={3,4};
        int[] test2={1,2,3};
        int[] test3={1,3,2};
        int[] test4={3,8,5,11,14,13,10};
        System.out.println(verifySequenceOfBstVersionTwo(test1));
    }
}


