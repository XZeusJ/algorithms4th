package ch24_Priority_Queues;

import edu.princeton.cs.algs4.StdOut;

public class CubeSum implements Comparable<CubeSum> {
    private final int i;
    private final int j;
    private final int sum;

    public CubeSum(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = i * i * i + j * j * j;
    }

    public int compareTo(CubeSum that) {
        if (this.sum < that.sum) return -1;
        if (this.sum > that.sum) return +1;
        return 0;
    }

    public String toString() {
        return sum + " = " + i + "^3" + "+" + j + "^3";
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        MinPQ<CubeSum> pq = new MinPQ<CubeSum>(n + 1);

        for (int i = 0; i < n; i++) {
            pq.insert(new CubeSum(i, 0));
        }

        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            StdOut.println(s);
            if (s.j < n) pq.insert(new CubeSum(s.i, s.j + 1));
        }
    }
}
