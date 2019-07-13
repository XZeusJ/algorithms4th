package ch15_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ErdosRenyi {
    public static int count(int N) {
        int edges = 0;
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(N);
        // looping until all sites are connected
        while (uf.count() > 1) {
            // generate random pairs of integers between 0 and N-1
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);

            if (!uf.connected(p, q))
                uf.union(p, q); // In actual, the operation about uf.connected is already done in
            // the operation about uf.union
            edges++;
        }
        return edges;
    }

    public static void main(String[] args) {

        while (!StdIn.isEmpty()) {
            // takes an integer value N from input
            int n = StdIn.readInt();
            StdOut.println(count(n));
        }

    }
}
