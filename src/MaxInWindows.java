import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @author LiMin
 * @Title: MaxInWindows
 * @Description: 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * @date 2020/7/27  9:23
 */
public class MaxInWindows {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || num.length == 0 || size <= 0 || num.length < size) {
            return result;
        }
        Deque<Integer> deque = new ArrayDeque<>(size);//队列容量不会超过窗口大小
        int left = 0;//窗口左边缘的索引值
        deque.offer(0);//队列里放的是下标
        for (int i = 0; i < num.length; i++) {
            left = i - size + 1;
            while (!deque.isEmpty() && num[deque.peekLast()] <= num[i]) {//等于的时候也要更新，不然影响后面的边界判断
                deque.pollLast();
            }
            deque.addLast(i);
            if (deque.peekFirst() < left) {//窗口之外的元素移除掉
                deque.pollFirst();
            }
            if (left >= 0) {//至少遍历到第一个窗口的右边缘
                result.add(num[deque.peekFirst()]);//只读不取出
            }
        }
        return result;

    }
}
