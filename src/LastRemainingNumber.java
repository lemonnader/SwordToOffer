import java.util.LinkedList;

/**
 * @author LiMin
 * @Title: LastRemainingNumber
 * @Description: 圆圈中最后剩下的数字/约瑟夫环问题
 * @date 2020/6/289:34
 */
public class LastRemainingNumber {
    /**
     * 用LinkedList进行过程模拟
     */
    public int lastRemainingNumberOne(int n, int m) {
        if (m <= 0 || n <= 0) return -1;
        LinkedList<Integer> temp = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < n; i++) temp.add(i);
        while (temp.size() > 1) {
            index = (index + m - 1) % temp.size();//每次选出来要删除的索引值
            temp.remove(index);
        }
        return temp.get(0);
    }

    /**
     * 公式推导——递归法
     */
    public int lastRemainingNumberTwo(int n, int m) {
        if (m <= 0 || n <= 0) return -1;
        if (n == 1) return 0;
        return (lastRemainingNumberTwo(n - 1, m) + m) % n;
    }

    /**
     * 公式推导——迭代法
     */
    public int lastRemainingNumberThree(int n, int m) {
        if (m <= 0 || n <= 0) return -1;
        if (n == 1) return 0;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + m) % i;
        }
        return result;
    }
}
