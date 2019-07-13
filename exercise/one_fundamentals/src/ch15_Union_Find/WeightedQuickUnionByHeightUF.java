package ch15_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionByHeightUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] height;   // height[i] = height of subtree rooted at i
    private int count;  // number of components

    // initialize an empty union-find data structure with n sites
    public WeightedQuickUnionByHeightUF(int n) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 0;
        }
    }

    // returns the number of components
    public int count() {
        return count;
    }

    // return true if the two sites are in the same component
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // return the component parententifier for the component containing site p
    public int find(int p) {
        // path compress
        // we find what root of tree about p node
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // merges the component containing ste p with the component containing site q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make shorter root point to taller one
        if (height[rootP] < height[rootQ]) parent[rootP] = rootQ;
        else if (height[rootP] > height[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            height[rootP]++;
        }
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
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            StdOut.println(uf.count() + " components left");
        }
        StdOut.println(uf.count() + " components");
    }
}
