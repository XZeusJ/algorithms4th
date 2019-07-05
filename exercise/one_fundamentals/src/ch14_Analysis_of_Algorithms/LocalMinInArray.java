package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class LocalMinInArray {
    private static int localMin(int[] a) {
        int len = a.length;

        int mid = len /2;
        if (a[mid] < a[mid+1] || a[mid] > a[mid-1])
            return mid;
        else {
            mid /= 2;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++)
            a[i] = i;
        StdRandom.shuffle(a);
        for (int n: a) {
            StdOut.print(n + " ");
        }
        StdOut.println();

        int lm = localMin(a);
        StdOut.println("local min -> " + lm);
    }
}
