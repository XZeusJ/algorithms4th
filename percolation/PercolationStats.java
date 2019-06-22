/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int numTrials;
    private double[] result;
    private double m = 0;
    private double dev = 0;

    public PercolationStats(int n, int trials) {
        int gridSize = n;
        numTrials = trials;
        result = new double[numTrials];

        for (int i = 0; i < numTrials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, gridSize + 1);
                int col = StdRandom.uniform(1, gridSize + 1);
                if (perc.isOpen(row, col)) continue;
                perc.open(row, col);
                result[i]++;
            }
            result[i] = result[i] / (gridSize * gridSize);
        }
    }

    public double mean() {
        m = StdStats.mean(result);
        return m;
    }

    public double stddev() {
        dev = StdStats.stddev(result);
        return dev;
    }

    public double confidenceLo() {
        return m - 1.960 * dev / (Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        return m + 1.960 * dev / (Math.sqrt(numTrials));
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
