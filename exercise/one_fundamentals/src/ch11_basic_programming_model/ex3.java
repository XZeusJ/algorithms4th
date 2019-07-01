package ch11_basic_programming_model;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex3 {
    public static void main(String[] args) {
        StdOut.println("please enter a number: ");
        int a = StdIn.readInt();
        StdOut.println("please enter a number: ");
        int b = StdIn.readInt();
        StdOut.println("please enter a number: ");
        int c = StdIn.readInt();

        if (a == b && b == c){
            StdOut.println("equal");
        }else{
            StdOut.println("not equal");
        }
    }
}
