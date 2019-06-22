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
    private int[][] sites;
    private int numOfOpenSites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        gridSize = n;
        uf = new WeightedQuickUnionUF(
                n * n); // record information about whether the sites is connected
        sites = new int[n][n]; // record information about Open or block sites.
        numOfOpenSites = 0;
    }

    private int xyTo1D(int x, int y) {
        return x * gridSize + y; // map "sites" array entries to "uf" array entries
    }

    // validate that p is a valid index
    private void validate(int row, int col) {
        if (row < 0 || row >= gridSize) throw new IllegalArgumentException(
                "index " + row + " is not between 0 and " + (gridSize - 1));
        if (col < 0 || col >= gridSize) throw new IllegalArgumentException(
                "index " + col + " is not between 0 and " + (gridSize - 1));
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        // validate the indices of the site
        validate(row, col);

        // mark the site as open
        sites[row][col] = 1;
        numOfOpenSites++;

        // links the site to its open neighbors
        if (isOpen(row - 1, col)) uf.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        if (isOpen(row + 1, col)) uf.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        if (isOpen(row, col - 1)) uf.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        if (isOpen(row, col + 1)) uf.union(xyTo1D(row, col + 1), xyTo1D(row, col));
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) return false;
        return sites[row][col] == 1;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        int q = xyTo1D(row, col);
        for (int i = 0; i < gridSize; i++) {
            // StdOut.println(i + " " + q + " is " + uf.connected(i, q));
            if (uf.connected(i, q) && isOpen(row, col)) return true;
        }
        return false;
    }

    public int numberOfOpenSites() { // number of open sites
        return numOfOpenSites;
    }

    public boolean percolates() { // does the system percolate?
        for (int col = 0; col < gridSize; col++) {
            if (isFull(gridSize - 1, col)) return true;
        }
        return false;
    }

    public void showSites() {
        for (int i = 0; i < sites.length; i++) {
            for (int j = 0; j < sites[0].length; j++)
                StdOut.print(sites[i][j] + " ");
            StdOut.println();
        }
        StdOut.println("is percolates? " + percolates());
        StdOut.println();
    }


    public static void main(String[] args) { // test client (optional)
        StdOut.print("Enter the n: ");
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);

        // test Open() and isOpen()
        perc.showSites();
        perc.open(1, 1);
        perc.showSites();
        perc.open(1, 2);
        perc.showSites();

        // test validate()
        // perc.open(1, 3);
        // perc.showSites();

        // test isFull()
        StdOut.println("0,2 is full? " + perc.isFull(0, 2));
        StdOut.println("1,2 is full? " + perc.isFull(1, 2));
        perc.open(0, 1);
        StdOut.println("1,2 is full? " + perc.isFull(1, 2));


        // test percolates()
        // perc.open(0, 1);
        // perc.showSites();
        //
        // perc.open(2, 1);
        // perc.showSites();

    }
}
