package ch12_Data_Abstraction;

import edu.princeton.cs.algs4.StdOut;

public class ex6_IsCircularShifts {
    public static boolean isCS(String s, String t) {
        return (s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0);
    }

    public static void main(String[] args) {
        StdOut.println(isCS("ACTGACG", "TGACGAC"));
    }
}
