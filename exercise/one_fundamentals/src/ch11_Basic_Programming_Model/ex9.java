package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex9 {
    public static void main(String[] args){
        String s = "";
        int N = StdIn.readInt();

        for (int n = N; 0 < n; n /=2) {
            s = (n % 2) + s;
            StdOut.println("n is: "+n);
            StdOut.println("s is: "+s);
        }
        StdOut.println(s);
    }
}
