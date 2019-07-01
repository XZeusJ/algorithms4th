package ch11_basic_programming_model;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex29 {

    private static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // 被查找的键值要么不存在, 要么必然存在于a[lo..hi]之中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    private static int count(int key, int[] a){
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);

        StdOut.print("Please enter the key: ");
        int key = StdIn.readInt();
        int index = rank(key, whitelist);

        if (index<0) StdOut.println("This key is not in the whitelist");
        else
            StdOut.println("About "+index+" numbers in the whitelist less than the key "+key);
            StdOut.println(("About "+ count(key, whitelist)+" copies in the whitelist as the key "+key));



    }
}
