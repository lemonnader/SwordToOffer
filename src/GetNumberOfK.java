/**
 * @author LiMin
 * @Title: GetNumberOfK
 * @Description: 统计一个数字在排序数组中出现的次数。
 * @date 2020/6/2016:20
 */
public class GetNumberOfK {
    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 5};
        GetNumberOfK getNumberOfK = new GetNumberOfK();
        getNumberOfK.getNumberOfKTwo(arr, 3);
    }

    /**
     * 利用二分法先找到某个k然后往前往后找k并计数，直到不是k为止，适用于k比较小的情况，k比较大时会退化成O(N)
     */
    public int getNumberOfKOne(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        int left = 0;
        int right = array.length - 1;
        int mid = ((right - left) >> 1) + left;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (array[mid] == k) {
                break;
            } else if (array[mid] > k) {
                right = mid - 1;
            } else left = mid + 1;
        }
        int count = 0;
        int temp = mid + 1;
        while (mid >= 0 && array[mid--] == k) count++;
        while (temp < array.length && array[temp++] == k) count++;
        return count;
    }

    /**
     * 利用二分法找到k的上界和下界,复杂度:O(N)
     * 上界（right）定义为：当数组存在k时，上界是最后一个k的坐标；不存在k时，上界是最后一个小于k的数字的坐标
     * 下界（left）定义为：不管k存不存在，都是最后一个小于k的数字的坐标
     * 那么当k存在时，k的个数就等于right-left；k不存在时，right=left，也满足个数等于right-left
     */
    public int getNumberOfKTwo(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        //int count = getRight(array, k)-getLeft(array, k);
        int count = getLeft(array, k + 1) - getLeft(array, k);
        System.out.println(count);
        return count;
        //return getLeft(array,k+1)-getLeft(array, k);
    }

    /**
     * 找出下界
     */
    public int getLeft(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        int mid = ((right - left) >> 1) + left;

        while (left <= right) {
            if (array[mid] >= k) {
                right = mid - 1;//找到k后向左走
            } else left = mid + 1;
            //需要放在后面这里，最后left>right时，需要再计算修改一次mid
            mid = ((right - left) >> 1) + left;
        }
        return mid;
    }

    /**
     * 找出上界
     */
    public int getRight(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        int mid = ((right - left) >> 1) + left;
        while (left <= right) {
            if (array[mid] > k) {
                right = mid - 1;
            } else left = mid + 1;
            //找到k时向右走
            //需要放在后面这里，最后left>right时，需要再计算修改一次mid
            mid = ((right - left) >> 1) + left;
        }
        return mid;
    }


}
