/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private Digraph G;
    ST<Integer, Integer> dtv;   // symbol tabel about <dist ancestor>
    ST<Integer, Integer> setdtv; // symbol tabel about <dist ancestor> from two point sets

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G); // deep copy the argument G
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();
        ancestor(v, w);
        if (!dtv.isEmpty()) return dtv.min();
        else return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v > G.V() || v < 0 || w > G.V() || w < 0) throw new IllegalArgumentException();

        // 1. find root
        // int root = 0;
        // while (G.outdegree(v) != 0) {
        //     for (int i : G.adj(v)) {
        //         root = i;
        //         StdOut.println(root);
        //         break;
        //     }
        // }
        // StdOut.println(root);

        // 2. choose one of v, w closer to root
        BreadthFirstDirectedPaths bfsV, bfsW;
        bfsV = new BreadthFirstDirectedPaths(G, v);
        bfsW = new BreadthFirstDirectedPaths(G, w);
        //
        // int lenVtoRoot = 0, lenWtoRoot = 0;
        // for (int i : bfsV.pathTo(root)) lenVtoRoot++;
        // for (int i : bfsW.pathTo(root)) lenWtoRoot++;
        //
        // int closeV = lenVtoRoot < lenWtoRoot ? lenVtoRoot : lenWtoRoot;
        // we set closeV is the point closer to root
        // and bfsCloseV is the bfs start from the other point, which is far from root
        // BreadthFirstDirectedPaths bfsCloseW = lenVtoRoot < lenWtoRoot ? bfsW : bfsV;

        // 3. check if closer points connect with the other, if not, check again on add(closer point)
        // not stop till find shortest ancestor

        dtv = new ST<>(); // to store <distance ancestorID>, which to find the min distance
        ancestor(bfsV, bfsW, v);

        if (!dtv.isEmpty()) return dtv.get(dtv.min());
        else return -1;
    }

    private void ancestor(BreadthFirstDirectedPaths bfsV, BreadthFirstDirectedPaths bfsW,
                          int ance) {
        // if (v == root) return;

        // check current V if is the ancestor of W
        if (bfsW.hasPathTo(ance))
            // once find ancestor, store <dist ancestor>, then stop current branch
            dtv.put(bfsW.distTo(ance) + bfsV.distTo(ance),
                    ance); // put <dist ancestor> in symbol tabel
        else
            // if not find ancestor, check all adjacent points from v
            for (int ancePlus : G.adj(ance))
                ancestor(bfsV, bfsW, ancePlus);
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
        for (Integer i : v) if (i == null) throw new IllegalArgumentException();
        for (Integer i : w) if (i == null) throw new IllegalArgumentException();

        for (Integer i : v) {
            // i = i + 1;
        }

        return -1;
    }


    public static void main(String[] args) {
        // In in = new In(args[0]);
        In in = new In("digraph1.txt");
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
