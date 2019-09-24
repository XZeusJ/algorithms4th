package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdOut;

public class ex20 {
    private static double ln(int n) {
        if (n == 0) return 0;
        else return Math.log(n) + ln(n - 2);
    }

    public static void main(String[] args) {
        StdOut.println(ln(10));
    }
}
