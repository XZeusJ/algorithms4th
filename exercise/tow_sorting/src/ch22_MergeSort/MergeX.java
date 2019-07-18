package ch22_MergeSort;

/*
THREE IMPROVEMENTS:
add a cutoff from small subarrays,
test whether the array is already in order,
and avoid the copy by switching arguments in the recursive code.
 */
public class MergeX {
    private static final int CUTOFF = 7; // cutoff to insertion sort
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
//        if (lo >= hi) return;
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);        // why here is not
        sort(dst, src, mid + 1, hi);    // sort(src, dst, lo, mid) ??

        // test whether the array is already in order,
        // if so, not use merge method
        if (!less(src[mid+1], src[mid])) {
            for (int i = lo; i <= hi; i++) dst[i] = src[i];
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    dst[k] = src[j++];
            else if (j > hi)                dst[k] = src[i++];
            else if (less(src[j], src[i]))  dst[k] = src[j++];  // to ensure stability
            else                            dst[k] = src[i++];
        }
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // parse command-line argument as an array of 1-character strings
        String s;
        if (args.length != 0) s = args[0];
        else s = "FASTEREXAMPLE";
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        show(a);
        MergeX.sort(a);
        show(a);
    }
}
