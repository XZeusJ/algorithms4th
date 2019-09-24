package ch23_QuickSort;

import edu.princeton.cs.algs4.StdOut;

public class Sort2distinct {
    // rearranges a[] in ascending order assuming a[] has at most 3 distinct values
    public static void sort(Comparable[] a) {
        int lo = 0, hi = a.length - 1;
        int i = 0;
        while (i <= hi) {
            int cmp = a[i].compareTo(a[lo]);
            if (cmp < 0) exch(a, lo++, i++);
//            else if (cmp > 0) exch(a, i, hi--); // if a[] has at most 3 distinct values
            else if (cmp == 0) i++;
        }
    }

    private static boolean less(Comparable v, Comparable m) {
        return v.compareTo(m) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    // test client
    public static void main(String[] args) {

        // parse command-line argument as an array of 1-character strings
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        // sort a print results
        show(a);
        sort(a);
        show(a);
        StdOut.println();
    }
}
