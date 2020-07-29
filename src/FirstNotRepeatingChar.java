import java.util.HashMap;

/**
 * @author LiMin
 * @Title: FirstNotRepeatingChar
 * @Description: 在一个字符串(0 < = 字符串长度 < = 10000 ， 全部由字母组成)中找到第一个只出现一次的字符, 并返回它的位置,
 * 如果没有则返回-1（需要区分大小写）.（从0开始计数
 * @date 2020/6/1910:44
 */
public class FirstNotRepeatingChar {

    public static void main(String[] args) {
        String str = "google";
        FirstNotRepeatingChar firstNotRepeatingChar = new FirstNotRepeatingChar();
        System.out.println(firstNotRepeatingChar.firstNotRepeatingCharOne(str));
    }

    /**
     * 法一：极简内存,其实主要还是hash，利用每个字母的ASCII码作hash值来作为数组的index。首先用一个58长度的数组来存储每个字母出现的次数，为什么是58呢，主要是由于A-Z
     * 对应的ASCII码为65-90，a-z对应的ASCII码值为97-122，那么从A-z就是122-65+1=58，其实91-96对应的并不是字母，但是为了方便统一管理，这六个数字虽然没有用到，但是也
     * 申请了空间，而每个字母的索引值就是index=charx-'A'，比如g=103-65=38，而数组中具体记录的内容是该字母出现的次数，最终遍历一遍原始字符串，找出第一个数组内容为1
     * 的字母就可以了
     * 时间复杂度为O(2n)=O(n)；空间复杂度为O(58)
     *
     * @param str
     * @return
     */
    public int firstNotRepeatingCharOne(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] numOfChar = new int[58];
        for (int i = 0; i < str.length(); i++) {
            numOfChar[str.charAt(i) - 'A']++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (numOfChar[str.charAt(i) - 'A'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 法二：利用HashMap保存每个字符出现的次数，然后遍历字符串，找出第一个出现次数为1的字符
     * Hashmap的查找复杂度为o（1）；总的时间复杂度O(2n)=O(n),空间复杂度：O(n)
     *
     * @param str
     * @return
     */
    public int firstNotRepeatingCharTwo(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        HashMap<Character, Integer> numOfCharacter = new HashMap<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (numOfCharacter.containsKey(c)) {
                int num = numOfCharacter.get(c);
                numOfCharacter.put(c, ++num);
            } else numOfCharacter.put(c, 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (numOfCharacter.get(str.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
