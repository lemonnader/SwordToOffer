package pta;

import java.util.Scanner;

/**
 * @author limin
 */
public class Main {
    //最大地址
    public static final int MAX_SIZE = 100005;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int firstAddress;
        //头结点指针
        firstAddress = in.nextInt();
        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        int[] data = new int[MAX_SIZE];
        int[] next = new int[MAX_SIZE];
        int[] list = new int[MAX_SIZE];
        for (int i = 0; i < n; i++) {
            int tempAdd = in.nextInt();
            data[tempAdd] = in.nextInt();
            next[tempAdd] = in.nextInt();
        }
        in.close();
        //记录有效点(确实在链表上的点)的个数
        int sum = 0;
        while (firstAddress != -1) {
            list[sum++] = firstAddress;
            firstAddress = next[firstAddress];
        }

        int step = 2;
        for (int i = 0; i < sum - sum % k; i += k) {
            for (int j = 0; j < k / step; j++) {
                //逆序其实就是对称位置上的结点交换位置
                swap(list, i + j, i + k - 1 - j);
            }
        }
        for (int i = 0; i < sum - 1; i++) {
            System.out.printf("%05d %d %05d\n", list[i], data[list[i]], list[i + 1]);
        }
        //最后一个结点要单独输出
        System.out.printf("%05d %d -1\n", list[sum - 1], data[list[sum - 1]]);
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}