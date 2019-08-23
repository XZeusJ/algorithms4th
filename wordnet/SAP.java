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
    private final Digraph G;

    private class SAPResult {
        int ancestor;
        int length;

        public SAPResult(int ancestor, int length) {
            this.ancestor = ancestor;
            this.length = length;
        }
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G); // deep copy the argument G
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();

        return findSAP(v, w).length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();

        return findSAP(v, w).ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new IllegalArgumentException();
        if (w == null) throw new IllegalArgumentException();
        for (Integer i : v) if (i == null) throw new IllegalArgumentException();
        for (Integer i : w) if (i == null) throw new IllegalArgumentException();

        return findSAP(v, w).length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new IllegalArgumentException();
        if (w == null) throw new IllegalArgumentException();
        for (Integer i : v) if (i == null) throw new IllegalArgumentException();
        for (Integer i : w) if (i == null) throw new IllegalArgumentException();

        return findSAP(v, w).ancestor;
    }

    // private findSAP
    private SAPResult findSAP(int v, int w) {
        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(G, w);

        return findSAP(vBFS, wBFS);
    }

    private SAPResult findSAP(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(G, w);

        return findSAP(vBFS, wBFS);  // use brute-force algorithm
    }

    // Recursive way for find SAP
    // we must start from TWO points and see if sets has same ancestor
    // not correct, this algorithm is just from ONE point
    // ----waiting for revising
    private void findSAP(BreadthFirstDirectedPaths vBFS, BreadthFirstDirectedPaths wBFS, int anceW,
                         SAPResult result) {

        if (vBFS.hasPathTo(anceW)) {
            // once find ancestor, store <dist ancestor>, then stop current branch
            int length = vBFS.distTo(anceW) + wBFS.distTo(anceW);
            if (result.length == -1 || length < result.length) {
                result.length = length;
                result.ancestor = anceW;
            }
        }
        else
            // if not find ancestor, check all adjacent points from v
            for (int ancePlus : G.adj(anceW))
                findSAP(vBFS, wBFS, ancePlus, result);
    }

    // Brute-Force algorithm for find SAP
    private SAPResult findSAP(BreadthFirstDirectedPaths vBFS, BreadthFirstDirectedPaths wBFS) {
        SAPResult result = new SAPResult(-1, -1);

        for (int i = 0; i < G.V(); i++) {
            if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
                int length = vBFS.distTo(i) + wBFS.distTo(i);
                if (result.length == -1 || result.length > length) {
                    result.length = length;
                    result.ancestor = i;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // In in = new In(args[0]);
        In in = new In("digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
