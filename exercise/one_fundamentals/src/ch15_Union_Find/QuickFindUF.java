package ch15_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;   // id[i] = component identifier of i
    private int count;  // number of componets

    // initialize an empty union-find data structure with n sites
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    // returns the number of components
    public int count() {
        return count;
    }

    // return true if the two sites are in the same component
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // return the component identifier for the component containing site p
    public int find(int p) {
        return id[p];
    }

    // merges the component containing ste p with the component containing site q
    public void union(int p, int q) {
        int pID = id[p];    // to reduce the number of array accesses
        int qID = id[q];

        // p and q are already in the same component
        if (pID == qID) return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }

    /**
     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input,
     * where each integer represents some site;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
