public class JumpFloor {
    public int jumpOnFloor(int n){
        if(n==0) {
            return 0;
        } else if(n==1) {
            return 1;
        } else if(n==2) {
            return 2;
        }

        int sum=2;
        int f1=1;

        for(int i=3;i<=n;i++){
            sum=sum+f1;
            f1=sum-f1;
        }
        return sum;
    }

    /**拓展提升代码，很简单*/
    public int superJumpOnFloor(int n){
        if(n==0) {
            return 0;
        }
        return (int)Math.pow(2,n-1);
    }
}
