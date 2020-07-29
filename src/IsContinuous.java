import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author LiMin
 * @Title: IsContinuous
 * @Description: 判断能否构成顺子，0（大小王）可以充当任意数字
 * @date 2020/6/2719:02
 */
public class IsContinuous {
    /**
     * 1.用一个set来填充数据，0不要放进去；set的大小加上0的个数必须为5个；此外set中数值差值在5以内。
     */
    public boolean isContinuousOne(int[] numbers) {
        if (numbers == null || numbers.length != 5) return false;
        TreeSet<Integer> temp = new TreeSet<>();//TreeSet存放Integer时默认为自然升序
        int numOfZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
                continue;
            }
            temp.add(numbers[i]);
        }
        if (numOfZero + temp.size() != 5) return false;//检查是否有对子出现
        return temp.last() - temp.first() < 5;
    }

    /**
     * 2.先排序，顺子：【n,n+1,n+2,n+3,n+4】,可以看出不论有几个大小王，只要剩下的数字的（max-min）<5就一定可以组成顺子，所以可以先找出最小值的位置，
     * 然后计算（max-min）；遇到两个元素相等（对子）时直接返回false
     */
    public boolean isContinuousTwo(int[] numbers) {
        if (numbers == null || numbers.length != 5) return false;
        Arrays.sort(numbers);//升序排序
        int firstNoZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                firstNoZero++;
                continue;
            }
            if (i > 0 && numbers[i] == numbers[i - 1]) return false;//对子的情况
        }
        return numbers[numbers.length - 1] - numbers[firstNoZero] < 5;

    }

    /**
     * 3.先排序，数出大小王也就是0的数量，然后数出剩下的数字里面每两个相邻数字的diff=（差值-1）的和，差值-1即为这一对相邻数字之间需要插入的大小王的个数，最后比较diff
     * 的和与大小王数量是否相等；遇到两个元素相等（对子）时直接返回false
     */
    public boolean isContinuousThree(int[] numbers) {
        if (numbers == null || numbers.length != 5) return false;
        Arrays.sort(numbers);//升序排序
        int numOfZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
                continue;
            }
            if (numbers[i] != 0 && (i + 1) < numbers.length) {
                if (numbers[i] == numbers[i + 1]) return false;//对子
                numOfZero -= numbers[i + 1] - numbers[i] - 1;
                if (numOfZero < 0) return false;//大小王不够用了
            }
        }
        return true;
    }
}
