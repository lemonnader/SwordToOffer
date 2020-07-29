import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiMin
 * @Title: MovingCount
 * @Description: 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * @date 2020/7/28  14:55
 */
public class MovingCount {
    public static void main(String[] args) {
        MovingCount moving = new MovingCount();
        System.out.println(moving.movingCount(3, 3, 3));
    }

    private int count;

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        boolean[][] flag = new boolean[rows][cols];//默认初始化为false
//        dfs(flag,threshold,rows,cols,0,0);
//        return count;
        return bfs(flag, threshold, rows, cols);
    }

    private void dfs(boolean[][] flag, int threshold, int rows, int cols, int x, int y) {
        //边界和check条件判断
        if (x < 0 || x >= cols || y < 0 || y >= rows || flag[y][x] == true || !check(threshold, x, y)) {
            return;
        }
        //标记为已访问
        flag[y][x] = true;
        count++;//走过了一个新的格子
        dfs(flag, threshold, rows, cols, x + 1, y);
        //回过头去走其他格子的时候count是累加的，不用恢复
        dfs(flag, threshold, rows, cols, x - 1, y);
        dfs(flag, threshold, rows, cols, x, y + 1);
        dfs(flag, threshold, rows, cols, x, y - 1);
        //已经走过的格子不能再走第二次，所以不用恢复
        //牛客评论区看到一个不用维护全局变量result的写法：dfs返回类型设置为int，直接return四个方向的和然后+1（当前所在格子）
    }

    private int bfs(boolean[][] flag, int threshold, int rows, int cols) {
        Queue<Integer> queue = new LinkedList<>();
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(0);
        queue.offer(0);
        flag[0][0] = true;
        count = 1;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newx = x + dir[i][0];
                int newy = y + dir[i][1];
                if (newx < 0 || newx >= cols || newy < 0 || newy >= rows || flag[newy][newx] == true || !check(threshold, newx,
                        newy)) {
                    continue;
                } else {
                    flag[newy][newx] = true;
                    queue.offer(newx);
                    queue.offer(newy);
                    count++;
                }
            }

        }
        return count;
    }

    private boolean check(int threshold, int x, int y) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        while (y != 0) {
            sum += y % 10;
            y /= 10;
        }
        if (sum <= threshold) {
            return true;
        }
        return false;
    }
}
