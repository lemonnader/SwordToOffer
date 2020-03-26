/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOfOne {
    /**
     *1.每次对n进行无符号右移一位，和1相与，将结果为1的情况进行计数，循环终止条件为n==0
     */
    public static int numberOfOneVersionOne(int n){
        if(n==0) {
            return 0;
        }
        int count=0;
        while(n!=0){
            if((n&1)==1) {
                count++;
            }
            n=n>>>1;//一定要无符号右移，不然n等于负数时由于高位补1，会陷入死循环
        }
        return count;
    }

    /**
     * 2.每次将1左移一位，然后n&1，同样计数结果为1的次数，循环结束条件为flag==0
     * flag需要位移32次才能结束循环，对于较小的n，效率较低
     */
    public static int numberOfOneVersionTwo(int n){
        if(n==0) {
            return 0;
        }
        int flag=1;
        int count=0;
        while(flag!=0){
            if((n&flag)!=0) {
                count++;
            }
            flag=flag<<1;//flag需要位移32次才能结束循环，对于较小的n，效率较低
        }
        return count;
    }

    /**
     *3.利用n&(n-1)可以把最右边的1变成0，只要n不等于0，就至少可以进行一次与操作
     * 有多少个1，就可以进行多少次，计数操作次数
     * 也适用于负数
     */
    public static int numberOfOneVersionThree(int n){
        if(n==0) {
            return 0;
        }
        int count=0;
        while (n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }
public static void test(int n,int number){
    System.out.println("n="+n+" 应输出："+number+" 实际输出：");
    System.out.println("方法1："+numberOfOneVersionOne(n));
    System.out.println("方法2："+numberOfOneVersionTwo(n));
    System.out.println("方法3："+numberOfOneVersionThree(n));
    System.out.println();
}

    public static void main(String[] args) {
        test(0,0);
        test(1,1);
        test(10,2);
        test(-10,30);
        test(0x7FFFFFFF,31);
        test(0xFFFFFFFF,32);
        test(0x80000000,1);
    }
}
