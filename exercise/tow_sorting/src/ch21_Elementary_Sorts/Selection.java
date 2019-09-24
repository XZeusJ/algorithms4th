package ch21_Elementary_Sorts;

import edu.princeton.cs.algs4.StdOut;

public class Selection {

    // this class should not be instantiated.
    private Selection() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
        String[] a = {"s","o","r","t","e","x","a","p","l","n","m"};
        show(a);
        Selection.sort(a);
        show(a);
    }
}
