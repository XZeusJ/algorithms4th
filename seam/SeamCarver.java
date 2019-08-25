/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private final Picture picture;
    private double[] distTo;
    private int[][] edgeTo;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(this.picture);
    }

    // width of current picture
    public int width() {
        return this.picture.width();
    }

    // height of current picture
    public int height() {
        return this.picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) throw new IllegalArgumentException();
        if (y < 0 || y > height() - 1) throw new IllegalArgumentException();

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) return 1000;

        Color top = this.picture.get(x, y + 1);
        Color bottom = this.picture.get(x, y - 1);
        Color left = this.picture.get(x - 1, y);
        Color right = this.picture.get(x + 1, y);

        return Math.sqrt(squareGradient(left, right) + squareGradient(top, bottom));
    }

    private double squareGradient(Color c1, Color c2) {
        int rgb1 = c1.getRGB(), rgb2 = c2.getRGB();
        return Math.pow(((rgb1 >> 16) & 0xff) - ((rgb2 >> 16) & 0xff), 2) +
                Math.pow(((rgb1 >> 8) & 0xff) - ((rgb2 >> 8) & 0xff), 2) +
                Math.pow(((rgb1 >> 0) & 0xff) - ((rgb2 >> 0) & 0xff), 2);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        // this.picture =
        return new int[0];
    }


    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        // construct 2D energy array
        double[][] energy = new double[height()][width()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++)
                energy[col][row] = energy(col, row);
        }

        // INIT
        this.edgeTo = new int[width()][height()];
        this.distTo = new double[width()]; // current row's distant

        // init first row's distant all to be 1000;
        for (int v = 0; v < distTo.length; v++)
            distTo[v] = 1000;

        // we accumulate and update energy in distTo[]
        // and also record traces in edgeTo[]
        for (int i = 1; i < height(); i++) {
            // keep prev row's distant
            double[] prevDistTo = distTo.clone();
            // init curr row's distant
            for (int k = 0; k < distTo.length; k++)
                distTo[k] = Double.POSITIVE_INFINITY;

            for (int j = 1; j < width(); j++) {
                int x = j, y = i;
                double e = energy[x][y];

                relax(x - 1, x, y, e, prevDistTo);
                relax(x, x, y, e, prevDistTo);
                relax(x + 1, x, y, e, prevDistTo);
            }
        }

        // now we have complete DAG, and it can be assure that the shortest weighted path must
        // start from any pixels in the top row to any pixels in the bottom row.
        // so we can find the shortest path by this DAG

        // find the end pixel of the shortest path
        double minWeight = Double.POSITIVE_INFINITY;
        int minIndex = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] < minWeight) {
                minWeight = distTo[i];
                minIndex = i;
            }
        }

        int[] seam = new int[height()];
        for (int i = height() - 1; i >= 0; i--) {
            seam[i] = minIndex;
            minIndex = edgeTo[minIndex][i];
        }
        return seam;
    }

    private void relax(int prev, int x, int y, double e, double[] prevDistTo) {
        if (prev < 0 || prev >= prevDistTo.length) return;

        double weight = prevDistTo[prev];

        if (distTo[x] > weight + e) {
            distTo[x] = weight + e;
            edgeTo[x][y] = prev;
        }
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        isValidSeam(seam, true);

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        isValidSeam(seam, false);

    }

    private void isValidSeam(int[] seam, boolean isHorizon) {
        if (seam == null) throw new IllegalArgumentException();
        if (isHorizon) { // horizontalSeam case
            if (seam.length != width()) throw new IllegalArgumentException();
            if (height() <= 1) throw new IllegalArgumentException();

            for (int i : seam) {
                if (i < 0 || i > height()) throw new IllegalArgumentException();
            }
        }
        else { // VerticalSeam case
            if (seam.length != height()) throw new IllegalArgumentException();
            if (width() <= 1) throw new IllegalArgumentException();

            for (int i : seam) {
                if (i < 0 || i > height()) throw new IllegalArgumentException();
            }
        }
        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }
    }


    //  unit testing (optional)
    public static void main(String[] args) {

    }
}
