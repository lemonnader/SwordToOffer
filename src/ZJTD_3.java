import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class ZJTD_3 {

    public static Scanner in = new Scanner(new BufferedInputStream(System.in));
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        String str = null;
        boolean flag = false;//是否已经出现"/*"
        str = in.nextLine();
        out.println(str);
        while (in.hasNext()) {
            str = in.nextLine();
            if (str.equals("}")) {
                out.println(str);
                break;
            }
            if (flag) {
                int index = str.indexOf("*/");//没有遇到*/就一直往后找
                if (index == -1) continue;
                String sub = str.substring(index + 2);
                if (sub.length() != 0) out.println(sub);
                flag = false;
                continue;
            }
            int index = str.indexOf("//");
            if (index == -1) {//本行没有出现“//”
                int index2 = str.indexOf("/*");
                if (index2 == -1) {
                    out.println(str);
                } else {
                    flag = true;
                    String sub = str.substring(0, index2);
                    out.println(sub);
                    if (str.indexOf("*/") != -1) {
                        flag = false;
                    }
                }
            } else {
                String sub = str.substring(0, index);
                out.println(sub);
            }
        }
        in.close();
        out.close();
        return;
    }
}
