package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdOut;

public class ex22 {
    private static int rank(int key, int[] a) {
        return rankIter(key, a, 0, a.length - 1, 1);
    }

    private static int rankIter(int key, int[] a, int lo, int hi, int depth) {
        for (int i = 0; i < depth; i++) {
            StdOut.printf(" ");
        }
        StdOut.println("lo=" + lo + ", hi=" + hi);

        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rankIter(key, a, lo, mid - 1, depth+1);
        else if (key > a[mid])
            return rankIter(key, a, mid + 1, hi, depth+1);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 8, 23, 45, 67, 123};
        int key = 5;
        int pos = rank(key, a);
        if (pos == -1) StdOut.println("not found!");
        else StdOut.println("position="+pos);
    }
}
