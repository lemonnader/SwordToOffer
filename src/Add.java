/**
 * @author LiMin
 * @Title: Add
 * @Description:
 * @date 2020/6/3021:26
 */
public class Add {
    public int add(int num1, int num2) {
        int sum = num1 ^ num2;//无进位相加
        int carry = (num1 & num2) << 1;//进位值，并左移一位
        while (carry != 0) {
            int oldSum = sum;
            sum ^= carry;
            carry = (oldSum & carry) << 1;
        }
        return sum;
    }
}
