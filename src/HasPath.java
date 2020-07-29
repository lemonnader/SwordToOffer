/**
 * @author LiMin
 * @Title: HasPath
 * @Description: 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * @date 2020/7/28  9:27
 */
public class HasPath {
    public static void main(String[] args) {
        HasPath has = new HasPath();
        char[] matrix = "ABCESFCSADEE".toCharArray();
        char[] str = "ABCCED".toCharArray();
        System.out.println(has.hasPath(matrix, 3, 4, str));
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0 || matrix.length < str.length || str == null || str.length <= 0) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, rows, cols, str, 0, j, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[] matrix, int rows, int cols, char[] str, int step, int x, int y) {
        int index = cols * y + x;//先计算出（x,y）在一维数组中的索引值
        //判断边界和check条件
        if (x < 0 || x >= cols || y < 0 || y >= rows || matrix[index] == '#' || matrix[index] != str[step]) {
            return false;
        }
        if (step == str.length - 1) {
            return true;//匹配成功的截止条件
        }
        //当前字符可以匹配，标记为已访问，这里不用再专门申请一个标记数组了
        char c = matrix[index];
        matrix[index] = '#';
        //递归检查四个方向是否至少有一个可以完全匹配，只要有一个匹配就可以返回了，所以用“或”来连接
        if (dfs(matrix, rows, cols, str, step + 1, x + 1, y) || dfs(matrix, rows, cols, str, step + 1, x - 1, y)
                || dfs(matrix, rows, cols, str, step + 1, x, y - 1) || dfs(matrix, rows, cols, str,step + 1, x, y + 1)) {
            return true;
        }
        //回溯：当前字符在当前路径的step不匹配，但是之后可以出现在其他路径，所以应该重新标记为未访问;另外step因为递归调用被压栈，返回上一层可以自动减一
        matrix[index] = c;
        //走到这还没返回说明走不通
        return false;
    }
}
