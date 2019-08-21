/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G); // deep copy the argument G

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths bfsV, bfsW;

        // 1. find root
        int root = -1;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i) == 0){
                root = G.outdegree(i);
                break;
            }
        }
        // 2. choose one of v, w closer to root
        bfsV = new BreadthFirstDirectedPaths(G, v);
        bfsW = new BreadthFirstDirectedPaths(G, w);

        int lenVtoRoot = 0, lenWtoRoot = 0;
        for (int i: bfsV.pathTo(root)) lenVtoRoot++;
        for (int i: bfsW.pathTo(root)) lenWtoRoot++;

        int closeV = lenVtoRoot < lenWtoRoot ? lenVtoRoot : lenWtoRoot;

        // 3. check if closer points connect with the other, if not, check again on add(closer point)
        // not stop till find shortest ancestor


    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int vlen = 0, wlen = 0;
        for (Integer i : v) {
            if (i == null) throw new IllegalArgumentException();
            else vlen++;
        }
        for (Integer i : w) {
            if (i == null) throw new IllegalArgumentException();
            else wlen++;
        }
        return vlen + wlen;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer i : v) {
            if (i == null) throw new IllegalArgumentException();
        }
        for (Integer i : w) {
            if (i == null) throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
