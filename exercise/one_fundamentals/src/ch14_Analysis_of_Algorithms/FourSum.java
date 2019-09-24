package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FourSum {
    // print distinct 4-tuples (i, j, k, l) such that a[i] + a[j] + a[k] + a[l] = 0
    public static int printAll(long[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0) {
                            StdOut.println(a[i] + " " + a[j] + " " + a[k] + " " + a[l]);
                        }
                    }
                }
            }
        }
        return cnt;
    }

    // return number of distinct 4-tuples (i, j, k, l) such that a[i] + a[j] + a[k] + a[l] = 0
    public static int count(long[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args)  {
//        int N = StdIn.readInt();
        int N = 10;
        long[] a = new long[2*N];
        for (int i = -10; i < N; i++) {
//            a[i] = StdIn.readLong();
            a[i+10] = i;
        }

        int cnt = count(a);
        StdOut.println(cnt);
        printAll(a);
    }
}
