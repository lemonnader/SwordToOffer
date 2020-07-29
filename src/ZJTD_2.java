import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class ZJTD_2 {

    public static Scanner in = new Scanner(new BufferedInputStream(System.in));
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        int t = in.nextInt();
        int ans = 0;
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n % (m + 1) != 0) {
                ans++;
            }
        }
        in.close();
        out.println(ans);
        out.close();
        return;
    }
}

