package ch22_MergeSort;

import edu.princeton.cs.algs4.StdOut;

public class MergeTD {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
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
        MergeTD.sort(a);
        show(a);
    }
}
