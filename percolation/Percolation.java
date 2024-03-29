/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int gridSize;
    private final int lastIndexOfUF;
    private boolean[][] sites;
    private int numOfOpenSites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        gridSize = n;
        validate(n, n);
        uf = new WeightedQuickUnionUF(
                n * n + 2); // record information about whether the sites is connected
        sites = new boolean[n + 1][n + 1]; // record information about Open or block sites.
        numOfOpenSites = 0;

        // set the first line of sites array connect to start vitual uf.parent[0]
        // set the last line of sites array connect to end vitual uf.parent[n*n+1]
        lastIndexOfUF = n * n + 1;
        for (int j = 1; j <= gridSize; j++) {
            uf.union(0, xyTo1D(1, j));
            uf.union(lastIndexOfUF, xyTo1D(n, j));
        }
    }

    private int xyTo1D(int x, int y) {
        return gridSize * (x - 1) + y; // map "sites" array entries to "uf" array entries
    }

    // validate that p is a valid index
    private void validate(int row, int col) {
        if (row <= 0 || row > gridSize) throw new IllegalArgumentException(
                "index " + row + " is not between 1 and " + gridSize);
        if (col <= 0 || col > gridSize) throw new IllegalArgumentException(
                "index " + col + " is not between 1 and " + gridSize);
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        // validate the indices of the site
        validate(row, col);

        // mark the site as open
        sites[row][col] = true;
        numOfOpenSites++;

        // links the site to its open neighbors

        if (row - 1 <= 0 || row + 1 > gridSize || col - 1 <= 0 || col + 1 > gridSize) return;
        if (isOpen(row - 1, col)) uf.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        if (isOpen(row + 1, col)) uf.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        if (isOpen(row, col - 1)) uf.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        if (isOpen(row, col + 1)) uf.union(xyTo1D(row, col + 1), xyTo1D(row, col));
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        validate(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        validate(row, col);
        return (uf.connected(0, xyTo1D(row, col)) && isOpen(row, col));
    }

    public int numberOfOpenSites() { // number of open sites
        return numOfOpenSites;
    }

    public boolean percolates() { // does the system percolate?
        if (gridSize == 1) return isOpen(1, 1); // special condition about 1x1 array
        return uf.connected(0, lastIndexOfUF);
    }

    private void showSites() {
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++)
                StdOut.print(sites[i][j] + " ");
            StdOut.println();
        }
        showFullSites();
        StdOut.println("is percolates? " + percolates());
        StdOut.println();
    }

    private void showFullSites() {
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if (isFull(i, j)) StdOut.printf("(%d, %d) is full.\n", i, j);
            }
        }
        StdOut.println();
    }


    public static void main(String[] args) { // test client (optional)
        StdOut.print("Enter the n: ");
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);


        // test Open() and isOpen()
        perc.showSites();
        perc.open(1, 3);
        perc.showSites();
        perc.open(2, 3);
        perc.showSites();
        perc.open(3, 3);
        perc.showSites();
        perc.open(3, 1);
        perc.showSites();
        perc.open(2, 1);
        perc.showSites();
        perc.open(1, 1);
        perc.showSites();
        //
        // // test validate()
        // // perc.open(1, 3);
        // // perc.showSites();
        //
        // // test isFull()
        // StdOut.println("(1,2) is full? " + perc.isFull(1, 2));
        // StdOut.println("(2,2) is full? " + perc.isFull(2, 2));
        // perc.open(2, 2);
        // StdOut.println("(2,2) is full after open? " + perc.isFull(2, 2));
        // perc.open(3, 3);
        // StdOut.println("(3,3) is full after open(3,3)? " + perc.isFull(3, 3));
        // perc.open(3, 2);
        // StdOut.println("(3,3) is full after open(3,2)? " + perc.isFull(3, 3));
        //
        // StdOut.println();
        //
        // // test percolates()
        // perc.showSites();


    }
}
