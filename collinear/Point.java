/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally, if the two points are
     * (px, py) and (qx, qy), then the slope is (qy - py) / (qx - px). For completeness, the slope
     * is defined to be +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and Double.NEGATIVE_INFINITY if
     * (px, py) and (qx, qy) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double diffX = (double) (that.x - this.x);
        double diffY = (double) (that.y - this.y);
        if (diffY == 0) {
            return diffX == 0 ? Double.NEGATIVE_INFINITY : +0.0;
        } else if (diffX == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            return diffY / diffX;
        }

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally, the invoking
     * point (px, py) is less than the argument point (qx, qy) if and only if either py < qy or if
     * py = qy and px < qx.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point (px = qx and py =
     * qy); a negative integer if this point is less than the argument point; and a positive integer
     * if this point is greater than the argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        // int px = this.x, py = this.y;
        // int qx = that.x, qy = that.y;
        // if (py < qy || (py == qy && px < qx)) return -1;
        // else if (px == qx && py == qy) return 0;
        // else return 1;

        if (this.y > that.y) return +1;
        else if (this.y < that.y) return -1;
        else return Integer.compare(this.x, that.x);
    }

    /**
     * Compares two points by the slope they make with this point. The slope is defined as in the
     * slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() { // this is a method which return a new comparator class
        /* YOUR CODE HERE */
        return new slopeOrder();
    }

    private class slopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);

            // if (slope1 < slope2) return -1;
            // else if (slope1 > slope2) return 1;
            // else return 0;

            return Double.compare(slope1, slope2);
        }
    }


    /**
     * Returns a string representation of this point. This method is provide for debugging; your
     * program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // print the points
        for (Point p : points)
            StdOut.println(p);

        // test slopeTo
        // for (Point p : points)
        //     for (Point q : points)
        //         StdOut.println(p + " to " + q + " 's slope is " + p.slopeTo(q));

        // test compareTo
        // Arrays.sort(points);
        // StdOut.println("sorted!");
        // for (Point p : points)
        //     StdOut.println(p);

        // test slopeOrder
        // Arrays.sort(points, points[0].slopeOrder());
        // StdOut.println("sorted!");
        // for (Point p : points)
        //     StdOut.println(p);


        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points)
            for(Point q: points)
                p.drawTo(q);
        StdDraw.show();


    }
}
