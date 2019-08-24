/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private final Picture picture;

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
        return Math.pow(c1.getRed() - c2.getRed(), 2) +
                Math.pow(c1.getGreen() - c2.getGreen(), 2) +
                Math.pow(c1.getBlue() - c2.getBlue(), 2);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
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

        return new int[0];
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
