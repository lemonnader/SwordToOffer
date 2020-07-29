import java.util.HashMap;

/**
 * @author LiMin
 * @Title: FindNumsAppearOnce
 * @Description:
 * @date 2020/6/2417:28
 */
public class FindNumsAppearOnce {
    public static void main(String[] args) {
        FindNumsAppearOnce find = new FindNumsAppearOnce();
        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        find.findNumsAppearOnceTwo(arr, num1, num2);
        System.out.println(num1[0] + "  " + num2[0]);
    }

    /**
     * 利用HashMap来存（数字，数字出现的次数），然后遍历一遍找出出现次数为1的数字
     */
    public void findNumsAppearOnceOne(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int number = array[i];
            if (temp.containsKey(number)) {
                int count = temp.get(number);
                temp.put(number, count + 1);
            } else temp.put(number, 1);
        }
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (temp.get(array[i]) == 1) {
                if (j == 0) {
                    num1[j++] = array[i];
                } else if (j == 1) {
                    num2[0] = array[i];
                } else break;
            }
        }
    }

    /**
     * 位运算：利用异或 、与
     */
    public void findNumsAppearOnceTwo(int[] array, int num1[], int num2[]) {
        int ret = 0;
        for (int k : array) ret ^= k;
        int i = ret & (-ret);
        int result1 = 0;
        int result2 = 0;
        for (int k : array) {
            if ((i & k) == 0) result1 ^= k;
            else result2 ^= k;
        }
        num1[0] = result1;
        num2[0] = result2;
    }
}
