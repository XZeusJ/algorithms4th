package ch23_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    /*
    The crux of the method is the partitioning process, which rearranges the array to make the
    following three conditions hold:
    The entry a[j] is in its final place in the array, for some j.
    No entry in a[lo] through a[j-1] is greater than a[j].
    No entry in a[j+1] through a[hi] is less than a[j].
     */

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                // if (j == lo) break; // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // parse command-line argument as an array of 1-character strings
        String s;
        if (args.length != 0) s = args[0];
        else s = "FASTEREXAMPLE";
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        show(a);
        Quick.sort(a);
        show(a);
    }
}
