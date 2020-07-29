import java.util.ArrayList;

/**
 * @author LiMin
 * @Title: FindNumbersWithSum
 * @Description: 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * @date 2020/6/2618:14
 */
public class FindNumbersWithSum {
    public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if (array.length < 2) return result;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int tempSum = array[left] + array[right];
            if (tempSum == sum) {
                result.add(array[left]);
                result.add(array[right]);
                return result;
            } else if (tempSum > sum) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }
}
