import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
public class PrintMatrix {
    public static ArrayList<Integer> printMatrix1(int[][] matrix){
        ArrayList<Integer> result=new ArrayList<>();
        int height=matrix.length;//矩阵的总行数
        int width=matrix[0].length;//矩阵的总列数
        int beginIndex=0;//每一圈起始点(beginIndex,beginIndex)的坐标
        int row=0;
        int col=0;//当前打印数据的行和列的索引值
        int count=0;//已经打印的数据个数
        int number=height*width;//数组元素总个数
        while(count<number){
            //打印顶部的一行
            for(;col<width;col++){
                result.add(matrix[row][col]);
                count++;
            }
            row++;
            col--;//上面的循环执行完之后col=width，越界了，需要col自减一
            //打印右边的一列
            for(;row<height;row++){
                result.add(matrix[row][col]);
                count++;
            }
            row--;//上面的循环执行完之后row=height，越界了，需要row自减一
            col--;
            //if(count==number) break;//需要判断一下是否应打印完了，奇数阶矩阵最里面一圈其实只有其实只有一条边，
            // 如果不判断一下继续打印的话会出现重复打印元素的情况
            //打印底部一行
            for(;col>=beginIndex;col--){
                result.add(matrix[row][col]);
                count++;
            }
            col++;//上面的循环执行完之后col=beginIndex-1,需要col自加一
            row--;
            //打印左边的一列
            for(;row>beginIndex;row--){
                result.add(matrix[row][col]);
                count++;
            }
            beginIndex++;
            row=beginIndex;
            col=beginIndex;
            width--;
            height--;//缩小一圈
        }
        return result;
    }

    /**
     *旋转矩阵的方法
     */
    public static ArrayList<Integer> printMatrix2(int[][] matrix){
        ArrayList<Integer> result = new ArrayList<>();
        int row = matrix.length;
        while (row != 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                result.add(matrix[0][i]);
            }
            if (row == 1) {
                break;
            }
            matrix = turn(matrix);
            row = matrix.length;
        }
        return result;
    }
    private static int[][] turn(int[][] matrix) {
        // TODO 自动生成的方法存根
        int col = matrix[0].length;
        int row = matrix.length;
        int[][] newMatrix = new int[col][row - 1];
        for (int j = col - 1; j >= 0; j--) {
            for (int i = 1; i < row; i++) {
                newMatrix[col - 1 - j][i - 1] = matrix[i][j];
            }
        }
        return newMatrix;
    }
}
