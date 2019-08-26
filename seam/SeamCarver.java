

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;

    private int width, height;

    private Picture picture;
    private double[][] energy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();

        this.picture = new Picture(picture);
        this.height = picture.height();
        this.width = picture.width();

        this.energy = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++)
                this.energy[i][j] = energy(j, i);
        }
    }

    // current picture
    public Picture picture() {
        return new Picture(this.picture);
    }

    // width of current picture
    public int width() {
        return this.width;
    }

    // height of current picture
    public int height() {
        return this.height;
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
        this.energy = transpose(this.energy);
        int[] seam = findVerticalSeam();
        this.energy = transpose(this.energy);
        return seam;
    }

    private double[][] transpose(double[][] origin) {
        if (origin == null) throw new NullPointerException();
        if (origin.length < 1) throw new IllegalArgumentException();
        double[][] result = new double[origin[0].length][origin.length];
        for (int i = 0; i < origin[0].length; i++) {
            for (int j = 0; j < origin.length; j++)
                result[i][j] = origin[j][i];
        }
        int swap = this.width;
        this.width = this.height;
        this.height = swap;
        return result;
    }


    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {

        // INIT
        int[][] edgeTo = new int[width()][height()];
        double[] distTo = new double[width()]; // current row's distant

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
                double e = energy[y][x];

                relax(x - 1, x, y, e, prevDistTo, distTo, edgeTo);
                relax(x, x, y, e, prevDistTo, distTo, edgeTo);
                relax(x + 1, x, y, e, prevDistTo, distTo, edgeTo);
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

        // use end pixel to build the complete shortest path
        int[] seam = new int[height()];
        for (int i = height() - 1; i >= 0; i--) {
            seam[i] = minIndex;
            minIndex = edgeTo[minIndex][i];
        }
        return seam;
    }

    private void relax(int prev, int x, int y, double e, double[] prevDistTo, double[] distTo,
                       int[][] edgeTo) {
        if (prev < 0 || prev >= prevDistTo.length) return;

        double weight = prevDistTo[prev];

        if (distTo[x] > weight + e) {
            distTo[x] = weight + e;
            edgeTo[x][y] = prev;
        }
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        isValidSeam(seam, HORIZONTAL);

        Picture newPicture = new Picture(width(), height() - 1);

        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                if (seam[i] == j) continue;
                Color color = this.picture.get(i, j);
                newPicture.set(i, seam[i] > j ? j : j - 1, color);
            }
        }
        this.picture = newPicture;
        this.height = height() - 1;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        isValidSeam(seam, VERTICAL);

        Picture newPicture = new Picture(width() - 1, height());

        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                if (seam[i] == j) continue;

                Color color = this.picture.get(j, i);
                newPicture.set(seam[i] > j ? j : j - 1, i, color);
            }
        }
        this.picture = newPicture;
        this.width = width() - 1;
    }

    private void isValidSeam(int[] seam, boolean isHorizon) {
        if (seam == null) throw new IllegalArgumentException();
        if (isHorizon == HORIZONTAL) { // horizontalSeam case
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
                if (i < 0 || i > width()) throw new IllegalArgumentException();
            }
        }
        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) throw new IllegalArgumentException();
        }
    }


    //  unit testing (optional)
    // public static void main(String[] args) {
    //
    // }
}

