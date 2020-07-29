/**
 * @author LiMin
 * @Title: CutRope
 * @Description: 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），每段绳子的长度记为k[1],...,k[m]。
 * 请问k[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * @date 2020/7/28  22:38
 */
public class CutRope {
    public static void main(String[] args) {
        System.out.println(cutRopeMath(15));
    }

    /**
     * 动态规划
     */
    public static int cutRopeDP(int target) {
        //2,3单独返回
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        //开始dp
        int[] dp = new int[target + 1];//默认值为0
        for (int i = 0; i <= target; i++) {
            if (i <= 4) {
                dp[i] = i;
            } else {
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
                }
            }
        }
        return dp[target];
    }

    /**
     * 数学推导
     */
    public static int cutRopeMath(int target) {
        //2,3单独返回
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target % 3 == 0) {
            return (int) pow(3, target / 3);
        } else if (target % 3 == 1) {
            return (int) (4 * pow(3, target / 3 - 1));
        } else {
            return (int) (2 * pow(3, target / 3));
        }
    }

    /**
     * 第12题中写到的快速幂算法
     */
    public static double pow(double base, int exponent) {
        boolean flag = true;
        if (exponent == 0) return 1;
        if (exponent < 0) {
            if (Math.abs(base) < 0.000001)
                throw new RuntimeException("分母不能为0");
            flag = false;
            exponent = -exponent;
        }
        double result = 1.0;//最终结果
        double multiplier = base;//乘数项
        while (exponent != 0) {
            if ((exponent & 1) == 1)
                result *= multiplier;//该位为1时才把对应位的乘数项乘上去
            multiplier *= multiplier;//每移动一位都将乘数项翻倍
            exponent = exponent >> 1;//右移一位，此处exponent>0，所以不用考虑符号的问题
        }
        return flag ? result : 1 / result;
    }

}
