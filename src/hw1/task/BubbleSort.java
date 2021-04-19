package hw1.task;

import java.util.Arrays;

public class BubbleSort {

    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;
        for (int k = n; k >= 2; --k) {
            boolean ordered = true;
            for (int i = 1; i < k; ++i) {
                if (a[i - 1].compareTo(a[i]) > 0) {
                    T x = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = x;
                    ordered = false;
                }
            }
            if (ordered)
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Integer[] nums = new Integer[]{5, 2, 3, 1, 7, 0};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
