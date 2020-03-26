import java.time.Clock;
import java.util.Arrays;
import java.util.Random;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ReOrderAraay {
    /**
     * 1.不借助额外的空间，利用插入排序的思想(稳定的排序)
     * 最坏情况下复杂度位O(n²)
     */
    public static void reOrderArray1(int[] array){
        //System.out.println("original array："+Arrays.toString(array));
        int i=0;
        for(;i<array.length;i++) {
            if ((array[i] & 1) == 0) {
                break;//遇到第一个偶数时跳出循环，找到了第一个奇数应改放置的位置
            }
        }
        int odd=i;//下一个奇数需要放置的位置
        for(int j=odd;j<array.length;j++){
            if ((array[j] & 1) == 1){
                int data=array[j];
                for (int k=j;k>=(odd+1);k--){
                    array[k]=array[k-1];
                }
                array[odd++]=data;
            }
        }
       // System.out.println("reordered array："+Arrays.toString(array));
    }

    /**
     *2.用空间换时间，借助额外的辅助数组
     * 一次循环，两个指针，一个从前往后，只看奇数，按序从头放进辅助数组里
     * 另一个从后往前找一遍，只看偶数，按序从尾巴放进辅助数组里
     * 然后把数据倒腾进原数组
     * 也保证了数据的稳定性有序性，时间和空间复杂度都为O(n)
     */
    public static void reOrderArray2(int[] array){
        int[] temp=new int[array.length];
        int odd=0;
        int even=array.length-1;//奇数和偶数指针
        int saveOdd=0;
        int saveEven=array.length-1;
        while(odd<array.length){
            if((array[odd]&1)==1) {
                temp[saveOdd++]=array[odd++];
            } else {
                odd++;
            }
            if ((array[even]&1)==0) {
                temp[saveEven--]=array[even--];
            } else {
                even--;
            }
        }
        for(int i=0;i<array.length;i++) {
            array[i]=temp[i];//结果倒腾回原数组
        }
       // System.out.println(Arrays.toString(array));
    }
    public static int[] randArray(int n, int scale) {
        Random rand = new Random(47);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(scale);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a=randArray(20000,1000);
        int[] b=a.clone();
        long s = Clock.systemDefaultZone().millis();
        reOrderArray1(a);
        System.out.println("reOrderArray1耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        reOrderArray2(b);
        System.out.println("reOrderArray2耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
        //运行结果：
        //reOrderArray1耗时: 80 ms
        //reOrderArray2耗时: 2 ms
    }
}
