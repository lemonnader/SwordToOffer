import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author LiMin
 * @Title: FirstAppearingOnce
 * @Description: 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * @date 2020/7/7  16:41
 */
public class FirstAppearingOnce {

    HashMap<Character, Integer> showTimes = new HashMap<>();//记录字符出现次数
    ArrayList<Character> tempOnce = new ArrayList<>();//记录当前状态下非重复的字符,全为ASCII码字符的情况下，最大空间只需要128
    int[] count = new int[128];//也可以用数组记录字符出现次数

    //Insert one char from stringstream
    public void Insert(char ch) {
        //数组写法：
/*        if(count[ch-'\0']==0){
            tempOnce.add(ch);
            count[ch-'\0']++;
        }else   count[ch-'\0']++;*/
        if (showTimes.get(ch) == null) {
            showTimes.put(ch, 1);
            tempOnce.add(ch);
        } else showTimes.put(ch, showTimes.get(ch) + 1);
    }

    //return the first appearence once char in current stringstream
    public char firstAppearingOnce() {
        //数组写法
/*        for(char c:tempOnce){
            if(count[c-'\0']==1){
                return c;
            }
        }*/
        for (char c : tempOnce) {
            if (showTimes.get(c) == 1) {
                return c;
            }
        }
        return '#';
    }
}
