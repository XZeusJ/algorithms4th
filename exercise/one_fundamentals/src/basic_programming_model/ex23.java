package basic_programming_model;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ex23 {
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

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);

//        StdOut.println("123");
//        for (int i = 0; i < whitelist.length; i++) StdOut.print(whitelist[i]);
//        StdOut.println();

        while(!StdIn.isEmpty()){
            // 读取键值, 如果不存在于白名单中则将其打印
            int key = StdIn.readInt();
            if (rank(key, whitelist)<0)
                StdOut.println(key);
        }

    }
}
