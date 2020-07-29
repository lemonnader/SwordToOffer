/**
 * @author LiMin
 * @Title: ZJTD_1
 * @Description:
 * @date 2020/7/17  21:26
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ZJTD_1 {

    public static Scanner in = new Scanner(new BufferedInputStream(System.in));
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        for (int k = 0; k < n; k++) {
            int M = in.nextInt();
            int N = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int X = in.nextInt();
            int Y = in.nextInt();
            int[][] table = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    table[i][j] = in.nextInt();
                }
            }
            Position begin = new Position(x, y);
            Position end = new Position(X, Y);
            out.println(shortest(table, begin, end));
        }
        in.close();
        out.close();
        return;
    }

    public static int shortest(int[][] map, Position begin, Position end) {
        int[][] direct = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<Position> queue = new LinkedList<>();
        queue.offer(begin);
        int[][] dis = new int[map.length][map[0].length];//从begin到该坐标处所经历的结点个数;默认值为-1,所以也能作为每个节点是否已经被访问的标志数组
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis[0].length; j++) {
                dis[i][j] = -1;
            }
        }
        dis[begin.y][begin.x] = 1;//注意x是列，y是行
        while (!queue.isEmpty()) {
            Position pre = queue.poll();
            if (pre.x == end.x && pre.y == end.y) {//走到终点了
                break;
            }
            for (int i = 0; i < 4; i++) {
                int newx = pre.x + direct[i][0];
                int newy = pre.y + direct[i][1];
                //没越界并且没被访问并且可达就入队，否则搜索下一个点
                if (newx >= 0 && newx < dis[0].length && newy >= 0 && newy < dis.length && dis[newy][newx] == -1 && map[newy][newx] == 0) {
                    dis[newy][newx] = dis[pre.y][pre.x] + 1;//更新当前坐标和begin之间的路径长度
                    Position cur = new Position(newx, newy);//一定要new一个对象放进去
                    queue.offer(cur);
                }
            }
        }
        return dis[end.y][end.x];
    }
}
