import javax.swing.plaf.basic.BasicButtonUI;
import java.time.Clock;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class RankingMethods {
    /**
     * 1.冒泡排序，是通过每一次遍历获取最大/最小值；将最大值/最小值放在尾部/头部；
     * 然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值...
     * 此处采取最小值放在头部
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {//外层循环,i值等于已经冒泡完成的最大值个数
            int count = 0;
            for (int j = 0; j < arr.length - i - 1; j++) {
                //内层循环，升序，在剩下的数据里遍历，每次循环完成其实是把一个最大值放到最后了
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    count++;
                }
            }
            if (count == 0) {
                break;//程序改进，一旦发现某次内循环并没有发生任何交换，就说明数据已经有序了
            }
            // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 2.选择排序
     * 将第一个值看成最小值，然后和后续的比较找出最小值和下标；交换本次遍历的起始值和最小值
     * 说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，
     * 然后每次遍历无序列表找出最小值。
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;//先假定当前值为最小值
            for (int j = i + 1; j < arr.length; j++) {//内循环从i+1开始，每循环一次就找到当前无序序列的最小值
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;//当前值(有序序列的下一个值)和最小值交换
            //  System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 3.插入排序，类似于打扑克，每摸一张就按序插入，默认从第二个数据开始比较。
     * 前面的（i之前）为有序序列，内循环用当前值和其前面的值比较，小于则交换；
     * 否则（内循环已经到了最头上的元素或者已经比前面的值大了），退出内循环
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {//从第二个开始比较
            int j = i - 1;
            int data = arr[i];//这样先保存好当前值可以省去后面很多赋值操作
            while (j >= 0 && data < arr[j]) {
                arr[j + 1] = arr[j];//依次挪动
                j--;
            }
            arr[j + 1] = data;//将当前值插入正确位置
            //System.out.println("插入排序" + Arrays.toString(arr));
        }
    }

    /**
     * 4.希尔排序
     * 是插入排序的一种高效率的实现，也叫缩小增量排序
     * 先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序。
     * 增量的序列取法？没有统一标准，但最后一步必须是1；
     * 因为不同的取法涉及时间复杂度不一样，常用的h序列由Knuth提出，该序列从1开始，
     * 通过如下公式产生：h = 3 * h +1
     * 反过来程序需要反向计算h序列，应该使用：h=(h-1)/3
     */
    public static void shellSort(int[] arr) {
        int i = 1;
        //先求出最大的i:
        while (i <= arr.length / 3) {
            i = 3 * i + 1;
        }
        for (; i >= 1; i = (i - 1) / 3) {//每次分割的步长
            for (int j = i; j < arr.length; j++) {//从第i个元素开始，在之前的每隔i个元素构成的序列中做插入排序
                //注意一定是j++,而不是j+=i，才是真正的希尔排序
                int data = arr[j];//先把要插入元素取出来，省去后面很多赋值操作
                int k = j - i;//在此次插入排序中，k初始对应的元素相当于当前元素的前一个
                while (k >= 0 && arr[k] > data) {//没到头并且值比要插入的元素大就把它后移
                    arr[k + i] = arr[k];
                    k -= i;
                }
                arr[k + i] = data;//注意这个地方是k+i
            }
           // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 5.归并排序
     * 使用了递归分治的思想：先使子序列有序，然后融合：
     * 右半边的当前元素小于左半边的当前元素，则取右半边元素；否则则取左半边的元素。
     * 左半边用尽，则取右半边元素；右半边用尽，则取左半边元素；
     * 说明：两边相等时取左边的元素，保证了稳定性
     */
    public void mergeSortRecursion(int[] arr) {
        int[] temp = new int[arr.length];//多次递归的过程中只用到这一个辅助数组
        sort(arr, temp, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));
    }

    //左右序列融合函数
    public void merge(int[] arr, int[] temp, int left, int mid, int right) {
        //System.out.println("融合左" + left + " 右" + right);
        int pLeft = left;
        int pRight = mid + 1;
        int pSave = left;//左右序列当前值指针和保存指针，一最好不要直接动传进来的索引值
        //或者要把left先保存下来，后面把数组的值重新转移到arr的时候会用到
        while ((pLeft <= mid) && (pRight <= right)) {
            if (arr[pLeft] <= arr[pRight]) {
                temp[pSave++] = arr[pLeft++];
            } else {
                temp[pSave++] = arr[pRight++];
            }
        }//当前元素哪边更小就取哪边的元素放到辅助数组里，并把对应指针后移
        while (pLeft <= mid) {
            temp[pSave++] = arr[pLeft++];
        }
        while (pRight <= right) {
            temp[pSave++] = arr[pRight++];
        }//哪边有剩下的就继续取
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k];//把融合好的数据重新转进arr，准备下一次使用
        }
    }

    public void sort(int[] arr, int[] temp, int left, int right) {
      //  System.out.println("排序左" + left + " 右" + right);
        int mid = left + (right - left) / 2;
        if (left < right) {//子序列至少要有一个元素
            //递归的终点是序列只有一个元素，此时什么也不做，直接融合
            sort(arr, temp, left, mid);
            sort(arr, temp, mid + 1, right);//递归解决左右序列的排序
            merge(arr, temp, left, mid, right);//左右序列融合
        }
    }

    /**
     * 非递归方法
     */
    public void mergeSortnonrecur(int[] arr) {
        int[] temp = new int[arr.length];//外部定义辅助数组，节省空间
        int length = 1;//子序列初始长度为1
        while (length < arr.length) {
            mergePass(arr, temp, length);//最终数据在temp里
            length *= 2;//子序列长度成倍增长
            mergePass(temp, arr, length);//最终数据在arr里
            length *= 2;
        }//两次mergePass保证了最后的数据一定在arr里
      //  System.out.println(Arrays.toString(arr));
    }

    public void mergePass(int[] arr, int[] temp, int length) {//length是当前子序列长度
        int i = 0;
        int n ;
        n = arr.length;
        /*这个函数的各种边界条件一定要搞清楚*/
        //i每次跳过两段
        for (; i <= n - 2 * length; i += 2 * length) {
            //判决条件解释：因为最后可能剩下两段或者一段(长度还小于length)
            mergenonrecur(arr, temp, i, i + length - 1, i + 2 * length - 1);
            //这个函数每次运行完数据保存都在temp数组里，没有重新倒腾回arr
        }
        //还剩下两个序列
        if ((i + length) < n)
        {
            //最后一个序列的右边界是n-1
            mergenonrecur(arr, temp, i, i + length - 1, n - 1);
        } else //还剩下一个序列
        {
            while (i < n) {
                temp[i] = arr[i++];
            }
        }

    }

    //左右序列融合函数
    public void mergenonrecur(int[] arr, int[] temp, int left, int mid, int right) {
        int pLeft = left;
        int pRight = mid + 1;
        //左右序列当前值指针和保存指针，一最好不要直接动传进来的索引值
        int pSave = left;
        //或者要把left先保存下来，后面把数组的值重新转移到arr的时候会用到
        while ((pLeft <= mid) && (pRight <= right)) {
            if (arr[pLeft] <= arr[pRight]) {
                temp[pSave++] = arr[pLeft++];
            } else {
                temp[pSave++] = arr[pRight++];
            }
        }//当前元素哪边更小就取哪边的元素放到辅助数组里，并把对应指针后移
        while (pLeft <= mid) {
            temp[pSave++] = arr[pLeft++];
        }
        while (pRight <= right) {
            temp[pSave++] = arr[pRight++];
        }//哪边有剩下的就继续取
    }

    /**
     * 6.快速排序
     * 取左中右三个数中的中位数为主元，并把三个数中的最小值放在最左边，最大值放在最右边
     * 主元先藏在倒数第二个位置，然后在剩下的队列中，看成有左右两个指针（高低）。
     * 开始高指针向左移动，如果遇到小于中间值的数据，先停下来；切换低指针移动，
     * 当低指针移动到大于中间值的时候，高低指针数据互换，重复以上操作
     * 直到低指针不小于高指针时退出，并且将中间值赋值给低指针位置，最后一次移动指针时使得低指针指向较大的值。
     * 递归解决中间值的左右两边序列
     */
    public void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
      //  System.out.println(Arrays.toString(arr));
    }

    public void qSort(int[] arr, int left, int right) {
        int pLeft = left;
        int pRight = right - 1;
        int pivot = medium3(arr, left, right);//主元
        int cutOff = 1000000;//数据规模临界值
        if ((right - left + 1) > cutOff) {
            //System.out.println("快速排序");
            while (true) {
                while (arr[++pLeft] < pivot) {
                    ;
                }
                while (arr[--pRight] > pivot) {
                    ;//等于也停下来做交换
                }
                if (pLeft < pRight) {
                    swap(arr, pLeft, pRight);
                } else {
                    break;
                }
            }
            swap(arr, pLeft, right - 1);//最后一次指针移动会使得低指针指向较大的数据
            //执行完上述程序会使得pivot一次放到最后的正确位置
            qSort(arr, left, pLeft - 1);
            qSort(arr, pLeft + 1, right);
        } else {
            insertionSort(arr);//数据量太小时用简单排序
        }
    }

    /**
     * 取左中右三个数中的中位数为主元，并把三个数中的最小值放在最左边，最大值放在最右边
     * 并把主元先藏在倒数第二个位置,这样排序的时候就只需要考虑left+1~right-2
     */
    public int medium3(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        swap(arr, right - 1, mid);
        return arr[right - 1];
    }

    public static void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    /**
     * 7.堆排序
     * 将无序序列构建成一个堆，根据升序降序需求可以构建最大堆或者最小堆(推荐最大堆);
     * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     */
    public void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            down(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);//将最大值放到数组最后
            down(arr, 0, i);//将arr[0]下沉，重新把剩下的数组调整成最大堆,每次传进去的数组长度减一
        }
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 如果数组除了根节点以外本身就是最大堆（把根节点和最后一个结点交换以后），那么执行完根节点下沉以后还是最大堆
     * 因为执行过程中是把左右孩子中较大的那一个浮上去,就能保证根节点的值依旧大于左右子树
     */
    public void down(int[] arr, int root, int length) {//将根节点下沉到正确位置
        int data = arr[root];//根节点的值
        int parent = root;//父节点
        int child = parent * 2 + 1;//左孩子
        while (child < length) {//判决条件是为了不让后面的索引值出界
            if (child != (length - 1) && arr[child] < arr[child + 1]) {
                child++;//有右孩子并且右孩子的值大于左孩子，把child指向较大的孩子
            }
            if (arr[child] > data) {//某个孩子的值大于父节点的值!!要和data比较，别写错了
                arr[parent] = arr[child];//较大孩子的值上浮
                parent = child;//父节点指针下移
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
        //退出循环的条件是父节点指针没有孩子或者data大于等于此时较大孩子的值，这时应该把之前保存的data
        //放到现在的父节点指针位置
        arr[parent] = data;
        //System.out.println(Arrays.toString(arr));
    }



    /**
     * 8.桶排序
     * 以统计学生成绩并排序为例，成绩0~100一共101个桶
     */
    public void bucketSort(int[] arr) {
        int[] buckets = new int[101];//计数每个桶里面元素的个数
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i]]++;
        }
        int k = 0;
        for (int i = 0; i <= 100; i++) {
            while ((buckets[i]--) > 0) {
                arr[k++] = i;
            }
        }
       // System.out.println(Arrays.toString(arr));
    }

    /**
     * 9.基数排序
     */
    public void radixSort(int[] data, int radix, int d) {
        // 缓存数组
        int[] tmp = new int[data.length];
        // buckets数组定义了radix个桶,分别代表数字0-9
        int[] buckets = new int[radix];
        for (int i = 0, rate = 1; i < d; i++) {//rate代表当前比较的是哪一位，1指各位;d代表最大值一共有几位
            // 将data中的元素完全复制到tmp数组中
            //System.arraycopy(data, 0, tmp, 0, data.length);
            // 计算每个待排序数据的当前关键字
            for (int j = 0; j < data.length; j++) {
                int subKey = (tmp[j] / rate) % radix;//subkey为元素当前位上的数字
                buckets[subKey]++;//对应桶的值（元素个数）加一
            }
            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];//计算累计的元素个数，此时buckets[j]-1
                // 应该是当前位上的数字是j的最后一个元素的索引值
            }
            //按照顺序把元素放进数组
            for (int m = 0; m < data.length; m++) {//倒着来放很方便
                int subKey = (tmp[m] / rate) % radix;
                data[--buckets[subKey]] = tmp[m];//每放置一个元素之前先把索引值向前减一才是正确的位置
            }
            rate *= radix;//个位→十位→百位...
        }
        //System.out.println(Arrays.toString(data));
    }

    /**
     * 10.计数排序
     */
    //计数排序的初步实现，使用了多余的空间，可以尝试不使用多余的空间
    public static void countingSort(int[] arr, int m, int n) {
        int len = arr.length;
        int[] tem = new int[n - m + 1];
        for (int i = 0; i < len; i++) {
            tem[arr[i] - m] += 1;
        }
        for (int i = 0, index = 0; i < tem.length; i++) {
            int item = tem[i];
            while (item-- != 0) {
                arr[index++] = i + m;
            }
        }
    }

    public int[] randArray(int n, int scale) {
        Random rand = new Random(47);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(scale);
        }
        return arr;
    }

    public static void main(String[] args) {
        RankingMethods rank = new RankingMethods();
        int[] a = rank.randArray(200000, 1000);
        int[] b = a.clone();
        int[] c = b.clone();
        int[] d = b.clone();
        int[] e = b.clone();
        int[] f = b.clone();
        int[] g = b.clone();
        int[] h = b.clone();
        int[] i = b.clone();
        int[] j = b.clone();

        long s = Clock.systemDefaultZone().millis();
        rank.quickSort(a);
        System.out.println("quickSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        rank.mergeSortRecursion(b);
        System.out.println("mergeSortRecursion耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        rank.mergeSortnonrecur(c);
        System.out.println("mergeSortnonrecur耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        rank.radixSort(d, 10, 4);
        System.out.println("radixSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        rank.quickSort(e);
        System.out.println("quickSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        bubbleSort(f);
        System.out.println("bubbleSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        insertionSort(g);
        System.out.println("insertionSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        selectionSort(h);
        System.out.println("selectionSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        shellSort(i);
        System.out.println("shellSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

        s = Clock.systemDefaultZone().millis();
        rank.heapSort(j);
        System.out.println("heapSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");

    }

}
