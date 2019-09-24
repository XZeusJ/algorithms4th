package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdOut;

public class HotOrCold {

    public static void bs(int[] a, int key) {
        bs(a, 0, a.length - 1, key);
    }

    private static void bs(int[] a, int lo, int hi, int key) {
        if (lo > hi) {
            StdOut.println("Cannot guess number");
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) {
            StdOut.println("You guess is right. " + key);
            return;
        } else if (distance(a[mid - 1], key) < distance(a[mid + 1], key)) {
            StdOut.println("key is in the left side of " + mid);
            StdOut.println("we now find between " + lo + " and " + (mid - 1));
            bs(a, lo, mid - 1, key);
        } else {
            StdOut.println("key is in the right side of " + mid);
            StdOut.println("we now find between " + (mid + 1) + " and " + hi);
            bs(a, mid + 1, hi, key);
        }
    }

    private static int distance(int a, int b) {
        return Math.abs(a - b);
    }

    public static void main(String[] args) {
        int[] a = new int[20];
        int key = 16;
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();

        bs(a, key);
    }
}
