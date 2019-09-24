package ch24_Priority_Queues;

import ch21_Elementary_Sorts.Transaction;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;       // complete binary tree based on heap
    private int N;      // elements stored in pq[1..N], pq[0] not used

    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
        N = 0;
    }

    public MinPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[N+1];
        for (int i = 0; i < N; i++) { // copy keys into priority queue
            pq[i+1] = keys[i];
        }
        for (int k = N/2; k>=1; k--){ // reheapify
            sink(k);
        }
        assert isMinHeap();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
        assert isMinHeap();
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        assert isMinHeap();
        return min;
    }

    public boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > N) return true;
        int left = 2*k;
        int right = 2*k+1;
        if(left <= N && greater(k, left)) return false;
        if(right <= N && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    // helper function "less, exch, swim, sink"
    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j , j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(M+1);
        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) pq.delMin();
        }

        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t: stack) StdOut.println(t);
    }
}
