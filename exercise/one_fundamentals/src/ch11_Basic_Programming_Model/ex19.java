package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdOut;

public class ex19 {
    public static long F(int N, long[] memo) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        if (memo[N] != 0) {
            return memo[N];
        } else {
            long result = F(N - 1, memo) + F(N - 2, memo);
            memo[N] = result;
            return result;
        }
    }

    public static void main(String[] args) {
        long[] memo = new long[10000000];
        for (int i = 0; i < 200; i++) {
            StdOut.println(i + " " + F(i, memo));
        }
    }
}
