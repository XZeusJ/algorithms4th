package ch22_MergeSort;

import edu.princeton.cs.algs4.StdOut;

public class MergeBU {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                merge(a, lo, mid, hi);
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // copy a[] into aux[]
        int len = a.length;
        Comparable[] aux = new Comparable[len];
        for (int i = lo; i <= hi; i++) aux[i] = a[i];

        int left = lo;
        int right = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (left > mid) a[i] = aux[right++];
            else if (right > hi) a[i] = aux[left++];
            else if (less(a[left], a[right])) a[i] = aux[left++];
            else a[i] = aux[right++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // parse command-line argument as an array of 1-character strings
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        show(a);
//        MergeSizes.sort(a);
        MergeBU.sort(a);
        show(a);
    }
}
