/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class RectCover {
    public int rectCoverWays(int n){
        if(n==0) {
            return 0;
        } else if(n==1) {
            return 1;
        } else if(n==2) {
            return 2;
        }

        int sum=2;
        int f1=1;

        for(int i=3;i<=n;i++) {
            sum=sum+f1;
            f1=sum-f1;
        }
        return sum;
        }

}
