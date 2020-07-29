/**
 * @author LiMin
 * @Title: InversePairs
 * @Description: 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 * @date 2020/6/1916:51
 */
public class InversePairs {

    //public int count = 0;
    //必须要在外部声明成全局变量，如果是作为函数入参，每次递归调用的时候都会把状态量压栈，最后出栈的是最初调用时的count，一直是0
    //或者可以通过返回值实现
    public static void main(String[] args) {
        int[] arr = {1, 2, 0};
        InversePairs inversePairs = new InversePairs();
        System.out.println(inversePairs.inversePairs(arr));
    }

    /**
     * 1.将数组拆分为左右两个部分，逆序总个数=左半部分逆序对个数+右半部分逆序对个数+跨左右的逆序对个数
     * 2.递归求解左半部分和右半部分
     * 3.对于跨左右的逆序对个数计算，是发生在归并排序融合左右两个序列时，将左半部分尾指针pLeft对应值依次与右半部分尾指针pRright对应值进行比较，若pLeft对应值大于
     * pRright对应值，则pLeft至左子序列最后都大于pRright对应值，逆序对个数为左半部分当前指针至尾指针的元素个数，即存在（mid-pRright+1）个逆序对。（前提是左右部分
     * 已经分别递增排序）。
     * 4.计算过程中依次将左右子序列当前指针对应值比较较小者自左向右依次放入临时数组中，使其排成一段递增排序序列，最后再把临时数组中的值转回原数组。
     * 5.最终可以递归求解出总的逆序对个数，同时也将原序列归并排序为递增序列。计算过程中需要注意题目附加条件，防止数值过大进行取余操作；最后的count可能会超过int
     * 最大值而溢出，所以需要提前取余数。
     * @param array
     * @return
     */
    public int inversePairs(int[] array) {
        //需要在递归的外部申请merge时用来临时转移元素的数组，否则每次递归时都会申请不同长度的空间
        int[] temp = new int[array.length];
        int count = mergeSort(array, temp, 0, array.length - 1);
        return count;
    }

    /**
     * 归并排序
     * @param temp 从大到小临时存放元素的数组
     */
    public int mergeSort(int[] array, int[] temp, int left, int right) {
        //递归终止条件：
        if (left == right) return 0;
        int mid = ((right - left) >> 1) + left;
        //递归处理左右序列
        int countLeft = mergeSort(array, temp, left, mid);
        int countRight = mergeSort(array, temp, mid + 1, right);
        //融合左右序列并计算跨左右序列的逆序对
        int countMid = merge(array, temp, left, mid, right);
        //提前取余数，防止溢出（每次一大于1000000007就取其余数，和最后再取余数是一样的，所以count不会超过1000000007）
        return (countLeft + countRight + countMid) % 1000000007;
    }

    /**
     * 融合左右子序列
     */
    public int merge(int[] array, int[] temp, int left, int mid, int right) {
        if (left > right) return 0;
        //左右序列当前指针&向临时数组存值的指针，从左向右存
        int pLeft = left;
        int pRight = mid + 1;
        int pSave = left;
        int count = 0;
        while (pLeft <= mid && pRight <= right) {
            if (array[pLeft] > array[pRight]) {
                temp[pSave++] = array[pRight++];
                count += mid - pLeft + 1;
                //提前取余数，防止溢出（每次一大于1000000007就取其余数，和最后再取余数是一样的，所以count不会超过1000000007）
                count %= 1000000007;
            } else temp[pSave++] = array[pLeft++];
        }
        while (pLeft <= mid) temp[pSave++] = array[pLeft++];
        while (pRight <= right) temp[pSave++] = array[pRight++];
        //把temp数组排好序的部分转移到array中
        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }
        return count;
    }
}
