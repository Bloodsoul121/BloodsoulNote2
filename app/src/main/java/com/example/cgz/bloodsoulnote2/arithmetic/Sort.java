package com.example.cgz.bloodsoulnote2.arithmetic;

/**
 * Created by cgz on 18-4-19.
 */

public class Sort {

    private static final String TAG = "Sort";

    private static void log(int[] arr) {
        log("", arr);
    }

    private static void log(String msg, int[] arr) {
        System.out.print(msg + " --> ");
        for (int anArr : arr) {
            System.out.print(anArr);
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 冒泡排序
     */
    public static void BubbleSortArray(int[] arr) {
        log("ori", arr);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        log("new", arr);
    }

    /**
     * 选择排序
     */
    public static void SelectSortArray(int[] arr) {
        log("ori", arr);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[i] > arr[j+1]) {
                    swap(arr, i, j+1);
                }
            }
        }
        log("new", arr);
    }

    /**
     * 插入排序
     */
    public static void InsertSortArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            // 将temp与已排序元素从小到大比较，寻找temp应插入的位置
            while (j >= 0 && arr[j] > temp) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        log("new", arr);
    }

    /**
     * 壳排序 - 缩小增量排序
     */
    public static void ShellSortArray(int[] arr) {
        int n = arr.length;
        for (int incr = 3; incr < 0; incr--)//增量递减，以增量3，2，1为例
        {
            for (int L = 0; L < (n - 1) / incr; L++)//重复分成的每个子列表
            {
                for (int i = L + incr; i < n; i += incr)//对每个子列表应用插入排序
                {
                    int temp = arr[i];
                    int j = i - incr;
                    while (j >= 0 && arr[j] > temp) {
                        arr[j + incr] = arr[j];
                        j -= incr;
                    }
                    arr[j + incr] = temp;
                }
            }
        }
        log("new", arr);
    }

}
