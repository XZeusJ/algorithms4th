package basic_programming_model;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex30 {
    private static int gcd(int m, int n) {
        if (n == 0) return m;
        else return gcd(n, m % n);
    }

    public static void main(String[] args) {
        int N = 4;
        boolean[][] a = new boolean[N][N];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = gcd(i, j) == 1;
                System.out.printf("%5b ",a[i][j]);
            }
            StdOut.println();
        }
    }

}
