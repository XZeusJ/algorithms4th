/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.960;
    private final int numTrials;
    private final double[] result;
    private double m = 0;
    private double dev = 0;

    public PercolationStats(int n, int trials) {
        numTrials = trials;
        result = new double[numTrials];

        for (int i = 0; i < numTrials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (perc.isOpen(row, col)) continue;
                perc.open(row, col);
                result[i]++;
            }
            result[i] = result[i] / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(result);
    }

    public double stddev() {
        return StdStats.stddev(result);
    }

    public double confidenceLo() {
        if (m == 0) m = mean();
        if (dev == 0) dev = stddev();
        return m - CONFIDENCE_95 * dev / (Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        if (m == 0) m = mean();
        if (dev == 0) dev = stddev();
        return m + CONFIDENCE_95 * dev / (Math.sqrt(numTrials));
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percStat = new PercolationStats(n, trials);
        double m = percStat.mean();
        double dev = percStat.stddev();
        StdOut.printf("mean                    = %f\n", m);
        StdOut.printf("stddev                  = %f\n", dev);
        StdOut.print("95% confidence interval = [" + percStat.confidenceLo() + ", " + percStat
                .confidenceHi() + "]\n");
    }
}
