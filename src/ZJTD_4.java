import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ZJTD_4 {
    public static Scanner in = new Scanner(new BufferedInputStream(System.in));
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static ArrayList<Integer>[] table;
    public static int[] values;
    public static int[] visit;
    public static int[] k;
    public static int n, ans;

    public static void main(String[] args) {
        n = in.nextInt();
        values = new int[n + 1];
        table = new ArrayList[n + 1];
        k = new int[n + 1];
        visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = in.nextInt();
            table[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            table[x].add(y);
            table[y].add(x);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < table[i].size(); j++) {
                if (gcd(values[i], values[table[i].get(j)]) > 1) {
                    k[i] = 1;
                    k[table[i].get(j)] = 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) visit[j] = 0;
            if (k[i] == 1 && table[i].size() == 1) f(i, 1, values[i]);
        }
        if (ans == 1) out.println(0);
        else out.println(ans);
        in.close();
        out.close();
        return;
    }

    public static void f(int i, int now, int p) {
        visit[i] = 1;
        if (now > ans) ans = now;
        for (int j = 0; j < table[i].size(); j++) {
            if (visit[table[i].get(j)] == 0 && k[table[i].get(j)] == 1) {
                int pp = gcd(p, gcd(values[i], values[table[i].get(j)]));
                if (pp > 1) f(table[i].get(j), now + 1, pp);
                else f(table[i].get(j), 1, values[table[i].get(j)]);
            }
        }
    }

    public static int gcd(int x, int y) {
/*        //相减法
        while(x!=y){
            if(x>y){
                x-=y;
            }
            else {
                y-=x;
            }
        }
        return x;*/
/*        //更减相损法
        int count=1;
        while(x%2==0&&y%2==0){
            x=x/2;
            y=y/2;
            count*=2;
        }
        if(x<y){
            int max=y;
            y=x;
            x=max;
        }
        int differ=x-y;
        while(differ!=y){
            if(differ>y){
                x=differ;
            }
            else {
                x=y;
                y=differ;
            }
            differ=x-y;
        }
        return y*count;*/
        //辗转相除法，先把x变成两者之中较大的
        if (x < y) {
            int max = y;
            y = x;
            x = max;
        }
        int remain = x % y;//余数
        while (remain != 0) {//不断求余，直到整除
            x = y;
            y = remain;
            remain = x % y;
        }
        return y;
    }

    /*最大公倍数*/
    public static int gcm(int x, int y) {
        int gcd = gcd(x, y);
        return x * y / gcd;
    }
}
