/**
 * @author LiMin
 * @Title: IsNumeric
 * @Description: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * @date 2020/7/7  10:25
 */
public class IsNumeric {
    public static void main(String[] args) {
        IsNumeric isNumeric = new IsNumeric();
        System.out.println(isNumeric.isNumericOne("12e+5.4".toCharArray()));

    }

    /**
     * 方法一：考虑所有情况，从前往后依次判断
     */
    public boolean isNumericOne(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        //只有一个非数字字符
        if (str.length == 1 && (str[0] < '0' || str[0] > '9')) {
            return false;
        }
        //是否已经出现小数点,e/E
        boolean decimal = false, hasE = false;
        int i = 0;
        //逐个分析+、-、.、e/E能出现的条件，数字可以随时出现；满足任何一个就继续，不满足就跳出
        for (; i < str.length; i++) {
            //正负号只能出现在最前面或者e/E后面（紧跟着），并且不能是最后一个字符
            if (i == 0 && isSymOrE(str[0], 1)
                    || i > 0 && i < str.length - 1 && isSymOrE(str[i - 1], 2) && isSymOrE(str[i], 1)) {
                continue;
            }
            //小数点只能出现一次且不能出现在e/E的后面
            else if (!decimal && !hasE && str[i] == '.') {
                decimal = true;
                continue;
            }
            //e/E的前后都要有数字,且只能出现一次
            else if (i > 0 && i < str.length - 1 && !hasE && isSymOrE(str[i], 2)) {
                hasE = true;
                continue;
            }
            //数字可以随时出现
            else if (str[i] >= '0' && str[i] <= '9') {
                continue;
            } else {
                break;
            }
        }
        return i == str.length;//只需判断能不能正常结束
    }

    /** 是否为正负号(1)或者e/E(2) */
    public boolean isSymOrE(char c, int x) {
        if (x == 1) {
            return c == '-' || c == '+';
        } else if (x == 2) {
            return c == 'e' || c == 'E';
        }
        return false;
    }

    /**
     * 方法二：正则表达式
     */
    public boolean isNumericTwo(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        if (str[0] == 'e' || str[0] == 'E') {//第一个字符不能是e或者E
            return false;
        }
        String string = String.valueOf(str);//注意str.toString()是把str的首地址转化成了String
        return string.matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");//这里其实包括了第一个字符为e/E的情况
    }
}
