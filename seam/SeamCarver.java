/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {

    }

    // current picture
    public Picture picture() {

    }

    // width of current picture
    public int width() {

    }

    // height of current picture
    public int height() {

    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) throw new IllegalArgumentException();
        if (y < 0 || y > height() - 1) throw new IllegalArgumentException();

    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {

    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {

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
            if (Math.abs(seam[i] - seam[i-1]) > 1) throw new IllegalArgumentException();
        }
    }


    //  unit testing (optional)
    public static void main(String[] args) {

    }
}
