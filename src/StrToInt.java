/**
 * @author LiMin
 * @Title: StrToInt
 * @Description: 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * @date 2020/7/19:48
 */
public class StrToInt {
    public static void main(String[] args) {
        StrToInt strInt = new StrToInt();
        System.out.println(strInt.strToInt("+2147483647"));
        System.out.println(Integer.valueOf("+2147483648"));
    }

    public int strToInt(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        int isNegtive = 1;//正负标志
        int value  = 0;
        int overValue = 0;//是否越界的标志，大于0说明越界，否则没有
        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            i++;
            isNegtive = -1;
        }
        while (i < str.length()) {
            if (!isNumber(str.charAt(i))) return 0;//出现不是数字的非法字符就返回0
            int digit  = str.charAt(i) - '0';
            //MAX_VALUE=2^31-1=2147483647;MIN_VALUE=-2^31=-2147483648
            overValue = isNegtive * value  - Integer.MAX_VALUE/10 + ((isNegtive + 1) / 2 + digit) > 8 ? 1 : 0;//计算是否越界
            if (overValue > 0) return 0;
            else value  = value  * 10 + isNegtive * digit ;
            i++;
        }
        return value ;
    }

    public boolean isNumber(char c) {
        if (c >= '0' && c <= '9') return true;
        else return false;
    }
}
