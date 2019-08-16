/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints_ov {
    private final ArrayList<LineSegment> lineSegments;

    /**
     * Finds all line segments containing 4 pointsCopy or more pointsCopy.
     *
     * @throws IllegalArgumentException if the argument to the constructor is null if any
     *                                  point in the array is null, or if the argument to
     *                                  the constructor contains a repeated point.
     */
    public FastCollinearPoints_ov(Point[] points) {
        isLegal(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        lineSegments = new ArrayList<>();

        for (int i = 0; i < pointsCopy.length - 3; i++) {
            Point startPoint = pointsCopy[i];
            double[] preSlopes = new double[i];
            Point[] nextPoints = new Point[pointsCopy.length - i - 1];

            for (int j = 0; j < i; j++) {
                preSlopes[j] = startPoint.slopeTo(pointsCopy[j]);
            }

            for (int j = 0; j < pointsCopy.length - i - 1; j++) {
                nextPoints[j] = pointsCopy[i + j + 1];
            }
            //for binary search
            Arrays.sort(preSlopes);
            // sort after point by slope
            Arrays.sort(nextPoints, startPoint.slopeOrder());
            findLineSegments(preSlopes, startPoint, nextPoints);
        }
    }

    private void findLineSegments(double[] preSlopes, Point startPoint, Point[] nextPoints) {
        double currentSlope;
        double beforeSlope = Double.NEGATIVE_INFINITY;
        int countRepeat = 1;

        for (int i = 0; i < nextPoints.length; i++) {
            currentSlope = startPoint.slopeTo(nextPoints[i]);
            if (beforeSlope != currentSlope) {
                //beforeSlope != currentSlope and countRepeat>= 3
                if (countRepeat >= 3 && !isSubLine(beforeSlope, preSlopes)) {
                    lineSegments.add(new LineSegment(startPoint, nextPoints[i - 1]));
                }
                countRepeat = 1;
            } else {
                countRepeat++;
            }
            beforeSlope = currentSlope;
        }
        //record the rest of the situation.
        if (countRepeat >= 3 && !isSubLine(beforeSlope, preSlopes)) {
            lineSegments.add(new LineSegment(startPoint, nextPoints[nextPoints.length - 1]));
        }
    }

    private void isLegal(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private boolean isSubLine(double tempSlope, double[] beforeSlope) {
        int lo = 0;
        int hi = beforeSlope.length - 1;
        // use binary search
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (tempSlope < beforeSlope[mid]) {
                hi = mid - 1;
            } else if (tempSlope > beforeSlope[mid]) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of line segments.
     *
     * @return the number of line segments.
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * Returns a LineSegment array.
     *
     * @return a LineSegment array.
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // Arrays.sort(points);
        // for (Point p : points) {
        //     StdOut.println("point" + p);
        //     // p.draw();
        // }
        // StdOut.println();
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints_ov collinear = new FastCollinearPoints_ov(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }

        // StdDraw.show();
    }
}
