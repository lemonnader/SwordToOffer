import java.util.ArrayList;

/**
 * @author LiMin
 * @Title: FindContinuousSequence
 * @Description:
 * @date 2020/6/2521:29
 */
public class FindContinuousSequence {
    public static void main(String[] args) {
        FindContinuousSequence find = new FindContinuousSequence();
        int s = 2;
//        double n=2;
//        double tempX0=(2*s-n*n+n)/(2*n);
//        System.out.println(tempX0);

        //System.out.println(find.findContinuousSequenceOne(s));
        //System.out.println(find.findContinuousSequenceTwo(s));
        System.out.println(find.findContinuousSequenceThree(s));
    }

    /**
     * 法一：数学法，利用等差数列求和原理
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequenceOne(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        int a1 = 0;
        int n = (int) (-1 + Math.sqrt(1 + 8 * sum)) / 2;//求根公式求出n的上界,
        // 题目要求序列间按照开始数字从小到大的顺序，开始数字越小，序列内数字个数越多，即n越大，所以从上界开始
        while (n >= 2) {
            if ((2 * sum - n * n + n) % (2 * n) == 0) {//说明a1是整数，满足条件
                a1 = (2 * sum - n * n + n) / (2 * n);
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    tempList.add(a1 + i);
                }
                result.add(tempList);
            }
            n--;
        }
        return result;
    }

    /**
     * 法二：数学法，利用等差数列求和原理的另外一种方式
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequenceTwo(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        int m = (int) (Math.sqrt(2 * sum));
        for (int l = m; l >= 2; l--) {//l越大，k和l的差就越小，a1就越小
            if (2 * sum % l == 0) {
                int k = 2 * sum / l;
                if ((k % 2 == 0 && l % 2 != 0) || (k % 2 != 0 && l % 2 == 0)) {
                    int a1 = (k - l + 1) / 2;
                    int an = (k + l - 1) / 2;
                    ArrayList<Integer> tempList = new ArrayList<>();
                    for (int a = a1; a <= an; a++) {
                        tempList.add(a);
                    }
                    result.add(tempList);
                }
            }
        }
        return result;
    }

    /**
     * 法三：滑动窗口法
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequenceThree(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        int left = 1;
        int right = 1;
        int s = 0;
        while (left <= (sum / 2)) {
            if (s == sum) {
                ArrayList<Integer> tempList = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    tempList.add(i);
                }
                result.add(tempList);
                s -= left;
                left++;
            } else if (s > sum) {
                s -= left;
                left++;
            } else {
                s += right;
                right++;
            }
        }
        return result;
    }
}
