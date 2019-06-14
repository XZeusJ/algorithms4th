package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

public class ex24 {
    private static int euclid(int m, int n) {
        if (n==0) return m;
        StdOut.printf("Now m is: %d, n is: %d\n", m, n);
        return euclid(n, m % n);
    }

    public static void main(String[] args) {
        StdOut.println(euclid(1111111,1234567));
        StdOut.println(euclid(13,65));
        StdOut.println(euclid(16,22));
    }


}
