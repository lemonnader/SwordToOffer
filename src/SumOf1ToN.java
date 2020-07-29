/**
 * @author LiMin
 * @Title: SumOf1ToN
 * @Description: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * @date 2020/6/309:34
 */
public class SumOf1ToN {
    public static void main(String[] args) {
        SumOf1ToN sumOf1ToN = new SumOf1ToN();
        System.out.println(sumOf1ToN.sumOf1ToNThree(1));
    }

    /**
     * 利用&&短路特性表示if，实现递归
     */
    public int sumOf1ToNOne(int n) {
        int sum = n;
        boolean flag = (n > 0) && (sum += sumOf1ToNOne(n - 1)) > 0;//这里加上boolean完全是为了不报错
        return sum;
    }

    /**
     * 利用Java异常处理机制，除以0为截至条件，实现递归
     */
    public int sumOf1ToNTwo(int n) {
        try {
            int i = 1 % n;//当n==0时会抛出异常
            return n + sumOf1ToNTwo(n - 1);

        } catch (Exception e) {
            return 0;//当n==0时返回0
        }
    }

    /**
     * 快速幂算法实现两数相乘
     */
    public int sumOf1ToNThree(int n) {
        return multi(n, n + 1) >> 1;
    }

    public int multi(int a, int b) {
        int result = 0;
        //利用短路特性模拟if
        boolean flag1 = ((a & 1) == 1) && (result += b) > 0;//a的当前最低位为1时才加上b
        a >>= 1;//a/2
        b <<= 1;//b*2;
        //利用短路特性模拟while
        boolean flag2 = (a != 0) && (result += multi(a, b)) > 0;
        System.out.println(result);
        return result;
    }

}
