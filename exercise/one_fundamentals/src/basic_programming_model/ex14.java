package basic_programming_model;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex14 {
    public static void main(String[] args){
        while (true){
            int N = StdIn.readInt();
            StdOut.println(lg(N));
        }
    }

    private static int lg(int N) {
        int exp = 2;
        int count = 0;
        while (exp <= N) {
            exp *= 2;
            count++;
        }
        return count;
    }
}
