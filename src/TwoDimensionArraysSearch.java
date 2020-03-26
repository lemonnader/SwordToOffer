public class TwoDimensionArraysSearch {
    /**
     *第一种方法：二分查找
     * 复杂度：O(NlogN)
     * 运行时间：202ms;占用内存：16928k
     */
    public boolean binaryFind(int target,int [][]array){

        int count=0;
        if(array==null||array.length==0) {
            return false;//判定数组空
        }
        //变量定义
        int left,right,mid;

        //遍历每一行
        for(int i=0;i<array.length;i++){

            if(array[i]==null||array[i].length==0) {
                return false;//判定数组空
            }

            //变量初始化
            left=0;
            right=array[i].length-1;


            while(left<=right){
                ++count;
                mid=(left+right)/2;

                if(array[i][mid]>target) {
                    right=mid-1;//可以-1，也可以不写，-1效率更高
                } else if(array[i][mid]<target) {
                    left=mid+1;//必须+1
                } else {
                    System.out.println(count);
                    return true;}
            }
        }

        return false;
    }

    /**
     * 最优解
     * 思路：从最大行第一列开始查询，若比目标大则行减一，若比目标小则列加一
     * 复杂度：O(N)(假设列数为N)
     * 运行时间：193ms； 占用内存：18412k
     */
    public boolean find(int target,int [][]array){

        if(array==null||array.length==0) {
            return false;//判定数组空
        }

        //变量定义及初始化
        int row=array.length-1;
        int col=0;

        while(row>=0&&col<array[0].length){//行列均未出界
            if(array[row][col]>target) {
                row--;
            } else if(array[row][col]<target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[][] arrays={{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int[][] a1={{1,2,3,4,5}};
        TwoDimensionArraysSearch findNum=new TwoDimensionArraysSearch();
        boolean result=findNum.binaryFind(5,a1);
        System.out.println(result);

    }
}
