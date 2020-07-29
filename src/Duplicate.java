/**
 * @author LiMin
 * @Title: Duplicate
 * @Description: 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。
 * @date 2020/7/112:06
 */
public class Duplicate {
    public static void main(String[] args) {
        Duplicate dup = new Duplicate();
        int length = 5;
        int[] nums = {2, 1, 3, 1, 4};
        int[] duplicate = new int[1];
        System.out.println(dup.duplicateThree(nums, length, duplicate));
    }

    /**
     * 哈希法
     */
    public boolean duplicateOne(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) return false;
        //数组模拟，byte类型只占一个字节
        byte[] temp = new byte[length];
        for (int i = 0; i < length; i++) {
            if (temp[numbers[i]] == '0') {
                duplication[0] = numbers[i];
                return true;
            } else temp[numbers[i]] = '0';
        }
        return false;
/*
        HashSet<Integer> temp=new HashSet<>();
        for(int i=0;i<length;i++){
            if(temp.contains(numbers[i])) {
                duplication[0]=numbers[i];
                return true;
            }
            else temp.add(numbers[i]);
        }
        return false;
*/
    }

    /**
     * in-place法
     */
    public boolean duplicateTwo(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) return false;
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {//没到位就一直交换
                if (numbers[i] == numbers[numbers[i]]) {//出现重复元素
                    duplication[0] = numbers[i];
                    return true;
                } else swap(numbers, i, numbers[i]);//把i处元素放到正确位置
            }
        }
        return false;

    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应索引上的数 + n，之后再遇到相同的数时，会发现对应索引上的数已经大于等于n了，那么直接返回这个数即可。
     */
    public boolean duplicateThree(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) return false;
        for (int i = 0; i < length; i++) {
            int value = numbers[i];
            if (value >= length) value -= length;//按顺序，索引值为i的对应元素如果大于length的话应该减掉才能变回原来的值
            if (numbers[value] >= length) {//以numbers[i]为下标找到的值如果大于length，就说明之前以经因为某个
                // numbers[j]使得此下标下的元素值>length，即numbers[i]==numbers[j]
                duplication[0] = value;
                return true;
            }
            numbers[value] = (numbers[value] + length) > Integer.MAX_VALUE ? Integer.MAX_VALUE :
                    (numbers[value] + length);//防止越界
        }
        return false;
    }
}
