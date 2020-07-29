/**
 * @author LiMin
 * @Title: Match
 * @Description: 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * @date 2020/7/2  21:12
 */

public class Match {
    public static void main(String[] args) {
        char[] string = " ".toCharArray();
        char[] pat = " ".toCharArray();
        Match m = new Match();
        System.out.println(m.matchTwo("aa".toCharArray(), "aa*a*a".toCharArray()));
    }

    /**
     * 递归法
     */
    public boolean matchOne(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        int sx = 0;
        int px = 0;
        return matchhelper(str, sx, pattern, px);
    }

    public boolean matchhelper(char[] str, int sx, char[] pattern, int px) {
        //先列出递归终止条件
        //同时结束
        if (sx == str.length && px == pattern.length) {
            return true;
        }
        //pattern先结束
        if (sx != str.length && px == pattern.length) {
            return false;
        }

        //现在只剩下px<pattern.length的情况
        if (px + 1 < pattern.length && pattern[px + 1] == '*') {//pattern有下一个符号，且下一个符号为*
            if (sx != str.length && (str[sx] == pattern[px] || pattern[px] == '.')) {
                return matchhelper(str, sx, pattern, px + 2)//匹配0次,pattern直接跳过两个字符，即"x*"
                        || matchhelper(str, sx + 1, pattern, px);//匹配1次或多次，一个一个往后匹配
            } else {
                return matchhelper(str, sx, pattern, px + 2);//匹配0次,pattern直接跳过两个字符，即"x*"
            }
        } else {//pattern有下一个符号，且下一个符号不为*或者pattern没有下一个符号，即px==pattern.length-1
            if (sx != str.length && (str[sx] == pattern[px] || pattern[px] == '.')) {
                return matchhelper(str, sx + 1, pattern, px + 1);
            } else {
                return false;
            }
        }
    }

    /**
     * 动态规划
     */
    public boolean matchTwo(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        boolean[][] match = new boolean[str.length + 1][pattern.length + 1];//boolean默认初始化为false
        match[0][0] = true;//空字符串和空字符串相匹配，还和"a*b*c*.."类型的相匹配
        for (int i = 1; i <= pattern.length; i++) {
            if (pattern[i - 1] == '*') {
                match[0][i] = match[0][i - 2];
            }
        }
        //match[sx][px]对应当前s和p的元素为：s[sx-1]和p[px-1]
        for (int sx = 1; sx <= str.length; sx++) {
            for (int px = 1; px <= pattern.length; px++) {
                if (pattern[px - 1] == '.' || str[sx - 1] == pattern[px - 1]) {//当前元素相匹配
                    match[sx][px] = match[sx - 1][px - 1];
                }//str[sx-1]!=pattern[px-1]时默认match[sx][px]为false
                else if (pattern[px - 1] == '*') {
                    if (pattern[px - 2] == '.' || str[sx - 1] == pattern[px - 2]) {//可能重复0次或者多次
                        match[sx][px] = match[sx][px - 2] || match[sx - 1][px];
                    } else {
                        match[sx][px] = match[sx][px - 2];
                    }
                }
            }
        }
        return match[str.length][pattern.length];
    }
}
