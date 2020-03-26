/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * 特例：空数组、相等数组、非递增数组（eg：{5,5,5,5,5,2,2,2,5,5}）,升序数组，单个值数组
 */
public class MinInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        if(array.length==0) {
            return 0;
        }
        int left=0;
        int right=array.length-1;//数组左右边界
        int mid;
        int count=0;
        while(left<right){
            ++count;
            //升序数组
            if (array[left] < array[right]){
                System.out.println(count);
                System.out.println("yes");
                return array[left];
            }

            mid=left+(right-left)/2;

            //mid在最小值左边
            if(array[mid]>array[right])
            {
                //必须+1，否则最后只剩两个数而且最小值在最后的时候，会陷入死循环
                left=mid+1;
            }
            //不能判断mid在左边还是右边
            else if(array[mid]==array[right])
            {
                //为了不跳过最小值，一点点试探
                right--;
            } else  //mid在最小值右边或者就是最小值，为了避免跳过最小值，此处不能-1
                {right=mid;}
        }
        System.out.println(count);
        //left一旦到了右边就会在最小值处，而且不会再移动，等着right移动到
        return array[left];
        //和left相等的地方就把该处的值返回就可以了
    }

    public int minNumberOfIndexInRotateArray(int[] array) {
        if(array.length==0) {
            return 0;
        }
        int left=0;
        //数组左右边界
        int right=array.length-1;
        int mid;
        while(left<right){

            if (array[left] < array[right]) {
                return array[left];
            }
            mid=left+(right-left)/2;

            //mid在最小值左边
            if(array[mid]>array[right])
            {
                //必须+1，否则最后只剩两个数而且最小值在最后的时候，会陷入死循环
                left=mid+1;
            }
            //不能判断mid在左边还是右边
            else if(array[mid]==array[right])
            {
                //为了不跳过最小值，一点点试探
                right--;
            }
            //mid在最小值右边或者就是最小值，为了避免跳过最小值，此处不能-1
            else {right=mid;}
        }
        //left一旦到了右边就会在最小值处，而且不会再移动，等着right移动到
        return left;
        //和left相等的地方就把该处的值返回就可以了
    }

    public static void main(String[] args) {
        int []a1={2,2,2,2,2,2,1,1,1,2};
        int []a2={1,1,1,1,1,1,1};
        int []a3={3,4,5,6,7,8,9,0,1,2,3,3,3,3};
        MinInRotateArray min=new MinInRotateArray();
        System.out.println(min.minNumberInRotateArray(a1));
       // System.out.println(min.minNumberOfIndexInRotateArray(a1));
        System.out.println(min.minNumberInRotateArray(a2));
       // System.out.println(min.minNumberOfIndexInRotateArray(a2));
        System.out.println(min.minNumberInRotateArray(a3));
    }
}
