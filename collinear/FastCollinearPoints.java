/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        isLegal(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        lineSegments = new ArrayList<>();

        int l = points.length;
        for (int i = 0; i < l - 3; i++) {
            Point startPoint = pointsCopy[i];    // represent startPoint point
            double[] prevSlopes
                    = new double[i];  // used for recording slopes previous on the startPoint point
            Point[] nextPoints = new Point[l - i - 1];

            for (int j = 0; j < i; j++)
                prevSlopes[j] = startPoint.slopeTo(pointsCopy[j]);

            for (int j = 0; j < l - i - 1; j++)
                nextPoints[j] = pointsCopy[i + j + 1];

            Arrays.sort(prevSlopes);    // for binary search
            Arrays.sort(nextPoints, startPoint.slopeOrder());    // sort after point by slope
            findLineSegments(prevSlopes, startPoint, nextPoints);
        }
    }

    private void findLineSegments(double[] prevSlopes, Point startPoint, Point[] nextPoints) {
        double currentSlope;
        double beforeSlope = Double.NEGATIVE_INFINITY;
        int countRepeat = 1;

        for (int i = 0; i < nextPoints.length; i++) {
            currentSlope = startPoint.slopeTo(nextPoints[i]);
            if (beforeSlope != currentSlope) {
                if (countRepeat >= 3 && !isSubLine(beforeSlope, prevSlopes))
                    lineSegments.add(new LineSegment(startPoint, nextPoints[i - 1]));
                countRepeat = 1;
            }
            else
                countRepeat++;
            beforeSlope = currentSlope;
        }

        // handle end situation
        if (countRepeat >= 3 && !isSubLine(beforeSlope, prevSlopes))
            lineSegments.add(new LineSegment(startPoint, nextPoints[nextPoints.length - 1]));
    }

    private boolean isSubLine(double tempSlope, double[] beforeSlopes) {
        int lo = 0;
        int hi = beforeSlopes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (tempSlope < beforeSlopes[mid]) hi = mid - 1;
            else if (tempSlope > beforeSlopes[mid]) lo = mid + 1;
            else return true;
        }
        return false;
    }


    private void isLegal(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        for (int i = 0; i < points.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) throw new IllegalArgumentException();
        }
    }


    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }


    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("input40.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
