package ch11_Basic_Programming_Model;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class ex28 {

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);
        int len = whitelist.length;

        for (int i = 0; i < len; i++) {
            StdOut.print(whitelist[i] + " ");
        }
        StdOut.println();

        // set the repeat element into 0 in the whitelist
        for (int i = 0; i < len - 1; i++) {
            if (whitelist[i] == whitelist[i + 1]) whitelist[i] = 0;
        }

        Arrays.sort(whitelist);
        for (int i = 0; i < len; i++) {
            if (whitelist[i] != 0) {
                StdOut.print(whitelist[i] + " ");
            }
        }


    }
}
