package ch11_basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class ex13 {
    public static void main(String[] args){
        int M = 4;
        int N = 6;
        int[][] a = genArray(M, N);
        printArray(a);

        int[][] transA = transArray(a);
        printArray(transA);
    }

    private static int[][] genArray(int M, int N){
        Random random = new Random();
        int[][] a = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = random.nextInt(10) + 1;
            }
        }
        return a;
    }

    private static void printArray(int[][] a){
        int M = a.length;
        int N = a[0].length;
        for (int i = 0; i < M; i++) {
            StdOut.println();
            for (int j = 0; j < N; j++) {
                StdOut.print(a[i][j]+" ");
            }
        }
        StdOut.println();
    }

    private static int[][] transArray(int[][] a){
        int M = a.length;
        int N = a[0].length;
        int[][] transA = new int[N][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                transA[j][i] = a[i][j];
            }
        }
        return transA;
    }
}
