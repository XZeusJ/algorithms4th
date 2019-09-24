package ch25_Sorting_Applications;

import ch24_Priority_Queues.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class LPT {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);  // number of machines

        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        Arrays.sort(jobs);

        MinPQ<Processor> pq = new MinPQ<Processor>(m);
        for (int i = 0; i < m; i++)
            pq.insert(new Processor());

        for (int j = n-1; j >= 0; j++) {
            Processor min = pq.delMin();
            min.add(jobs[j]);
            pq.insert(min);
        }

        while (!pq.isEmpty())
            StdOut.println(pq.delMin());

    }

}
