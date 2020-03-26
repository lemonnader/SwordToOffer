/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 */
public class PowerOfMine {
    /**
     *1.降幂递归
     * 复杂度：O(logN)，但是递归存在时间和内存上的浪费
     */
    public static double powVersionOne(double base,int exponent){
        if(exponent==0) {
            return 1;
        } else if(exponent==1) {
            return base;
        } else if(exponent<0){
            if(Math.abs(base)<0.000001) {
                throw new RuntimeException("分母不能为0");
            }
            base=1/base;
            exponent=-exponent;
        }
        //exponent是偶数
        if((exponent&1)==0) {
            return powVersionOne(base,exponent>>1)*powVersionOne(base,exponent>>1);
        }
        ////exponent是奇数
        else {
            return powVersionOne(base,(exponent-1)>>1)*powVersionOne(base,(exponent-1)>>1)*base;
        }
    }
    /**
     * 2.快速幂算法
     * 复杂度：O(logN)
     */
    public static double powVersionTwo(double base,int exponent){
        boolean flag=true;
        if(exponent==0) {
            return 1;
        }
        if (exponent<0){
            if(Math.abs(base)<0.000001) {
                throw new RuntimeException("分母不能为0");
            }
            flag=false;
            exponent=-exponent;
        }
        double result=1.0;//最终结果
        double multiplier=base;//乘数项
        while(exponent!=0){
            if((exponent&1)==1) {
                result*=multiplier;//该位为1时才把对应位的乘数项乘上去
            }
            multiplier*=multiplier;//每移动一位都将乘数项翻倍
            exponent=exponent>>1;//右移一位，此处exponent>0，所以不用考虑符号的问题
        }
        return flag?result:1/result;
    }

    public static void main(String[] args) {
        System.out.println(powVersionTwo(2.0,-2));
        //System.out.println(powVersionTwo(0,-2));
        System.out.println(powVersionTwo(0,3));
        System.out.println(powVersionTwo(3,0));
        System.out.println(powVersionTwo(-2,3));
        System.out.println(powVersionTwo(-3,-5));
    }
}

