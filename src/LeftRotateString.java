/**
 * @author LiMin
 * @Title: LeftRotateString
 * @Description: 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
 * 请你把其循环左移K位后的序列输出。
 * @date 2020/6/2619:53
 */
public class LeftRotateString {
    public String leftRotateString(String str, int n) {
        if (str == null || str.length() == 0 || n < 0) return "";
        if (n == 0) return str;
        n %= str.length();
        String subleft = str.substring(n);//没移出去的剩下的部分，放在左边
        String subRight = str.substring(0, n);//移出去的部分，放在右边
        StringBuilder result = new StringBuilder(subleft);
        result.append(subRight);
        return result.toString();
//第二种方法
//        StringBuilder result=new StringBuilder(str);
//        result.append(str);
//        return result.toString().substring(n,n+str.length());

    }
}
