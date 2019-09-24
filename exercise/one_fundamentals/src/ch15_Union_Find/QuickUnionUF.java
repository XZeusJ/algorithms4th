package ch15_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int count;  // number of components

    // initialize an empty union-find data structure with n sites
    public QuickUnionUF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++)
            parent[i] = i;
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
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // merges the component containing ste p with the component containing site q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
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
        QuickUnionUF uf = new QuickUnionUF(n);
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
