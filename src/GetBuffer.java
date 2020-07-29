import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author LiMin
 * @Title: GetBuffer
 * @Description: 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的
 * 平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 * @date 2020/7/26  20:58
 */
public class GetBuffer {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //最大堆的建立
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private int count = 0;

    public void insert(Integer num) {
        count++;
        if ((count & 1) == 0) {//第偶数个放入最小堆,然后把最小堆的最小值放入最大堆;位运算判断奇偶效率较高
            minHeap.offer(num);
            int min = minHeap.poll();
            maxHeap.offer(min);
        } else {//第奇数个放入最大堆,然后把最大堆的最大值放入最小堆
            maxHeap.offer(num);
            int max = maxHeap.poll();
            minHeap.offer(max);
        }
    }

    public Double getMedian() {
        //注意每次取的时候只是读，不能取出，要用peek函数
        if ((count & 1) == 0) {//偶数个取两个堆顶元素的平均值
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;//注意是先把两个整数的和转化成double再除以2
        } else {//奇数个直接从最小堆取堆顶
            return (double) (minHeap.peek());
        }
    }

    public static void main(String[] args) {
        //测试用例：5 2 3 4 1 6 7 0 8
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        GetBuffer getBuffer = new GetBuffer();
        String str = input.nextLine();
        Scanner in = new Scanner(str);
        while (in.hasNext()) {//在等待要扫描的输入时，此方法可能阻塞。扫描器将不执行任何输入。所以循环会一直下去。
            getBuffer.insert(in.nextInt());
            out.println(getBuffer.getMedian());
            out.flush();
        }
        in.close();
        out.close();
        return;
    }
}
