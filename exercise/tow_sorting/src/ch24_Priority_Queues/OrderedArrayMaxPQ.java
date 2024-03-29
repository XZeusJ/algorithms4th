package ch24_Priority_Queues;

import edu.princeton.cs.algs4.StdOut;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // elements
    private int n;      // number of elements

    public OrderedArrayMaxPQ(int capacity) {
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
        return pq[n - 1];
    }

    public Key delMax() {
        return pq[--n];
    }


    public void insert(Key key) {
        int i = n-1;
        while (i >= 0 && less(key, pq[i])) {
            pq[i+1] = pq[i];
            i--;
        }
        pq[i+1] = key;
        n++;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }



    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }

}
