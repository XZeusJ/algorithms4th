/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int gridSize;

    private int xyTo1D(int x, int y) {
        return x * gridSize + y;
    }

    // validate that p is a valid index
    private void validate(int row, int col) {
        int p = xyTo1D(row, col);
        if (p < 0 || p >= gridSize * gridSize) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        gridSize = n;
        uf = new WeightedQuickUnionUF(gridSize * gridSize);
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        // validate the indices of the site
        validate(row, col);

        // mark the site as open

        // likes the site to its open neighbors

    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        return true;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        return true;
    }

    public int numberOfOpenSites() { // number of open sites
        return 1;
    }

    public boolean percolates() { // does the system percolate?
        return true;
    }

    public static void main(String[] args) { // test client (optional)
        int n = StdIn.readInt();
        Percolation pclt = new Percolation(n);
        StdOut.println(pclt.numberOfOpenSites());
    }
}
