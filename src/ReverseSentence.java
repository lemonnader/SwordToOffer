/**
 * @author LiMin
 * @Title: ReverseSentence
 * @Description:
 * @date 2020/6/2620:17
 */
public class ReverseSentence {
    public static void main(String[] args) {
        ReverseSentence re = new ReverseSentence();
//        char[] chars={'a','b','c'};
//        System.out.println(String.valueOf(chars));//打印出来是[abc]
//        System.out.println(Arrays.toString(chars));//打印出来是
//        System.out.println(new String(chars));
        System.out.println(re.reverseSentenceTwo("   "));
    }

    /**
     * 利用库函数
     */
    public String reverseSentenceOne(String str) {
        if (str == null || str.length() == 0) return "";
        String[] temp = str.split(" ");
        int length = temp.length - 1;
        if (length < 0) return str;//str中只有空格，会导致temp为空;注意要返回str（源字符串）
        StringBuilder result = new StringBuilder(temp[length--]);
        while (length >= 0) {
            result.append(" ").append(temp[length--]);
        }
        return result.toString();
    }

    /**
     * 自己写代码，先整体反转，再对每个字符反转
     */
    public String reverseSentenceTwo(String str) {
        if (str == null || str.length() == 0) return "";
        char[] chars = str.toCharArray();
        reversePart(chars, 0, chars.length - 1);//先整体反转
        int i = 0;
        int start = 0, end = 0;
        while (i < chars.length) {
            while (i < chars.length && chars[i] == ' ') i++;//找到单词的开头
            start = i;
            while (i < chars.length && chars[i] != ' ') i++;//找到单词的结尾
            end = i - 1;
            reversePart(chars, start, end);
        }
        return String.valueOf(chars);//不能用chars.toString();会把数组首地址赋给String
    }

    /**
     * 反转字符串从left到right的部分
     */
    public void reversePart(char[] chars, int left, int right) {
        while (left < right) swap(chars, left++, right--);
    }

    public void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}
