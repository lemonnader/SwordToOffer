/**
 * @author LiMin
 * @Title: ConcurrentTest
 * @Description: 并发测试
 * @date 2020/7/21  18:06
 */
public class ConcurrentTest {
    public static int g0=0,g1=0,g2=0,g3=0;

    public static void main(String[] args) {
    while(true) {
       g0=0;g1=0;g2=0;g3=0;
            new Thread(() -> {
                g0 = 1;
                g1 = 2;
            }).start();
            new Thread(() -> {
                g2 = g1;
                g3 = g2;
            }).start();
        System.out.println(g0+" "+g1+" "+g2 + " " + g3);
      }
 /*       int count=0;
        for(int a=1;a<=20;a++){
            for(int b=1;b<=20;b++){
                for(int c=1;c<=20;c++){
                    for(int d=1;d<=20;d++){
                        if((a+b+c+d)==20){
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);   */
    }
}
