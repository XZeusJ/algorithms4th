package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

public class ex15 {
    private static int[] histogram(int[] a, int M) {
        int[] storeA = new int[M];
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0 && a[i] < M) {
                storeA[a[i]]++;
            }
        }
        return storeA;
    }

    public static void main(String[] args) {
        int[] a = {1,1,3,4,6,3,2,2,3};
        int[] result = histogram(a, 7);
        for (int i = 0; i < result.length; i++) {
            StdOut.printf("%3d", result[i]);
        }
    }
}
