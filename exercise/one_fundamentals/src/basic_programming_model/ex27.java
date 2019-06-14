package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

public class ex27 {
    private static long COUNT = 0;
    private static long COUNT2 = 0;
    private static double[][] M;

    public static double slowbinomial(int N, int k, double p) {
        COUNT++;
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * slowbinomial(N - 1, k, p) + p * slowbinomial(N - 1, k - 1, p);
    }

    private static double fasterBinomial(int N, int k, double p) {
        COUNT2++;
        if (N == 0 && k == 0) {
            return 1.0;
        }
        if (N < 0 || k < 0) {
            return 0.0;
        }
        if (M[N][k] == -1) {  //将计算结果存起来，已经计算过的直接拿过来用，无需再递归计算
            M[N][k] = (1.0 - p) * fasterBinomial(N - 1, k, p) + p * fasterBinomial(N - 1, k - 1, p);
        }
        return M[N][k];
    }

    public static double binomial(int N, int k, double p) {
        M = new double[N + 1][k + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= k; j++) {
                M[i][j] = -1;
            }
        }
        return fasterBinomial(N, k, p);
    }

    public static void main(String[] args) {
        StdOut.println("result: " + slowbinomial(10, 5, 0.5) + ", count: " + COUNT);
        StdOut.println("result: " + binomial(10, 5, 0.5) + ", count: " + COUNT2);

    }
}
