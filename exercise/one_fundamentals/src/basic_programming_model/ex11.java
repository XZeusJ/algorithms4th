package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ex11 {
    public static void main(String[] args){
        boolean[][] a ={ { true, false, true, true },
                { false, true, false,true },
                { true, true, false, true } };

        int M = a.length;
        int N = a[0].length;

        // print col num
        StdOut.print(" ");
        for (int i = 0; i < N; i++) {
            StdOut.print(i);
        }
        StdOut.println();

        // print row num And a[i][j]
        for (int i = 0; i < M; i++) {
            StdOut.print(i);
            for (int j = 0; j < N; j++) {
                StdOut.print(a[i][j] ? "*" : ' ');
            }
            StdOut.println();
        }
    }
}
