package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex5 {
    public static void main(String[] args){
        double x = StdIn.readDouble();
        double y = StdIn.readDouble();
        StdOut.println( 0 < x && x<1 && 0<y && y<1);
    }
}