package ch24_Priority_Queues;

import edu.princeton.cs.algs4.StdOut;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // elements
    private int n;      // number of elements

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) (new Comparable[capacity]);
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        int m = 0;

        for (int i = 0; i < n; i++) {
            if (less(m, i)) m = i;
        }
        exch(m, n - 1);

        return pq[n - 1];
    }

    public Key delMax() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (less(max, i)) max = i;
        }
        exch(max, n - 1);

        return pq[--n];
    }


    public void insert(Key key) {
        pq[n++] = key;
    }

    private void exch(int p, int q) {
        Key swap = pq[p];
        pq[p] = pq[q];
        pq[q] = swap;
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }


    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}
