package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

public class ex10 {
    public static void main(String[] args){
        int[] a = new int[10];
        for (int i = 0; i< 10; i++)
            a[i] = i * i;

        for (int i = 0; i < 10; i++) {
            StdOut.println(a[i]);
        }
    }
}
