package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BitonicSearch {
    // create a bitonic array of size N
    public static int[] bitonic(int N) {
        int mid = StdRandom.uniform(N);
        int[] a = new int[N];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i - 1] + 1 + StdRandom.uniform(9);
        }

        if (mid > 0) a[mid] = a[mid - 1] + StdRandom.uniform(10) - 5;

        for (int i = mid + 1; i < N; i++) {
            a[i] = a[i - 1] - 1 - StdRandom.uniform(9);
        }

        for (int i = 0; i < N; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        return a;
    }

    // find the index of the maximum in a bitonic subarray a[lo..hi]
    public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid + 1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    }

    public static boolean ascendingBinarySearch(int[] a, int lo, int hi, int key) {
        if (lo > hi) return false;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return ascendingBinarySearch(a, lo, hi - 1, key);
        if (key > a[mid]) return ascendingBinarySearch(a, lo + 1, hi, key);
        else return true;
    }

    public static boolean descendingBInarySearch(int[] a, int lo, int hi, int key) {
        if (lo > hi) return false;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return descendingBInarySearch(a, lo + 1, hi, key);
        if (key > a[mid]) return descendingBInarySearch(a, lo, hi - 1, key);
        else return true;
    }

    public static void main(String[] args) {
        int N;
        if (args.length == 0) N = 10;
        else N = Integer.parseInt(args[0]);

        // init bitonic array
        int[] a = bitonic(N);
        // find max index of the array by binary search
        int maxIndex = max(a, 0, N - 1);
        StdOut.println("max = " + a[maxIndex]);

        int i = 0;
        while (true && i < 5) {
            StdOut.print("Enter the num you want to find: ");
            int n = StdIn.readInt();
            // split the array into two part, use binary search
            // to check if the number was there separately
            boolean isInBitonic =
                    ascendingBinarySearch(a, 0, maxIndex, n) || descendingBInarySearch(a, maxIndex,
                    a.length - 1, n);
            if (isInBitonic) StdOut.println("we find the number");
            else StdOut.println("cannot find the number");
            i++;
        }
        StdOut.println("Over");

    }
}
