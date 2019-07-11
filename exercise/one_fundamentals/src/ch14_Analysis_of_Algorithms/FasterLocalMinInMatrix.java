package ch14_Analysis_of_Algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// not finished yet
// http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf
public class FasterLocalMinInMatrix {

    private static int findMin(int[][] a, int midi, int midj) {
        int min = Integer.MAX_VALUE;
        int mini = -1;
        int minj = -1;
        for (int i = 0; i < a.length; i++) {

        }
        return -1;
    }

//    private static int[] localMin(int[][] a) {
//        return localMin(a, 0, a.length - 1, a.length);
//    }

//    private static int[] localMin(int[][] a, int loi, int hii, int len) {
//        int midi = loi + (hii - loi) / 2;
//        int midj = loj + (hij - loj) / 2;
//
//        // look at bound, center row, and center column
//        int index = findMin(a, midi, midj);
//
//        if ((mid == 0 || a[mid][j] < a[mid - 1][j]) && (mid == len - 1 || a[mid][j] < a[mid + 1][j]))
//            return new int[]{mid, j};
//        else if (mid > 0 && a[mid - 1][j] < a[mid][j])
//            return localMin(a, lo, mid - 1, len);
//        else return localMin(a, mid + 1, hi, len);
//    }

    public static void main(String[] args) {
        // init random array
        int[][] m = new int[10][10];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = StdRandom.uniform(100);
            }

        // display array
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                StdOut.printf("%3d", m[i][j]);
            }
            StdOut.println();
        }

        // find local min by ~NlgN
//        int[] index = localMin(m);
//        StdOut.println("local min index -> " + index[0] + " " + index[1]);
//        StdOut.println("local min -> " + m[index[0]][index[1]]);
    }
}
