/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项
 * （从0开始，第0项为0）。n<=39
 */
public class FibonacciArray {
    /**
     *1.递归解法
     * 代码简单，但是存在重复调用和计算，造成时间和内存的浪费
     * 复杂度：O(2^n)
     */
    public int fibonacciOne(int n){
        if(n==0) {
            return 0;
        } else if(n==1) {
            return 1;
        } else {
            return fibonacciOne(n-1)+fibonacciOne(n-2);
        }
    }
    /**
     * 2.非递归解法
     * 仔细观察其实要求f(n)只需要f(n-1)和f(n-2)两个变量
     * 设置三个变量，分别存f(n-1)和f(n-2)以及f(n)
     * 复杂度O(n)
     */
    public int fibonacciTwo(int n){
        if(n==0) {
            return 0;
        } else if(n==1) {
            return 1;
        }

        int sum=0;//f(n)
        int f1=1;//f(n-1)
        int f2=0;//f(n-2)

        for(int i=2;i<=n;i++){
            sum=f1+f2;
            f2=f1;
            f1=sum;
        }
        System.out.println(sum);
        return sum;
    }
    /**
     * 3.非递归解法优化
     * 仔细观察可以发现，现态sum可以用次态的sum+f1表示；而现态的f1可以用现态的sum减去次态的f1表示
     */
    public int fibonacciThree(int n){
        if(n==0) {
            return 0;
        } else if(n==1) {
            return 1;
        }

        int sum=1;//f(n)
        int f1=0;//f(1)
        for (int i=2;i<=n;i++){
            sum=sum+f1;
            f1=sum-f1;
        }
        return sum;
    }

    /**
     *测试函数
     */
    public void test(int n,int target){
        System.out.println("n="+n);
        if(fibonacciOne(n)==target) {
            System.out.println("方法1通过");
        } else if(fibonacciOne(n)!=target) {
            System.out.println("方法1失败");
        }
        if(fibonacciTwo(n)==target) {
            System.out.println("方法2通过");
        } else if(fibonacciTwo(n)!=target) {
            System.out.println("方法2失败");
        }
        if(fibonacciThree(n)==target) {
            System.out.println("方法3通过");
        } else if(fibonacciThree(n)!=target) {
            System.out.println("方法3失败");
        }
    }
    public static void main(String[] args) {
        FibonacciArray fi=new FibonacciArray();
        fi.test(0,0);
        fi.test(1,1);
        fi.test(2,1);
        fi.test(3,2);
        fi.test(4,3);
        fi.test(5,5);
        fi.test(6,8);
        fi.test(7,13);
        fi.test(8,21);
        fi.test(9,34);
        fi.test(10,55);
        fi.test(40, 102334155);
    }
}
