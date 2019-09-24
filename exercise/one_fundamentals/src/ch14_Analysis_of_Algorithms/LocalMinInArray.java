package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class LocalMinInArray {
    private static int localMin(int[] a) {
        return localMin(a, 0, a.length - 1, a.length);
    }

    private static int localMin(int[] a, int lo, int hi, int len) {
        int mid = lo + (hi - lo) / 2;

//        StdOut.println("lo mid hi -> " + lo + " " + mid + " " + hi);
        if ((mid == 0 || a[mid] < a[mid - 1]) && (mid == len - 1 || a[mid] < a[mid + 1]))
            return mid;
        else if (mid > 0 && a[mid - 1] < a[mid])
            return localMin(a, lo, mid - 1, len);
        else return localMin(a, mid + 1, hi, len);
    }

    public static void main(String[] args) {
        // init random array
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++)
            a[i] = i;
        StdRandom.shuffle(a);

        // display array
        for (int n : a) {
            StdOut.print(n + " ");
        }
        StdOut.println();

        // find local min by ~2lgN
        int lm = localMin(a);
        StdOut.println("local min index -> " + lm);
        StdOut.println("local min -> " + a[lm]);
    }
}
